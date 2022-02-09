package fr.eni.encheresApp.dal;

import java.util.List;

import fr.eni.encheresApp.BusinessException;
import fr.eni.encheresApp.bo.ArticleVendu;

public interface ArticleVenduDAO {

	public void insertArticle(ArticleVendu article) throws BusinessException;

	public List<ArticleVendu> selectAllArticle() throws BusinessException;

	public List<ArticleVendu> selectWithCondition(int requete, String pseudo, String filtre, int categorie)
			throws BusinessException;

	public ArticleVendu selectArticleById(int noArticle) throws BusinessException;

	public void updateArticle(ArticleVendu article) throws BusinessException;

	public void enchereArticle(int noArticle, int nouveauPrix) throws BusinessException;

	public void deleteArticle(int noArticle) throws BusinessException;

	public List<ArticleVendu> endSaleSelect() throws BusinessException;

	public void endSaleUpdateArticles(int id) throws BusinessException;

	public void updateUserEnd(int id) throws BusinessException;

}
