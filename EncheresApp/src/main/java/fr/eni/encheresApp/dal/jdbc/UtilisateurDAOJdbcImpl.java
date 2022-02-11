package fr.eni.encheresApp.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheresApp.BusinessException;
import fr.eni.encheresApp.bo.Utilisateur;
import fr.eni.encheresApp.dal.CodeResultatDAL;
import fr.eni.encheresApp.dal.ConnectionProvider;
import fr.eni.encheresApp.dal.UtilisateurDAO;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {
	// TODO: gestion admin & cr�dits
	private static final String INSERT = "INSERT INTO UTILISATEURS(pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) VALUES (?,?,?,?,?,?,?,?,?,500,0)";
	private static final String SELECT_BY_MAIL = "SELECT no_utilisateur,pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur FROM UTILISATEURS WHERE email = ?";
	private static final String SELECT_BY_PSEUDO_AND_PASSW = "SELECT no_utilisateur,pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur FROM UTILISATEURS WHERE pseudo = ? AND mot_de_passe = ?";
	private static final String SELECT_BY_ID = "SELECT pseudo,nom,prenom,email,telephone,rue,code_postal,ville,credit FROM UTILISATEURS WHERE no_utilisateur = ?";
	private static final String SELECT_BY_PSEUDO = "SELECT no_utilisateur,pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur FROM UTILISATEURS WHERE pseudo = ?";
	private static final String SELECT_CREDIT = "SELECT credit FROM UTILISATEURS WHERE pseudo = ?;";
	private static final String UPDATE_USER = "UPDATE UTILISATEURS SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ? , rue = ?, code_postal = ?, ville = ?, mot_de_passe = ? WHERE no_utilisateur = ?";
	private static final String REMOVE_USER = "DELETE FROM UTILISATEURS WHERE pseudo = ?";
	private static final String SELECT_BY_IDANTIFIANT_AND_PSW = "SELECT pseudo FROM UTILISATEURS WHERE (pseudo = ? OR  email = ?) AND  mot_de_passe = ? ";
	private static final String UPDATE_CREDIT = "UPDATE UTILISATEURS SET credit = ? WHERE no_utilisateur = ?";

	@Override
	public void insert(Utilisateur utilisateur) throws BusinessException {
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
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDAL.REGLE_UTILISATEURS_DAL_INSERT_ERREUR);
			throw businessException;
		}

	}

	@Override
	public Utilisateur selectByPseudoAndPsw(String pseudo, String password) throws BusinessException {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNoUtilisateur(-1);
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_PSEUDO_AND_PASSW)) {
			pstmt.setString(1, pseudo);
			pstmt.setString(2, password);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					utilisateurParser(rs, utilisateur);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDAL.REGLE_UTILISATEURS_DAL_SELECT_PSE_PWD_ERREUR);
			throw businessException;
		}

		return utilisateur;
	}

	public Utilisateur selectByPseudo(String pseudo) throws BusinessException {
		Utilisateur utilisateur = new Utilisateur();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try (PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_PSEUDO)) {
				pstmt.setString(1, pseudo);
				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						utilisateurParser(rs, utilisateur);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDAL.REGLE_UTILISATEURS_DAL_SELECT_PSE_ERREUR);
			throw businessException;
		}
		return utilisateur;
	}

	@Override
	public Utilisateur selectById(int id) throws BusinessException {
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
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDAL.REGLE_UTILISATEURS_DAL_SELECT_ID_ERREUR);
			throw businessException;
		}
		return utilisateur;
	}

	@Override
	public boolean updateUser(Utilisateur utilisateur) throws BusinessException {
		boolean toReturn = false;
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
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDAL.REGLE_UTILISATEURS_DAL_UPDATE_ERREUR);
			throw businessException;
		}
		return toReturn;
	}

	@Override
	public boolean deleteUser(String pseudo) throws BusinessException {
		boolean toReturn = false;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try (PreparedStatement pstmt = cnx.prepareStatement(REMOVE_USER)) {
				pstmt.setString(1, pseudo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDAL.REGLE_UTILISATEURS_DAL_DELETE_ERREUR);
			throw businessException;
		}
		return toReturn;
	}

	@Override
	public Utilisateur selectByMail(String email) throws BusinessException {
		Utilisateur utilisateur = new Utilisateur();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try (PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_MAIL)) {
				pstmt.setString(1, email);
				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						utilisateurParser(rs, utilisateur);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDAL.REGLE_UTILISATEURS_DAL_SELECT_MAIL_ERREUR);
			throw businessException;
		}
		return utilisateur;

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

	}

	@Override
	public String selectByIdentifiantAndPsw(String idantifiant, String password) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_IDANTIFIANT_AND_PSW)) {
			pstmt.setString(1, idantifiant);
			pstmt.setString(2, idantifiant);
			pstmt.setString(3, password);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getString(1);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDAL.REGLE_UTILISATEURS_DAL_SELECT_ID_PWD_ERREUR);
			throw businessException;
		}
		return null;
	}

	public void nouvelleCagnotte(int no_utilisateur, int nouvelleCagnotte) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(UPDATE_CREDIT)) {
			pstmt.setInt(1, nouvelleCagnotte);
			pstmt.setInt(2, no_utilisateur);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDAL.REGLE_UTILISATEURS_DAL_CAGNOTTE_UPDATE_ERREUR);
			throw businessException;
		}
	}

	@Override
	public int getUserCredit(String pseudo) throws BusinessException {
		int credit = 0;
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_CREDIT)) {
			pstmt.setString(1, pseudo);
			try (ResultSet resultSet = pstmt.executeQuery()) {
				if (resultSet.next()) {
					credit = resultSet.getInt(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDAL.REGLE_UTILISATEURS_DAL_CAGNOTTE_UPDATE_ERREUR);
			throw businessException;
		}
		return credit;
	}
}
