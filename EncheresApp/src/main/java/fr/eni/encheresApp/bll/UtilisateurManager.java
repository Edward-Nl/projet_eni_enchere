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

	public boolean selectByMailOrPseudo(String mail, String pseudo) throws BusinessException {
		BusinessException businessException = new BusinessException();
		UtilisateurControler.validePseudo(pseudo, businessException);
		UtilisateurControler.valideEmail(mail, businessException);
		if (!businessException.hasErreurs()) {
			return this.utilisateurDAO.selectByMailAndPseudp(mail, pseudo);
		} else {
			throw businessException;
		}
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

	public boolean selectByIdAndPassword(int id, String password) throws BusinessException {
		BusinessException businessException = new BusinessException();
		UtilisateurControler.valideMotDePasse(password, businessException);
		if (!businessException.hasErreurs()) {
			return this.utilisateurDAO.selectByIdAndPsw(id, password);
		} else {
			throw businessException;
		}
	}

	public boolean ajouterUtilisateur(Utilisateur utilisateur) throws BusinessException {
		BusinessException businessException = new BusinessException();
		UtilisateurControler.controlerUtilisateur(utilisateur, businessException);
		utilisateur.setMotDePasse(CryptagePassword.crypteString(utilisateur.getMotDePasse()));
		System.out.println(utilisateur);
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

	public boolean updateUtilisateur(Utilisateur utilisateur) throws BusinessException {
		BusinessException businessException = new BusinessException();
		UtilisateurControler.controlerUtilisateur(utilisateur, businessException);

		if (!businessException.hasErreurs()) {
			return this.utilisateurDAO.updateUser(utilisateur);
		} else {
			throw businessException;
		}
	}

}
