package fr.eni.encheresApp.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheresApp.bo.ArticleVendu;
import fr.eni.encheresApp.dal.ArticleVenduDAO;
import fr.eni.encheresApp.dal.ConnectionProvider;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO{
	private static final String SELECT_ALL = "SELECT * FROM ARTICLES_VENDUS";

	@Override
	public void insertArticle(ArticleVendu article) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ArticleVendu> selectAllArticle() {
		List<ArticleVendu> articles = new ArrayList<ArticleVendu>();
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL)){
			ArticleVendu art = null;
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					art = new ArticleVendu(rs.getInt("no_article"),rs.getString("nom_article"),rs.getString("description"),
							rs.getDate("date_debut_encheres"),rs.getDate("date_fin_encheres"),rs.getInt("prix_initial"),
							rs.getInt("prix_vente"),rs.getInt("no_utilisateur"),rs.getInt("no_categorie"));
					articles.add(art);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return articles;
	}

	@Override
	public ArticleVendu selectArticleById(int noArticle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateArticle(ArticleVendu article) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enchereArticle(int noArticle, int nouveauPrix) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteArticle(int noArticle) {
		// TODO Auto-generated method stub
		
	}

}
