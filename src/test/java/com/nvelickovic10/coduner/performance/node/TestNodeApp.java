package com.nvelickovic10.coduner.performance.node;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.nvelickovic10.coduner.httpclient.NodeResponse;
import com.nvelickovic10.coduner.util.CSVUtils;

public class TestNodeApp {

	public static class NodeCallerLoadTest implements Runnable {
		private HTTPNodeExecutionerCaller httpNodeExecutionerCaller = new HTTPNodeExecutionerCaller();
		private final String codeString;
		private int load;
		private final long reqId;

//		private static volatile double maxCompileTimeMs = -1;
//		private static volatile double minCompileTimeMs = Double.MAX_VALUE;
//
//		private static volatile double maxRunTimeMs = -1;
//		private static volatile double minRunTimeMs = Double.MAX_VALUE;
//
//		private static volatile double maxServiceTimeMs = -1;
//		private static volatile double minServiceTimeMs = Double.MAX_VALUE;
//
//		private static volatile double maxResponseTimeMs = -1;
//		private static volatile double minResponseTimeMs = Double.MAX_VALUE;

		private static AtomicLong totalRequests = new AtomicLong();
		private static AtomicLong totalFails = new AtomicLong();

		public NodeCallerLoadTest(String codeString, int load, long reqId) {
			this.codeString = codeString;
			this.load = load;
			this.reqId = reqId;
		}

		@Override
		public void run() {
//			try {
			long responseStratTime = System.nanoTime();
			NodeResponse nodeResponse = httpNodeExecutionerCaller.execute(codeString);

			if (nodeResponse != null) {
				handleResult(nodeResponse, (System.nanoTime() - responseStratTime) / 1e6);
			} else {
				totalFails.incrementAndGet();
			}
			// Thread.sleep(10);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}

		private synchronized void handleResult(NodeResponse nodeResponse, double responseTime) {
			double compileTime = (nodeResponse.compileTime - nodeResponse.startTime) / 1e6;
			double runTime = (nodeResponse.runTime - nodeResponse.startTime) / 1e6;
			double serviceTime = (nodeResponse.endTime - nodeResponse.startTime) / 1e6;

//			maxCompileTimeMs = compileTime > maxCompileTimeMs ? compileTime : maxCompileTimeMs;
//			minCompileTimeMs = compileTime < minCompileTimeMs ? compileTime : minCompileTimeMs;
//
//			maxRunTimeMs = runTime > maxRunTimeMs ? runTime : maxRunTimeMs;
//			minRunTimeMs = runTime < minRunTimeMs ? runTime : minRunTimeMs;
//
//			maxServiceTimeMs = serviceTime > maxServiceTimeMs ? serviceTime : maxServiceTimeMs;
//			minServiceTimeMs = serviceTime < minServiceTimeMs ? serviceTime : minServiceTimeMs;
//
//			maxResponseTimeMs = responseTime > maxResponseTimeMs ? responseTime : maxResponseTimeMs;
//			minResponseTimeMs = responseTime < minResponseTimeMs ? responseTime : minResponseTimeMs;

			CSVUtils.getInstance()
					.writeLine(String.format("%s,%s,%s,%s,%s,%s,%s,%s", compileTime, runTime, serviceTime,
							responseTime/* , maxCompileTimeMs, maxRunTimeMs, maxServiceTimeMs, maxResponseTimeMs */,
							load, totalFails.get(), totalRequests.incrementAndGet(), reqId));
		}

	}

	public static void main(String[] args) {
//		int[] loads = { 1, 2 };
		int[] loads = { 1, 4, 8 };
		int requestsPerClient = 400;
		long reqId = 0, requests = 0;

		CSVUtils.getInstance()
				.writeLine("compileTime,runTime,serviceTime,responseTime,load,totalFails,totalRequests,requestId");

		long startTime = System.nanoTime();

		try {
			for (int load : loads) {
				requests += requestsPerClient * load;
				ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(load);
				while (++reqId <= requests) {
					TestNodeApp.NodeCallerLoadTest task = new TestNodeApp.NodeCallerLoadTest(
							"var i=1000;while(i--){};i;", load, reqId);
					executor.execute(task);
				}
				executor.shutdown();
				executor.awaitTermination(1, TimeUnit.MINUTES);
			}
			CSVUtils.getInstance().saveFile();
			System.out.println("Total tequests: " + reqId + ", totalRunTime: " + (System.nanoTime() - startTime) / 1e9);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
