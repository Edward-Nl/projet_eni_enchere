package fr.eni.encheresApp.bll;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerUpdateVente {
	public TimerUpdateVente() {
		
		/*
		 Script Originel

		// Creation d'un localdatetime au prochain a 5 heure du matin
		LocalTime localTime = LocalTime.of(5, 0);
		LocalDate localDate = LocalDate.now();
		LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
		// Convertion du localdatetime en date
		Date date = Date.from(localDateTime.atZone(ZoneId.of("Europe/Paris")).toInstant());

		// Tache réaliser pendant le Timer
		TimerTask tasknew = new TimerTaskPerso();
		Timer timer = new Timer();
		System.out.println(date);
		// Attend 5 heure du matin si deja passer alors on commence le timer toute les 24 heures
		timer.schedule(tasknew, date, 86400000);*/
		
		// Script personnaliser pour la démo
		
		// Creation d'un localdatetime dans la prochaine minutes
		LocalTime localTime = LocalTime.now();
		
		LocalDate localDate = LocalDate.now();
		LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
		// Convertion du localdatetime en date
		Date date = Date.from(localDateTime.atZone(ZoneId.of("Europe/Paris")).toInstant());

		// Tache réaliser pendant le Timer
		TimerTask tasknew = new TimerTaskPerso();
		Timer timer = new Timer();
		// Attend 5 heure du matin si deja passer alors on commence le timer toute les 20 secondes
		timer.schedule(tasknew, date, 20000);
		
	}

}
