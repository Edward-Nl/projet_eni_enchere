package fr.eni.encheresApp.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fr.eni.encheresApp.bo.Enchere;
import fr.eni.encheresApp.dal.ConnectionProvider;
import fr.eni.encheresApp.dal.EnchereDAO;

public class EnchereDAOJdbcImpl implements EnchereDAO {

	private static final String INSERT = "INSERT INTO ENCHERES(no_utilisateur, no_article, date_enchere, montant_enchere) VALUES(?,?,?,?)";
	private static final String SELECT_ID = "SELECT * FROM ENCHERES WHERE no_article = ?";
	@Override
	public void insertEnchere(Enchere enchere) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try (PreparedStatement pstmt = cnx.prepareStatement(INSERT)) {
				pstmt.setInt(1, enchere.getNo_utilisateur());
				pstmt.setInt(2, enchere.getNo_article());
				pstmt.setDate(3, (Date) enchere.getDateEnchere());
				pstmt.setInt(4, enchere.getMontant_enchere());
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO gestion messages erreur
			e.printStackTrace();
		}
	}

}
