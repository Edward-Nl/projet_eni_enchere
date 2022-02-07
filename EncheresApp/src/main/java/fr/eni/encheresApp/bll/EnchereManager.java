package fr.eni.encheresApp.bll;

import java.sql.SQLException;

import fr.eni.encheresApp.bo.Enchere;
import fr.eni.encheresApp.dal.DAOFactory;
import fr.eni.encheresApp.dal.EnchereDAO;

public class EnchereManager {
	
	private EnchereDAO enchereDAO;
	
	public EnchereManager() {
		this.enchereDAO= DAOFactory.getEnchereDAO();
	}

	public void insert(Enchere enchere) throws SQLException {
		try {
			System.out.println(enchere);
			this.enchereDAO.insertEnchere(enchere);
			
		} catch (Exception e) {
			throw new SQLException();
		}
	}
}
