package fr.eni.encheresApp.bll;

import java.util.concurrent.TimeUnit;

public class Timer {
	public Timer() {
		Thread thread = new Thread() {
			public void run() {
				while (true) {
					try {
						TimeUnit.SECONDS.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Thread Running");
				}
			}
		};
		thread.start();
	}

}
