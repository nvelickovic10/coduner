package com.nvelickovic10.coduner.performance.boot;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.nvelickovic10.coduner.rest.model.Execution;
import com.nvelickovic10.coduner.util.CSVUtils;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

@SpringBootTest
public class TestBootApp {

	private static CSVUtils CSV_UTILS = new CSVUtils("bootLoadTestResults.csv");

	public static class BootCallerLoadTest implements Runnable {
		private HTTPBootExecutionerCaller httpBootExecutionerCaller = new HTTPBootExecutionerCaller();
		private String codeString;
		private int load;
		private final long reqId;

		private static AtomicLong totalRequests = new AtomicLong();
		private static AtomicLong totalFails = new AtomicLong();

		public BootCallerLoadTest(String codeString, int load, long reqId) {
			this.codeString = codeString;
			this.load = load;
			this.reqId = reqId;
		}

		@Override
		public void run() {
			Execution.Response bootResponse = httpBootExecutionerCaller.execute(codeString);
			double responseTime = httpBootExecutionerCaller.getResponseTime();

			if (bootResponse != null) {
				handleResult(bootResponse, responseTime);
			} else {
				totalFails.incrementAndGet();
			}
		}

		private synchronized void handleResult(Execution.Response bootResponse, double responseTime) {
			double runTime = bootResponse.getTotalRunTimeMs();
			double executionTime = bootResponse.getTotalExecutionTimeMs();
			double serviceTime = bootResponse.getTotalServiceTimeMs();

			if (bootResponse.isError()) {
				totalFails.incrementAndGet();
			}

			CSV_UTILS.writeLine(String.format("%s,%s,%s,%s,%s,%s,%s,%s", runTime, executionTime, serviceTime,
					responseTime, load, totalFails.get(), totalRequests.incrementAndGet(), reqId));
		}
	}

	public static void main(String[] args) {
		int[] loads = { 2 };
//		int[] loads = { 1, 4, 8 };
		int requestsPerClient = 50;
		long reqId = 0, requests = 0;

		CSV_UTILS.writeLine("runTime,executionTime,serviceTime,responseTime,load,totalFails,totalRequests,reqId");

		long startTime = System.nanoTime();

		try {
			for (int load : loads) {
				requests += requestsPerClient * load;
				ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(load);
				while (++reqId <= requests) {
					TestBootApp.BootCallerLoadTest task = new TestBootApp.BootCallerLoadTest(
							"var i;var fib=[];fib[0]=0;fib[1]=1;for(i=2;i<=100000;i++){fib[i]=fib[i-2]+fib[i-1];fib;}",
							load, reqId);
					executor.execute(task);
				}
				executor.shutdown();
				executor.awaitTermination(1, TimeUnit.MINUTES);
			}
			CSV_UTILS.saveFile();
			System.out.println(
					"Total tequests: " + requests + ", totalRunTime: " + (System.nanoTime() - startTime) / 1e9);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
