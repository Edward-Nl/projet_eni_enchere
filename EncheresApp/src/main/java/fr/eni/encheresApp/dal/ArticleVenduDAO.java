package fr.eni.encheresApp.dal;

import java.util.List;

import fr.eni.encheresApp.bo.ArticleVendu;

public interface ArticleVenduDAO {

	public void insertArticle(ArticleVendu article);

	public List<ArticleVendu> selectAllArticle();

	public List<ArticleVendu> selectWithCondition(String pseudo, String filtre, int current, int categorie);

	public List<ArticleVendu> selectArticleCurrent();

	public List<ArticleVendu> selectArticleCurrentWithFilterAllCat(String filtre);

	public List<ArticleVendu> selectArticleCurrentWithFilterSingleCat(String filtre, int cat);

	public ArticleVendu selectArticleById(int noArticle);

	public void updateArticle(ArticleVendu article);

	public void enchereArticle(int noArticle, int nouveauPrix);

	public void deleteArticle(int noArticle);

}
