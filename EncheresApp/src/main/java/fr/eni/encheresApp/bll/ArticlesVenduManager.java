package fr.eni.encheresApp.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.encheresApp.BusinessException;
import fr.eni.encheresApp.bo.ArticleVendu;
import fr.eni.encheresApp.dal.ArticleVenduDAO;
import fr.eni.encheresApp.dal.DAOFactory;

public class ArticlesVenduManager {
	private ArticleVenduDAO ArticleVenduDAO;

	public ArticlesVenduManager() {
		this.ArticleVenduDAO = DAOFactory.getArticleVenduDAO();
	}

	public void insert(ArticleVendu article) throws BusinessException {
		BusinessException businessException = new BusinessException();
		ArticleVenduControler.ArticleVenduController(article, businessException);
		if (!businessException.hasErreurs()) {
			this.ArticleVenduDAO.insertArticle(article);
		} else {
			throw businessException;
		}

	}

	public void update(ArticleVendu article) throws BusinessException {
		BusinessException businessException = new BusinessException();
		ArticleVenduControler.ArticleVenduController(article, businessException);
		if (!businessException.hasErreurs()) {
			this.ArticleVenduDAO.updateArticle(article);
		} else {
			throw businessException;
		}
	}

	public List<ArticleVendu> selectAllArticle() throws BusinessException {
		List<ArticleVendu> articles = new ArrayList<ArticleVendu>();
		articles = ArticleVenduDAO.selectAllArticle();
		return articles;
	}

	public List<ArticleVendu> selectAvecFiltre(int requete, String pseudo, String filtre, int categorie)
			throws BusinessException {
		return this.ArticleVenduDAO.selectWithCondition(requete, pseudo, filtre, categorie);
	}

	public ArticleVendu selectArticleById(int noArticle) throws BusinessException {
		ArticleVendu article = null;
		article = ArticleVenduDAO.selectArticleById(noArticle);
		return article;
	}

	public void deleteArticle(int noArticle) throws BusinessException {
		ArticleVenduDAO.deleteArticle(noArticle);
	}

	public void finVente() throws BusinessException {
		List<ArticleVendu> articles = ArticleVenduDAO.endSaleSelect();
		for (ArticleVendu article : articles) {
			ArticleVenduDAO.endSaleUpdateArticles(article.getNo_Article());
			ArticleVenduDAO.updateUserEnd(article.getNo_Article());
		}
	}

}
