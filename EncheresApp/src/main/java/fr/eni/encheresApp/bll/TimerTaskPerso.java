package fr.eni.encheresApp.bll;

import java.time.LocalDateTime;
import java.util.TimerTask;

import fr.eni.encheresApp.BusinessException;

public class TimerTaskPerso extends TimerTask {
	private ArticlesVenduManager manager = new ArticlesVenduManager();

	@Override
	public void run() {
		try {
			manager.finVente();
			System.out.println("Update du " + LocalDateTime.now());
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

}
