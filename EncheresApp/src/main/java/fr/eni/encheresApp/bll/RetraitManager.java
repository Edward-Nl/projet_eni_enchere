package fr.eni.encheresApp.bll;

import fr.eni.encheresApp.BusinessException;
import fr.eni.encheresApp.bo.Retrait;
import fr.eni.encheresApp.dal.DAOFactory;
import fr.eni.encheresApp.dal.RetraitDAO;

public class RetraitManager {

	private RetraitDAO RetraitDAO;

	public RetraitManager() {
		this.RetraitDAO = DAOFactory.getRetraitDAO();
	}

	public void insert(Retrait lieu) throws BusinessException {
		this.RetraitDAO.insert(lieu);
	}

	public void update(Retrait lieu) throws BusinessException {
		this.RetraitDAO.update(lieu);
	}

	public Retrait selectById(int no_article) throws BusinessException {
		Retrait retrait = null;
		retrait = RetraitDAO.selectById(no_article);
		return retrait;
	}

	public void delete(int no_article) throws BusinessException {
		RetraitDAO.delete(no_article);
	}
}
