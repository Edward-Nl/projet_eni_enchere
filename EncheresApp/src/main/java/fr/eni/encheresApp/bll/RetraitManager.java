package fr.eni.encheresApp.bll;

import java.sql.SQLException;

import fr.eni.encheresApp.bo.Retrait;
import fr.eni.encheresApp.dal.DAOFactory;
import fr.eni.encheresApp.dal.RetraitDAO;

public class RetraitManager {

	private RetraitDAO RetraitDAO;
	
	public RetraitManager() {
		this.RetraitDAO = DAOFactory.getRetraitDAO();
	}
	
	public void insert(Retrait lieu) throws SQLException {
		System.out.println(lieu);
		this.RetraitDAO.insert(lieu);
	}
}
