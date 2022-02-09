package fr.eni.encheresApp.bll;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class Timer {
	public Timer() {
		Thread thread = new Thread() {
			public void run() {
				ArticlesVenduManager manager = new ArticlesVenduManager();
				LocalDateTime localTime = LocalDateTime.now();
				LocalDateTime local = LocalDateTime.now().plusSeconds(10);
//				local = local.withHour(5);
//				local = local.withMinute(0);
				try {
					TimeUnit.SECONDS.sleep(Duration.between(localTime, local).getSeconds());
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				while (true) {
					try {
						manager.finVente();
						TimeUnit.SECONDS.sleep(20);
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
