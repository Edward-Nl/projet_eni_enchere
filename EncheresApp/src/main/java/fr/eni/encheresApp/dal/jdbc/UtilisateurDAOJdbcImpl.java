package fr.eni.encheresApp.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheresApp.bo.Utilisateur;
import fr.eni.encheresApp.dal.ConnectionProvider;
import fr.eni.encheresApp.dal.UtilisateurDAO;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {
	// TODO: gestion admin & crédits
	private static final String INSERT = "INSERT INTO UTILISATEURS(pseudo,nom,prenom,email,telephone,rue,code_postale,ville,mot_de_passe,credit,adminitrateur) VALUES (?,?,?,?,?,?,?,?,?,500,0)";
	private static final String SELECTBYMAIL = "SELECT * FROM UTILISATEURS WHERE email LIKE ? ";
	private static final String SELECTBYPSEUDO = "SELECT * FROM UTILISATEURS WHERE pseudo LIKE ? ";

	@Override
	public void insert(Utilisateur u) {
		if (u == null) {
			// TODO: Messages erreur
		}
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try (PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
				pstmt.setString(1, u.getPseudo());
				pstmt.setString(2, u.getNom());
				pstmt.setString(3, u.getPrenom());
				pstmt.setString(4, u.getEmail());
				pstmt.setString(5, u.getTelephone());
				pstmt.setString(6, u.getRue());
				pstmt.setString(7, u.getCodePostal());
				pstmt.setString(8, u.getVille());
				pstmt.setString(9, u.getMotDePasse());
				pstmt.executeUpdate();
				try (ResultSet rs = pstmt.getGeneratedKeys()) {
					if (rs.next()) {
						u.setNoUtilisateur(rs.getInt(1));
					} else {
						// TODO: message fail création utilisateur
					}
				}

			}
		} catch (SQLException e) {
			// TODO gestion messages erreur
			e.printStackTrace();
		}

	}

	@Override
	public Utilisateur selectByPseudoOrMailAndPsw(String pseudoOrMail, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean selectByMail(String mail) {
		boolean toReturn = false;
		if (mail.trim().isEmpty()) {
			// TODO: Messages erreur
		}
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try (PreparedStatement pstmt = cnx.prepareStatement(SELECTBYMAIL)) {
				pstmt.setString(1, mail);
				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						toReturn = true;
					}
				}
			}
		} catch (SQLException e) {
			// TODO gestion messages erreur
			e.printStackTrace();
		}
		return toReturn;
	}

	@Override
	public boolean selectByPseudo(String pseudo) {
		boolean toReturn = false;
		if (pseudo.trim().isEmpty()) {
			// TODO: Messages erreur
		}
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try (PreparedStatement pstmt = cnx.prepareStatement(SELECTBYPSEUDO)) {
				pstmt.setString(1, pseudo);
				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						toReturn = true;
					}
				}
			}
		} catch (SQLException e) {
			// TODO gestion messages erreur
			e.printStackTrace();
		}
		return toReturn;
	}

}
