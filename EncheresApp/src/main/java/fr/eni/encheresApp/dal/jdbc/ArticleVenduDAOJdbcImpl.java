package fr.eni.encheresApp.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheresApp.bo.ArticleVendu;
import fr.eni.encheresApp.dal.ArticleVenduDAO;
import fr.eni.encheresApp.dal.ConnectionProvider;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {
	private static final String SELECT_ALL = "SELECT * FROM ARTICLES_VENDUS";
	private static final String SELECT_BY_ID = "SELECT * FROM ARTICLES_VENDUS WHERE no_article = ?";
	private static final String DELETE = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?";
	private static final String INSERT = "INSERT INTO ARTICLES_VENDUS(nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,no_utilisateur,no_categorie) VALUES (?,?,?,?,?,?,?)";

	@Override
	public int insertArticle(ArticleVendu article) {
		int no_article=0;
			try (Connection cnx = ConnectionProvider.getConnection()) {
				try (PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
					pstmt.setString(1, article.getNomArticle());
					pstmt.setString(2, article.getDescription());
					pstmt.setDate(3, (Date) article.getDateDebutEncheres());
					pstmt.setDate(4, (Date) article.getDateFinEncheres());
					pstmt.setInt(5, article.getMiseAPrix());
					pstmt.setInt(6, article.getNo_Utilisateur());
					pstmt.setInt(7, article.getNo_Categorie());
					pstmt.executeUpdate();
					try (ResultSet rs = pstmt.getGeneratedKeys()) {
						if (rs.next()) {
							article.setNo_Article(rs.getInt(1));
							no_article = rs.getInt(1);
						} else {
							// TODO: message ajout d'un article échoué
						}
					}

				}
			} catch (SQLException e) {
				// TODO gestion messages erreur
				e.printStackTrace();
			}
			return no_article;

	}

	@Override
	public List<ArticleVendu> selectAllArticle() {
		List<ArticleVendu> articles = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL)) {
			ArticleVendu art = null;
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					art = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"),
							rs.getString("description"), rs.getDate("date_debut_encheres"),
							rs.getDate("date_fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"),
							rs.getInt("no_utilisateur"), rs.getInt("no_categorie"));
					articles.add(art);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articles;
	}

	@Override
	public ArticleVendu selectArticleById(int noArticle) {
		ArticleVendu article = null;
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID)) {
			pstmt.setInt(1, noArticle);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					article = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"),
							rs.getString("description"), rs.getDate("date_debut_encheres"),
							rs.getDate("date_fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"),
							rs.getInt("no_utilisateur"), rs.getInt("no_categorie"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return article;
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
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(DELETE)) {
			pstmt.setInt(1, noArticle);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	/*
	private void ArticleVenduParser(ResultSet rs, ArticleVendu article) throws SQLException{
		int id;
		try {
			id = rs.getInt("no_article");
		} catch(Exception e) {
			id = -1;
		}
		article.setNo_Article(id);
		
		article.setNomArticle(rs.getString("nom_article"));
		article.setDescription(rs.getString("description"));
		article.setDateDebutEncheres(rs.getDate("date_debut_encheres"));
		article.setDateFinEncheres(rs.getDate("date_fin_encheres"));
		
	}
	
	private void utilisateurParser(ResultSet rs, Utilisateur utilisateur) throws SQLException {
        int id;
        try {
            id = rs.getInt("no_utilisateur");
        } catch (Exception e) {
            id = -1;
        }
        utilisateur.setNoUtilisateur(id);

        utilisateur.setPseudo(rs.getString("pseudo"));
        utilisateur.setNom(rs.getString("nom"));
        utilisateur.setPrenom(rs.getString("prenom"));
        utilisateur.setEmail(rs.getString("email"));
        utilisateur.setTelephone(rs.getString("telephone"));
        utilisateur.setRue(rs.getString("rue"));
        utilisateur.setCodePostal(rs.getString("code_postal"));
        utilisateur.setVille(rs.getString("ville"));

        String mdp;
        try {
            mdp = rs.getString("mot_de_passe");
        } catch (Exception e) {
            mdp = null;
        }
        utilisateur.setMotDePasse(mdp);

        int credit;
        try {
            credit = rs.getInt("credit");
        } catch (Exception e) {
            credit = -1;
        }
        utilisateur.setCredit(credit);

        boolean admin;
        try {
            admin = rs.getBoolean("administrateur");
        } catch (Exception e) {
            admin = false;
        }

        utilisateur.setAdministrateur(admin);

    }*/

}
