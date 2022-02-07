package fr.eni.encheresApp.dal;

import fr.eni.encheresApp.bo.Enchere;

public interface EnchereDAO {

	public void insertEnchere(Enchere enchere);
	
	public Enchere selectById(int no_article);
	
	public void updateEnchere(Enchere enchere);
	
}