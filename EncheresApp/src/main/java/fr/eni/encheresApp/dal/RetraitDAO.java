package fr.eni.encheresApp.dal;

import fr.eni.encheresApp.BusinessException;
import fr.eni.encheresApp.bo.Retrait;

public interface RetraitDAO {

	public void insert(Retrait lieu) throws BusinessException;
	
	public Retrait selectById(int no_article) throws BusinessException;
	
	public void update(Retrait retrait) throws BusinessException;
	
	public void delete(int no_article) throws BusinessException;
}
