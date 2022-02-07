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

	private static final String[] SELECT_FILTRE = {
			"SELECT a.no_article ,no_categorie, nom_article, date_fin_encheres, pseudo, prix_initial,prix_vente FROM ARTICLES_VENDUS as a JOIN UTILISATEURS as u on a.no_utilisateur = u.no_utilisateur WHERE DATEDIFF(day, GETDATE(), date_fin_encheres) > 0 AND DATEDIFF(day, GETDATE(), date_debut_encheres) < 0 AND ",
			"SELECT a.no_article ,no_categorie, nom_article, date_fin_encheres, pseudo, prix_initial,prix_vente FROM ARTICLES_VENDUS as a JOIN ENCHERES as e ON a.no_article = e.no_article JOIN UTILISATEURS as u ON e.no_utilisateur = u.no_utilisateur WHERE DATEDIFF(day, GETDATE(), date_fin_encheres) > 0 AND pseudo= ? AND ",
			"SELECT a.no_article ,no_categorie, nom_article, date_fin_encheres, pseudo, prix_initial,prix_vente FROM ARTICLES_VENDUS as a JOIN ENCHERES as e ON a.no_article = e.no_article JOIN UTILISATEURS as u ON e.no_utilisateur = u.no_utilisateur WHERE DATEDIFF(day, GETDATE(), date_fin_encheres) < 0 AND pseudo= ? AND prix_vente = e.montant_enchere AND ",
			"SELECT a.no_article ,no_categorie, nom_article, date_fin_encheres, pseudo, prix_initial,prix_vente FROM ARTICLES_VENDUS as a JOIN UTILISATEURS as u on a.no_utilisateur = u.no_utilisateur WHERE DATEDIFF(day, GETDATE(), date_fin_encheres) > 0 AND DATEDIFF(day, GETDATE(), date_debut_encheres) < 0 AND pseudo= ? AND ",
			"SELECT a.no_article ,no_categorie, nom_article, date_fin_encheres, pseudo, prix_initial,prix_vente FROM ARTICLES_VENDUS as a JOIN UTILISATEURS as u on a.no_utilisateur = u.no_utilisateur WHERE DATEDIFF(day, GETDATE(), date_debut_encheres) > 0 AND pseudo= ? AND ",
			"SELECT a.no_article ,no_categorie, nom_article, date_fin_encheres, pseudo, prix_initial,prix_vente FROM ARTICLES_VENDUS as a JOIN UTILISATEURS as u on a.no_utilisateur = u.no_utilisateur WHERE DATEDIFF(day, GETDATE(), date_fin_encheres) < 0 AND pseudo= ? AND " };

	private static final String FILTER_CONDITION = "(nom_article LIKE ? OR description LIKE ?) AND ";
	private static final String CATEGORIE_CONDITION = "no_categorie = ? AND ";

	private static final String SELECT_BY_ID = "SELECT no_article,nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,pseudo,libelle FROM ARTICLES_VENDUS AS a JOIN UTILISATEURS as u on a.no_utilisateur = u.no_utilisateur JOIN CATEGORIES AS c on a.no_categorie = c.no_categorie WHERE no_article = ?";
	private static final String DELETE = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?";
	private static final String INSERT = "INSERT INTO ARTICLES_VENDUS(nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,no_utilisateur,no_categorie) VALUES (?,?,?,?,?,?,?)";

	@Override
	public void insertArticle(ArticleVendu article) {
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
					} else {
						// TODO: message ajout d'un article échoué
					}
				}

			}
		} catch (SQLException e) {
			// TODO gestion messages erreur
			e.printStackTrace();
		}

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
							rs.getString("pseudo"), rs.getString("libelle"));
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
	@Override
	public List<ArticleVendu> selectWithCondition(int requete, String pseudo, String filtre, int categorie,
			String etat) {
		StringBuilder requeteBuilder = new StringBuilder();
		requeteBuilder.append(SELECT_FILTRE[requete]);

		if (categorie != 0) {
			requeteBuilder.append(CATEGORIE_CONDITION);
		}

		if (filtre != null && !filtre.trim().isEmpty()) {
			filtre = "%" + filtre + "%";
			requeteBuilder.append(FILTER_CONDITION);
		}

		requeteBuilder = requeteBuilder.delete(requeteBuilder.length() - 4, requeteBuilder.length());

		List<ArticleVendu> articles = new ArrayList<ArticleVendu>();

		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(requeteBuilder.toString())) {

			ArticleVendu article = null;

			int i = 1;

			if (pseudo != null && !pseudo.trim().isEmpty()) {
				pstmt.setString(i, pseudo);
				i++;
			}

			if (categorie != 0) {
				pstmt.setInt(i, categorie);
				i++;
			}

			if (filtre != null && !filtre.trim().isEmpty()) {
				pstmt.setString(i, filtre);
				i++;
				pstmt.setString(i, filtre);
				i++;
			}

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					article = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"),
							rs.getDate("date_fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"),
							rs.getInt("no_categorie"), rs.getString("pseudo"), etat);
					articles.add(article);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return articles;
	}

}
