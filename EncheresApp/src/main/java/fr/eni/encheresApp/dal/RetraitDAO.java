package fr.eni.encheresApp.dal;

import fr.eni.encheresApp.bo.Retrait;

public interface RetraitDAO {

	public void insert(Retrait lieu);
	
	public Retrait selectById(int no_article);
	
	public void update(Retrait retrait);
	
	public void delete(int no_article);
}
