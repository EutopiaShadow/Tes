package com.eutopia.shadow.everyday.concurrent;


public class Share {
	
    public /*volatile*/ static int count = 0;
	
	public void increase() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized (this) {
			count++;
		}
	}
	
	public static void main(String[] args) {
		final Share share = new Share();
		for (int i = 0; i < 500; i++) {
			new Thread(new Runnable() {
				public void run() {
					share.increase();
				}
			}).start();
		}
		
		while(Thread.activeCount() > 1) {
			Thread.yield();
		}
		
		System.out.println(count);
	}
}
