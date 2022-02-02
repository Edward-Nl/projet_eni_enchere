package fr.eni.encheresApp.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.encheresApp.bo.ArticleVendu;
import fr.eni.encheresApp.dal.ArticleVenduDAO;
import fr.eni.encheresApp.dal.DAOFactory;

public class ArticlesVenduManager {
	private ArticleVenduDAO ArticleVenduDAO;
	
	public ArticlesVenduManager() {
		this.ArticleVenduDAO = DAOFactory.getArticleVenduDAO();
	}
	
	public List<ArticleVendu> selectAllArticle() throws SQLException{
		List<ArticleVendu> articles;
		try {
			articles = ArticleVenduDAO.selectAllArticle();
			System.out.println(articles);
		} catch(Exception e) {
			throw new SQLException();
		}
		
		return articles;
	}
}
