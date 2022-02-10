package fr.eni.encheresApp.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.encheresApp.BusinessException;
import fr.eni.encheresApp.bo.Enchere;
import fr.eni.encheresApp.dal.DAOFactory;
import fr.eni.encheresApp.dal.EnchereDAO;

public class EnchereManager {

	private EnchereDAO enchereDAO;

	public EnchereManager() {
		this.enchereDAO = DAOFactory.getEnchereDAO();
	}

	public void insert(Enchere enchere) throws BusinessException {
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		int no_enchere = enchere.getNo_article();
		int no_utilisateur = enchere.getNo_utilisateur();
		listeEnchere = enchereDAO.selectAll(no_enchere);
		Boolean update = false;
		for (int i = 0; i < listeEnchere.size(); i++) {
			Enchere e = listeEnchere.get(i);
			System.out.println("ici boucle : " + e);
			if (e.getNo_utilisateur() == no_utilisateur) {
				update = true;
				break;
			}
		}
		System.out.println("bool  " + update);
		if (update) {
			System.out.println("dans update" + enchere);
			this.enchereDAO.updateEnchere(enchere);
		} else {
			System.out.println("dans insert" + enchere);
			this.enchereDAO.insertEnchere(enchere);
		}
	}

	public Enchere selectById(int no_article) throws BusinessException {
		Enchere enchere = null;
		enchere = enchereDAO.selectById(no_article);
		return enchere;
	}

	public List<Enchere> selectAll(int no_article) throws BusinessException {
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		listeEnchere = enchereDAO.selectAll(no_article);
		return listeEnchere;
	}

	public void updateEnchere(Enchere enchere) throws BusinessException {
		this.enchereDAO.updateEnchere(enchere);
	}
}
