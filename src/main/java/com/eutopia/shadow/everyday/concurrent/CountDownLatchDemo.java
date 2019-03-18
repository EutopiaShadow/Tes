package com.eutopia.shadow.everyday.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * 5个人到齐了才能开会
 */
public class CountDownLatchDemo {
	
	private static CountDownLatch countDownLatch = new CountDownLatch(5);
	
	/**
	 * Boss,等待员工到齐后开会
	 */
	static class BossThread extends Thread {
		@Override
		public void run() {
			System.out.println("Boss is waiting, there are five employees...");
			try {
				countDownLatch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("all here!");
		}
	}
	
	static class EmployeeThread extends Thread {
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " is arrive...");
			countDownLatch.countDown();
		}
	}
	
	public static void main(String[] args) {
		new BossThread().start();
		for (int i = 0; i <= countDownLatch.getCount(); i++) {
			new EmployeeThread().start();
		}
	}
}