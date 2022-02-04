package fr.eni.encheresApp.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheresApp.bo.ArticleVendu;
import fr.eni.encheresApp.dal.ArticleVenduDAO;
import fr.eni.encheresApp.dal.DAOFactory;

public class ArticlesVenduManager {
	private ArticleVenduDAO ArticleVenduDAO;

	public ArticlesVenduManager() {
		this.ArticleVenduDAO = DAOFactory.getArticleVenduDAO();
	}

	public void insert(ArticleVendu article) throws SQLException {
		try {
			System.out.println(article);
			this.ArticleVenduDAO.insertArticle(article);
		} catch (Exception e) {
			throw new SQLException();
		}
	}

	public List<ArticleVendu> selectAllArticle() throws SQLException {
		List<ArticleVendu> articles = new ArrayList<ArticleVendu>();
		try {
			articles = ArticleVenduDAO.selectAllArticle();
		} catch (Exception e) {
			throw new SQLException();
		}

		return articles;
	}

	public List<ArticleVendu> selectArticleEnCours() throws SQLException {
		List<ArticleVendu> articles = new ArrayList<ArticleVendu>();
		try {
			articles = ArticleVenduDAO.selectArticleCurrent();
		} catch (Exception e) {
			throw new SQLException();
		}

		return articles;
	}

	public List<ArticleVendu> selectArticleEnCoursFiltrer(String filtre, int cat) throws SQLException {
		List<ArticleVendu> articles = new ArrayList<ArticleVendu>();
		if (filtre == null || filtre.isEmpty()) {
			filtre = "%%";
		} else {
			filtre = "%" + filtre + "%";
		}
		try {
			articles = ArticleVenduDAO.selectArticleCurrentWithFilter(filtre, cat);
		} catch (Exception e) {
			throw new SQLException();
		}

		return articles;
	}

	public ArticleVendu selectArticleById(int noArticle) throws SQLException {
		ArticleVendu article = null;
		try {
			article = ArticleVenduDAO.selectArticleById(noArticle);
		} catch (Exception e) {
			throw new SQLException();
		}

		return article;
	}

	public void deleteArticle(int noArticle) {
		try {
			ArticleVenduDAO.deleteArticle(noArticle);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
