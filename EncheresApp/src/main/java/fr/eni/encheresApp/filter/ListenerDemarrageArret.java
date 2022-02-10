package fr.eni.encheresApp.filter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import fr.eni.encheresApp.bll.TimerUpdateVente;

@WebListener
public class ListenerDemarrageArret implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		new TimerUpdateVente();
		System.out.println("ici");
	}

}
