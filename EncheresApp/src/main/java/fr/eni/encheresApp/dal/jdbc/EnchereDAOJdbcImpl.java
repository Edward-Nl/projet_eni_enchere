package fr.eni.encheresApp.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheresApp.BusinessException;
import fr.eni.encheresApp.bo.Enchere;
import fr.eni.encheresApp.dal.CodeResultatDAL;
import fr.eni.encheresApp.dal.ConnectionProvider;
import fr.eni.encheresApp.dal.EnchereDAO;

public class EnchereDAOJdbcImpl implements EnchereDAO {

	private static final String INSERT = "INSERT INTO ENCHERES(no_utilisateur, no_article, date_enchere, montant_enchere) VALUES(?,?,?,?)";
	private static final String UPDATE = "UPDATE ENCHERES SET montant_enchere = ?, date_enchere = ? WHERE no_utilisateur = ? AND no_article = ?";
	private static final String SELECT_ALL = "SELECT e.no_utilisateur,no_article,date_enchere,montant_enchere,u.pseudo FROM ENCHERES as e JOIN UTILISATEURS as u on e.no_utilisateur = u.no_utilisateur WHERE no_article = ? ORDER BY montant_enchere DESC";
	private static final String SELECT_ID = "SELECT TOP (1) e.no_utilisateur,no_article,date_enchere,montant_enchere,u.pseudo FROM ENCHERES as e JOIN UTILISATEURS as u on e.no_utilisateur = u.no_utilisateur WHERE no_article = ? ORDER BY montant_enchere DESC";

	@Override
	public void insertEnchere(Enchere enchere) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(INSERT)) {
			pstmt.setInt(1, enchere.getNo_utilisateur());
			pstmt.setInt(2, enchere.getNo_article());
			pstmt.setDate(3, (Date) enchere.getDateEnchere());
			pstmt.setInt(4, enchere.getMontant_enchere());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDAL.REGLE_ENCHERE_DAL_INSERT_ERREUR);
			throw businessException;
		}
	}

	public List<Enchere> selectAll(int no_article) throws BusinessException {
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL)) {
			pstmt.setInt(1, no_article);
			Enchere enchere = null;
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					enchere = new Enchere(rs.getInt("no_utilisateur"), rs.getInt("no_article"),
							rs.getDate("date_enchere"), rs.getInt("montant_enchere"), rs.getString("pseudo"));
					listeEnchere.add(enchere);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDAL.REGLE_ENCHERE_DAL_SELECT_ID_ERREUR);
			throw businessException;
		}
		return listeEnchere;
	}

	public Enchere selectById(int noArticle) throws BusinessException {
		Enchere enchere = null;
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_ID)) {
			pstmt.setInt(1, noArticle);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					enchere = new Enchere(rs.getInt("no_utilisateur"), rs.getInt("no_article"),
							rs.getDate("date_enchere"), rs.getInt("montant_enchere"), rs.getString("pseudo"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDAL.REGLE_ENCHERE_DAL_SELECT_ID_ERREUR);
			throw businessException;
		}
		return enchere;
	}

	public void updateEnchere(Enchere enchere) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(UPDATE)) {
			pstmt.setInt(1, enchere.getMontant_enchere());
			pstmt.setDate(2, (Date) enchere.getDateEnchere());
			pstmt.setInt(3, enchere.getNo_utilisateur());
			pstmt.setInt(4, enchere.getNo_article());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDAL.REGLE_ENCHERE_DAL_UPDATE_ERREUR);
			throw businessException;
		}
	}

}
