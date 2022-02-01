package fr.eni.encheresApp.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheresApp.bo.Utilisateur;
import fr.eni.encheresApp.dal.ConnectionProvider;
import fr.eni.encheresApp.dal.UtilisateurDAO;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {
	// TODO: gestion admin & cr�dits
	private static final String INSERT = "INSERT INTO UTILISATEURS(pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) VALUES (?,?,?,?,?,?,?,?,?,500,0)";
	private static final String SELECT_BY_MAIL_PSEUDO = "SELECT * FROM UTILISATEURS WHERE email = ? OR pseudo = ?";
	private static final String SELECT_BY_PSEUDO_AND_PASSW = "SELECT * FROM UTILISATEURS WHERE pseudo = ?";
	private static final String SELECT_BY_ID = "SELECT no_utilisateur,pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur FROM UTILISATEURS WHERE no_utilisateur = ?";
	private static final String SELECT_BY_ID_AND_PSW = "SELECT * FROM UTILISATEURS WHERE no_utilisateur = ? AND mot_de_passe = ?";
	private static final String UPDATE_USER = "UPDATE UTILISATEURS SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ? , rue = ?, code_postal = ?, ville = ?, mot_de_passe = ? WHERE no_utilisateur = ?";

	@Override
	public void insert(Utilisateur utilisateur) {
		if (utilisateur == null) {
			// TODO: Messages erreur
		}
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try (PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
				pstmt.setString(1, utilisateur.getPseudo());
				pstmt.setString(2, utilisateur.getNom());
				pstmt.setString(3, utilisateur.getPrenom());
				pstmt.setString(4, utilisateur.getEmail());
				pstmt.setString(5, utilisateur.getTelephone());
				pstmt.setString(6, utilisateur.getRue());
				pstmt.setString(7, utilisateur.getCodePostal());
				pstmt.setString(8, utilisateur.getVille());
				pstmt.setString(9, utilisateur.getMotDePasse());
				pstmt.executeUpdate();
				try (ResultSet rs = pstmt.getGeneratedKeys()) {
					if (rs.next()) {
						utilisateur.setNoUtilisateur(rs.getInt(1));
					} else {
						// TODO: message fail cr�ation utilisateur
					}
				}

			}
		} catch (SQLException e) {
			// TODO gestion messages erreur
			e.printStackTrace();
		}

	}

	@Override
	public boolean selectByPseudoOrMailAndPsw(String pseudoOrMail, String password) {
		boolean connect = false;
		String pseudo = pseudoOrMail;
		String pass = password;
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_PSEUDO_AND_PASSW)) {
			pstmt.setString(1, pseudo);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (!rs.next()) {
					connect = false;
				}
				if (pass.equals(rs.getString("mot_de_passe"))) {
					connect = true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connect;
	}

	@Override
	public boolean selectByMailAndPseudp(String mail, String pseudo) {
		boolean toReturn = false;
		if (mail.trim().isEmpty()) {
			// TODO: Messages erreur
		}
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try (PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_MAIL_PSEUDO)) {
				pstmt.setString(1, mail);
				pstmt.setString(2, pseudo);
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
	public Utilisateur selectById(int id) {
		Utilisateur utilisateur = new Utilisateur();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try (PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID)) {
				pstmt.setInt(1, id);
				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						utilisateurParser(rs, utilisateur);
					}
				}
			}
		} catch (SQLException e) {
			// TODO gestion messages erreur
			e.printStackTrace();
		}
		return utilisateur;
	}

	@Override
	public boolean selectByIdAndPsw(int id, String password) {
		boolean toReturn = false;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try (PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID_AND_PSW)) {
				pstmt.setInt(1, id);
				pstmt.setString(2, password);
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
	public boolean updateUser(Utilisateur utilisateur) {
		boolean toReturn = false;

		if (utilisateur == null) {
			// TODO: Messages erreur
		}
		// "UPDATE UTILISATEURS SET pseudo = ?, nom = ?, prenom = ?, email = ?,
		// telephone = ? , rue = ?, code_postal = ?, ville = ?, mot_de_passe = ? WHERE
		// no_utilisateur = ?"
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try (PreparedStatement pstmt = cnx.prepareStatement(UPDATE_USER)) {
				pstmt.setString(1, utilisateur.getPseudo());
				pstmt.setString(2, utilisateur.getNom());
				pstmt.setString(3, utilisateur.getPrenom());
				pstmt.setString(4, utilisateur.getEmail());
				pstmt.setString(5, utilisateur.getTelephone());
				pstmt.setString(6, utilisateur.getRue());
				pstmt.setString(7, utilisateur.getCodePostal());
				pstmt.setString(8, utilisateur.getVille());
				pstmt.setString(9, utilisateur.getMotDePasse());
				pstmt.setInt(10, utilisateur.getNoUtilisateur());
				if (pstmt.executeUpdate() == 1) {
					toReturn = true;
				}

			}
		} catch (SQLException e) {
			// TODO gestion messages erreur
			e.printStackTrace();
		}
		return toReturn;
	}

	private void utilisateurParser(ResultSet rs, Utilisateur utilisateur) throws SQLException {
		utilisateur.setNoUtilisateur(rs.getInt(1));
		utilisateur.setPseudo(rs.getString(2));
		utilisateur.setNom(rs.getString(3));
		utilisateur.setPrenom(rs.getString(4));
		utilisateur.setEmail(rs.getString(5));
		utilisateur.setTelephone(rs.getString(6));
		utilisateur.setRue(rs.getString(7));
		utilisateur.setCodePostal(rs.getString(8));
		utilisateur.setVille(rs.getString(9));
		utilisateur.setMotDePasse(rs.getString(10));
		utilisateur.setCredit(rs.getInt(11));
		utilisateur.setAdministrateur(rs.getBoolean(12));

	}

}
