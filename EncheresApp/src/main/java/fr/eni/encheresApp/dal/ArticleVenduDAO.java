package fr.eni.encheresApp.dal;

import java.util.List;

import fr.eni.encheresApp.bo.ArticleVendu;

public interface ArticleVenduDAO {

	public void insertArticle(ArticleVendu article);

	public List<ArticleVendu> selectAllArticle();

	public List<ArticleVendu> selectArticleCurrent();

	public ArticleVendu selectArticleById(int noArticle);

	public void updateArticle(ArticleVendu article);

	public void enchereArticle(int noArticle, int nouveauPrix);

	public void deleteArticle(int noArticle);

}
