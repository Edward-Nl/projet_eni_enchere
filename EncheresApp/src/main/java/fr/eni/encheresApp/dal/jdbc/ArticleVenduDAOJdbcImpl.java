package fr.eni.encheresApp.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheresApp.BusinessException;
import fr.eni.encheresApp.bo.ArticleVendu;
import fr.eni.encheresApp.dal.ArticleVenduDAO;
import fr.eni.encheresApp.dal.CodeResultatDAL;
import fr.eni.encheresApp.dal.ConnectionProvider;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {
	private static final String SELECT_ALL = "SELECT * FROM ARTICLES_VENDUS";

	private static final String SELECT_END = "SELECT * FROM ARTICLES_VENDUS WHERE DATEDIFF(day,date_fin_encheres, GETDATE()) > 0 AND  prix_vente IS NULL";
	private static final String UPDATE_END_ARTICLES = "UPDATE ARTICLES_VENDUS SET prix_vente = ISNULL((SELECT TOP 1 montant_enchere FROM ENCHERES AS E JOIN ARTICLES_VENDUS AS A ON e.no_article = a.no_article WHERE e.no_article = ? AND DATEDIFF(day,date_fin_encheres,date_enchere) < 0 ORDER BY montant_enchere DESC),0) WHERE no_article = ?;";
	private static final String UPDATE_END_USER = "UPDATE UTILISATEURS SET credit = credit + ISNULL((SELECT TOP 1 montant_enchere FROM ENCHERES AS E JOIN ARTICLES_VENDUS AS A ON e.no_article = a.no_article WHERE e.no_article = ? AND DATEDIFF(day,date_fin_encheres,date_enchere) < 0 ORDER BY montant_enchere DESC),0) WHERE no_utilisateur = (SELECT no_utilisateur FROM ARTICLES_VENDUS WHERE no_article = ?);";

	private static final String[] SELECT_FILTRE = {
			"SELECT a.no_article ,no_categorie, nom_article, date_debut_encheres, date_fin_encheres, pseudo, prix_initial,prix_vente FROM ARTICLES_VENDUS as a JOIN UTILISATEURS as u on a.no_utilisateur = u.no_utilisateur WHERE DATEDIFF(day, GETDATE(), date_fin_encheres) >= 0 AND DATEDIFF(day, GETDATE(), date_debut_encheres) <= 0 AND ",
			"SELECT a.no_article ,no_categorie, nom_article, date_debut_encheres, date_fin_encheres, ue.pseudo, prix_initial,prix_vente FROM ARTICLES_VENDUS as a JOIN UTILISATEURS as ue ON a.no_utilisateur = ue.no_utilisateur JOIN ENCHERES as e ON a.no_article = e.no_article JOIN UTILISATEURS as u ON e.no_utilisateur = u.no_utilisateur WHERE DATEDIFF(day, GETDATE(), date_fin_encheres) >= 0 AND DATEDIFF(day, GETDATE(), date_debut_encheres) <= 0 AND u.pseudo= ? AND ",
			"SELECT a.no_article ,no_categorie, nom_article, date_debut_encheres, date_fin_encheres, ue.pseudo, prix_initial,prix_vente FROM ARTICLES_VENDUS as a JOIN UTILISATEURS as ue ON a.no_utilisateur = ue.no_utilisateur JOIN ENCHERES as e ON a.no_article = e.no_article JOIN UTILISATEURS as u ON e.no_utilisateur = u.no_utilisateur WHERE DATEDIFF(day, GETDATE(), date_fin_encheres) < 0 AND u.pseudo= ? AND prix_vente = e.montant_enchere AND ",
			"SELECT a.no_article ,no_categorie, nom_article, date_debut_encheres, date_fin_encheres, pseudo, prix_initial,prix_vente FROM ARTICLES_VENDUS as a JOIN UTILISATEURS as u on a.no_utilisateur = u.no_utilisateur WHERE DATEDIFF(day, GETDATE(), date_fin_encheres) >= 0 AND DATEDIFF(day, GETDATE(), date_debut_encheres) <= 0 AND pseudo= ? AND ",
			"SELECT a.no_article ,no_categorie, nom_article, date_debut_encheres, date_fin_encheres, pseudo, prix_initial,prix_vente FROM ARTICLES_VENDUS as a JOIN UTILISATEURS as u on a.no_utilisateur = u.no_utilisateur WHERE DATEDIFF(day, GETDATE(), date_debut_encheres) > 0 AND pseudo= ? AND ",
			"SELECT a.no_article ,no_categorie, nom_article, date_debut_encheres, date_fin_encheres, pseudo, prix_initial,prix_vente FROM ARTICLES_VENDUS as a JOIN UTILISATEURS as u on a.no_utilisateur = u.no_utilisateur WHERE DATEDIFF(day, GETDATE(), date_fin_encheres) < 0 AND pseudo= ? AND ",
			"SELECT a.no_article ,no_categorie, nom_article, date_debut_encheres, date_fin_encheres, pseudo, prix_initial,prix_vente FROM ARTICLES_VENDUS as a JOIN UTILISATEURS as u on a.no_utilisateur = u.no_utilisateur WHERE DATEDIFF(day, GETDATE(), date_fin_encheres) >= 0 AND DATEDIFF(day, GETDATE(), date_debut_encheres) <= 0 AND pseudo != ? AND " };

	private static final String FILTER_CONDITION = "(nom_article LIKE ? OR description LIKE ?) AND ";
	private static final String CATEGORIE_CONDITION = "no_categorie = ? AND ";

	private static final String SELECT_BY_ID = "SELECT no_article,nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,pseudo,libelle FROM ARTICLES_VENDUS AS a JOIN UTILISATEURS as u on a.no_utilisateur = u.no_utilisateur JOIN CATEGORIES AS c on a.no_categorie = c.no_categorie WHERE no_article = ?";
	private static final String DELETE = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?";
	private static final String INSERT = "INSERT INTO ARTICLES_VENDUS(nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,no_utilisateur,no_categorie) VALUES (?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE ARTICLES_VENDUS SET nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, no_categorie = ? WHERE no_article = ?";

	@Override
	public void insertArticle(ArticleVendu article) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
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
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDAL.REGLE_ARTICLE_DAL_INSERT_ERREUR);
			throw businessException;
		}

	}

	@Override
	public List<ArticleVendu> selectAllArticle() throws BusinessException {
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
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDAL.REGLE_ARTICLE_DAL_SELECT_ALL_ERREUR);
			throw businessException;
		}
		return articles;
	}

	@Override
	public ArticleVendu selectArticleById(int noArticle) throws BusinessException {
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
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDAL.REGLE_ARTICLE_DAL_SELECT_ID_ERREUR);
			throw businessException;
		}
		return article;
	}

	@Override
	public void updateArticle(ArticleVendu article) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(UPDATE)) {
			pstmt.setString(1, article.getNomArticle());
			pstmt.setString(2, article.getDescription());
			pstmt.setDate(3, (Date) article.getDateDebutEncheres());
			pstmt.setDate(4, (Date) article.getDateFinEncheres());
			pstmt.setInt(5, article.getMiseAPrix());
			pstmt.setInt(6, article.getNo_Categorie());
			pstmt.setInt(7, article.getNo_Article());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDAL.REGLE_ARTICLE_DAL_UPDATE_ERREUR);
			throw businessException;
		}

	}

	@Override
	public void deleteArticle(int noArticle) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(DELETE)) {
			pstmt.setInt(1, noArticle);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDAL.REGLE_ARTICLE_DAL_DELETE_ERREUR);
			throw businessException;
		}

	}

	@Override
	public List<ArticleVendu> selectWithCondition(int requete, String pseudo, String filtre, int categorie)
			throws BusinessException {
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
							rs.getDate("date_debut_encheres"), rs.getDate("date_fin_encheres"),
							rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getInt("no_categorie"),
							rs.getString("pseudo"));
					articles.add(article);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDAL.REGLE_ARTICLE_DAL_SELECT_COND_ERREUR);
			throw businessException;
		}

		return articles;
	}

	@Override
	public List<ArticleVendu> endSaleSelect() throws BusinessException {
		List<ArticleVendu> articles = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_END)) {
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
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDAL.REGLE_ARTICLE_DAL_SELECT_ALL_ERREUR);
			throw businessException;
		}
		return articles;
	}

	@Override
	public void endSaleUpdateArticles(int id) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(UPDATE_END_ARTICLES)) {
			pstmt.setInt(1, id);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDAL.REGLE_ARTICLE_DAL_SELECT_ALL_ERREUR);
			throw businessException;
		}

	}

	@Override
	public void updateUserEnd(int id) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(UPDATE_END_USER)) {
			pstmt.setInt(1, id);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDAL.REGLE_UTILISATEURS_DAL_CAGNOTTE_UPDATE_ERREUR);
			throw businessException;
		}
	}

}
