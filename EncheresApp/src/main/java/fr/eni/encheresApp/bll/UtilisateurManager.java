package fr.eni.encheresApp.bll;

import fr.eni.encheresApp.BusinessException;
import fr.eni.encheresApp.bo.Utilisateur;
import fr.eni.encheresApp.dal.CryptagePassword;
import fr.eni.encheresApp.dal.DAOFactory;
import fr.eni.encheresApp.dal.UtilisateurDAO;

public class UtilisateurManager {
	private UtilisateurDAO utilisateurDAO;

	public UtilisateurManager() {
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	public boolean logUser(String identifiant, String password) {
		BusinessException businessException = new BusinessException();

		return false;
	}

	public Utilisateur selectByPseudoAndPsw(String pseudo, String password) throws BusinessException {
		BusinessException businessException = new BusinessException();

		UtilisateurControler.validePseudo(pseudo, businessException);
		UtilisateurControler.valideMotDePasse(password, businessException);

		if (!businessException.hasErreurs()) {
			return this.utilisateurDAO.selectByPseudoAndPsw(pseudo, CryptagePassword.crypteString(password));
		} else {
			throw businessException;
		}
	}

	public Utilisateur selectByPseudo(String pseudo) throws BusinessException {
		BusinessException businessException = new BusinessException();
		UtilisateurControler.validePseudo(pseudo, businessException);
		if (!businessException.hasErreurs()) {
			return this.utilisateurDAO.selectByPseudo(pseudo);
		} else {
			throw businessException;
		}
	}

	public Utilisateur selectByEmail(String email) throws BusinessException {
		BusinessException businessException = new BusinessException();
		UtilisateurControler.valideEmail(email, businessException);
		if (!businessException.hasErreurs()) {
			return this.utilisateurDAO.selectByMail(email);
		} else {
			throw businessException;
		}
	}

	public boolean selectByPseudoAndPswBoolean(String pseudo, String password) throws BusinessException {
		BusinessException businessException = new BusinessException();

		UtilisateurControler.validePseudo(pseudo, businessException);
		UtilisateurControler.valideMotDePasse(password, businessException);

		if (!businessException.hasErreurs()) {
			Utilisateur utilisateur = utilisateurDAO.selectByPseudoAndPsw(pseudo,
					CryptagePassword.crypteString(password));
			if (utilisateur != null && utilisateur.getNoUtilisateur() != -1) {
				return true;
			} else {
				return false;
			}
		} else {
			throw businessException;
		}
	}

	public boolean ajouterUtilisateur(Utilisateur utilisateur) throws BusinessException {
		BusinessException businessException = new BusinessException();

		UtilisateurControler.controlerUtilisateur(utilisateur, businessException);

		utilisateur.setMotDePasse(CryptagePassword.crypteString(utilisateur.getMotDePasse()));

		// Vérification email pas deja utiliser pars un autre utilisateur
		if (!businessException.hasErreurs()) {
			Utilisateur utilisateurTmp = this.selectByEmail(utilisateur.getEmail());
			if (utilisateurTmp.getEmail() != null) {
				businessException.ajouterErreur(CodesResultatBLL.EMAIL_UTILISATEURS_DEJA_UTILISER);
			}
		}

		// Vérification pseudo pas deja utiliser pars un autre utilisateur
		if (!businessException.hasErreurs()) {
			Utilisateur utilisateurTmp = this.selectByPseudo(utilisateur.getPseudo());
			if (utilisateurTmp.getPseudo() != null) {
				businessException.ajouterErreur(CodesResultatBLL.PSEUDO_UTILISATEURS_DEJA_UTILISER);
			}
		}

		if (!businessException.hasErreurs()) {
			this.utilisateurDAO.insert(utilisateur);
			return true;
		} else {
			throw businessException;
		}

	}

	public Utilisateur selectAvecId(int id) {
		return this.utilisateurDAO.selectById(id);

	}

	public boolean updateUtilisateur(Utilisateur utilisateurCourant, Utilisateur utilisateurModifier)
			throws BusinessException {
		BusinessException businessException = new BusinessException();

		UtilisateurControler.controlerUtilisateur(utilisateurModifier, businessException);

		utilisateurModifier.setMotDePasse(CryptagePassword.crypteString(utilisateurModifier.getMotDePasse()));

		// Vérification que les deux objet ne sont pas semblable
		if (!businessException.hasErreurs() && utilisateurCourant.equals(utilisateurModifier)) {
			businessException.ajouterErreur(CodesResultatBLL.TOUS_LES_CHAMPS_IDENTIQUE);
		}

		// Vérification email pas deja utiliser pars un autre utilisateur
		if (!businessException.hasErreurs() && !utilisateurCourant.getEmail().equals(utilisateurModifier.getEmail())) {
			Utilisateur utilisateurTmp = this.selectByEmail(utilisateurModifier.getEmail());
			if (utilisateurTmp.getEmail() != null) {
				businessException.ajouterErreur(CodesResultatBLL.EMAIL_UTILISATEURS_DEJA_UTILISER);
			}
		}
		// Vérification pseudo pas deja utiliser pars un autre utilisateur
		if (!businessException.hasErreurs()
				&& !utilisateurCourant.getPseudo().equals(utilisateurModifier.getPseudo())) {
			Utilisateur utilisateurTmp = this.selectByPseudo(utilisateurModifier.getPseudo());
			if (utilisateurTmp.getPseudo() != null) {
				businessException.ajouterErreur(CodesResultatBLL.PSEUDO_UTILISATEURS_DEJA_UTILISER);
			}
		}

		if (!businessException.hasErreurs()) {
			return this.utilisateurDAO.updateUser(utilisateurModifier);
		} else {
			throw businessException;
		}
	}

	public boolean supprimerUtilisateur(String pseudo) throws BusinessException {
		return this.utilisateurDAO.deleteUser(pseudo);

	}

	public String connectionUtilisateur(String idantifiant, String password) throws BusinessException {
		BusinessException businessException = new BusinessException();
		if (idantifiant.trim().isEmpty() || idantifiant == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_IDANTIFIANT_ERREUR);
		}

		if (!businessException.hasErreurs() && (idantifiant.trim().isEmpty() || idantifiant == null)) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_MOT_DE_PASSE_ERREUR);
		}

		return this.utilisateurDAO.selectByIdentifiantAndPsw(idantifiant, CryptagePassword.crypteString(password));
	}

}
