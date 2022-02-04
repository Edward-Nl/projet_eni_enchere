package fr.eni.encheresApp.bll;

import java.util.List;

import fr.eni.encheresApp.bo.Categorie;
import fr.eni.encheresApp.dal.CategorieDAO;
import fr.eni.encheresApp.dal.DAOFactory;

public class CategorieManager {

	private CategorieDAO categorieDAO;

	public CategorieManager() {
		this.categorieDAO = DAOFactory.getCategorieDAO();
	}

	public List<Categorie> selectAll() {
		return this.categorieDAO.selectAll();
	}
}
