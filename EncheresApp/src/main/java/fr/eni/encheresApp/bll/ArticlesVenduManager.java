package fr.eni.encheresApp.bll;

import java.sql.SQLException;
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

	public void insert(ArticleVendu article) throws SQLException {
		BusinessException businessException = new BusinessException();
		try {
			ArticleVenduControler.ArticleVenduController(article, businessException);
			System.out.println(businessException.getListeCodesErreur());
			if (!businessException.hasErreurs()) {
				System.out.println("ici");
				this.ArticleVenduDAO.insertArticle(article);
			} else {
				throw businessException;
			}

		} catch (Exception e) {
			throw new SQLException();
		}
	}

	public void update(ArticleVendu article) throws SQLException {
		try {
			System.out.println(article);
			this.ArticleVenduDAO.updateArticle(article);
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

	public List<ArticleVendu> selectAvecFiltre(int requete, String pseudo, String filtre, int categorie) throws BusinessException {
		return this.ArticleVenduDAO.selectWithCondition(requete, pseudo, filtre, categorie);
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
