package fr.eni.encheresApp.dal;

import fr.eni.encheresApp.BusinessException;
import fr.eni.encheresApp.bo.Enchere;

public interface EnchereDAO {

	public void insertEnchere(Enchere enchere) throws BusinessException;
	
	public Enchere selectById(int no_article) throws BusinessException;
	
	public void updateEnchere(Enchere enchere) throws BusinessException;
	
}