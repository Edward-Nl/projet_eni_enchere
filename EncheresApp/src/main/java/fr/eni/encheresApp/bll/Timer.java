package fr.eni.encheresApp.bll;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import fr.eni.encheresApp.BusinessException;

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
					while (true) {
						System.out.println("Update du " + LocalDateTime.now());
						manager.finVente();
						TimeUnit.SECONDS.sleep(20);
					}
				} catch (InterruptedException e1) {
					System.out.println("Erreur Thread Sleep ");
					e1.printStackTrace();
				} catch (BusinessException e) {
					System.out.println("Erreur Thread BLL ");
					e.printStackTrace();
				}
			}
		};
		thread.start();
	}

}
