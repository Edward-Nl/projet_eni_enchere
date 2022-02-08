package fr.eni.encheresApp.dal;

import java.util.List;

import fr.eni.encheresApp.BusinessException;
import fr.eni.encheresApp.bo.Categorie;

public interface CategorieDAO {
	public List<Categorie> selectAll() throws BusinessException;
}
