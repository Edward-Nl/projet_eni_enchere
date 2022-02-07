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
		try {
			this.RetraitDAO.insert(lieu);
		} catch(Exception e) {
			throw new SQLException();
		}
		
	}
	
	public void update(Retrait lieu) throws SQLException {
		try {
			this.RetraitDAO.update(lieu);
		} catch(Exception e) {
			throw new SQLException();
		}
		
	}
	
	public Retrait selectById(int no_article) throws SQLException{
		Retrait retrait = null;
		try {
			retrait = RetraitDAO.selectById(no_article);
		} catch (Exception e) {
			throw new SQLException();
		}

		return retrait;
	}
}
