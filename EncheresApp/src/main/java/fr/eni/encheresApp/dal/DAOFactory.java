package fr.eni.encheresApp.dal;

import fr.eni.encheresApp.dal.jdbc.UtilisateurDAOJdbcImpl;

public abstract class DAOFactory {
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}
}
