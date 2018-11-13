package com.nvelickovic10.coduner.performance.boot;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestBootApp {

	public static volatile double maxTotalServiceTimeMs = -1;
	public static volatile double minTotalServiceTimeMs = Double.MAX_VALUE;

	public static class BootCaller implements Runnable {
		private HTTPBootExecutionerCaller httpBootExecutionerCaller = new HTTPBootExecutionerCaller();
		private String codeString;

		public BootCaller(String codeString) {
			this.codeString = codeString;
		}

		@Override
		public void run() {
			BootResponse bootResponse = httpBootExecutionerCaller.execute(codeString);
			maxTotalServiceTimeMs = bootResponse.totalServiceTimeMs > maxTotalServiceTimeMs
					? bootResponse.totalServiceTimeMs
					: maxTotalServiceTimeMs;
			minTotalServiceTimeMs = bootResponse.totalServiceTimeMs < minTotalServiceTimeMs
					? bootResponse.totalServiceTimeMs
					: minTotalServiceTimeMs;
		}
	}

	public static void main(String[] args) {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2000);

		for (int i = 0; i < 4000; i++) {
			TestBootApp.BootCaller task = new TestBootApp.BootCaller("var i=1000;while(i--){};i;");
			executor.execute(task);
		}
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.MINUTES);
			System.out.println(String.format("totalServiceTimeMs: min: %s, max: %s", minTotalServiceTimeMs, maxTotalServiceTimeMs));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
