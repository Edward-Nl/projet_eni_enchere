package fr.eni.encheresApp.bll;

import fr.eni.encheresApp.bo.Utilisateur;
import fr.eni.encheresApp.dal.DAOFactory;
import fr.eni.encheresApp.dal.UtilisateurDAO;

public class UtilisateurManager {
	private UtilisateurDAO utilisateurDAO;

	public UtilisateurManager() {
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	public boolean selectByMailOrPseudo(String mail, String pseudo) {

		if (valideInput(mail) && valideInput(pseudo)) {
			return this.utilisateurDAO.selectByMailAndPseudp(mail, pseudo);
		} else {
			return false;
		}
	}

	public boolean selectByPseudoOrMailAndPsw(String pseudoOrMail, String password) {
		boolean connect = false;
		if (valideInput(pseudoOrMail)) {
			connect = this.utilisateurDAO.selectByPseudoOrMailAndPsw(pseudoOrMail, password);
		}

		return connect;
	}
	
	public Utilisateur selectByPseudo(String pseudo) {
		return this.utilisateurDAO.selectByPseudo(pseudo);
	}

	public boolean selectByIdAndPassword(int id, String password) {
		return this.utilisateurDAO.selectByIdAndPsw(id, password);
	}

	public boolean ajouterUtilisateur(Utilisateur utilisateur) {
		if (utilisateur == null) {
			// TODO: Message erreur
		} else {
			this.utilisateurDAO.insert(utilisateur);
			return true;
		}
		return false;

	}

	public Utilisateur selectAvecId(int id) {
		return this.utilisateurDAO.selectById(id);

	}

	public boolean updateUtilisateur(Utilisateur utilisateur) {
		// TODO: Control des champ Utilisateur
		if (this.valideUtilisateur(utilisateur)) {
			return this.utilisateurDAO.updateUser(utilisateur);
		} else {
			// TODO: messages d'erreur
			return false;
		}

	}

	private boolean valideInput(String input) {
		if (!input.trim().isEmpty() && input != null) {
			return true;
		}
		return false;
	}

	// controle de tous les champs de utilisateur
	private boolean valideUtilisateur(Utilisateur utilisateur) {
		if (utilisateur.getPseudo().trim().isEmpty()) {
			// TODO: Message d'erreur
			return false;
		}
		if (utilisateur.getNom().trim().isEmpty()) {
			// TODO: Message d'erreur
			return false;
		}
		if (utilisateur.getPrenom().trim().isEmpty()) {
			// TODO: Message d'erreur
			return false;
		}
		if (utilisateur.getEmail().trim().isEmpty()) {
			// TODO: Message d'erreur
			return false;
		}
		if (utilisateur.getTelephone().trim().isEmpty()) {
			// TODO: Message d'erreur
			return false;
		}
		if (utilisateur.getRue().trim().isEmpty()) {
			// TODO: Message d'erreur
			return false;
		}
		if (utilisateur.getCodePostal().trim().isEmpty()) {
			// TODO: Message d'erreur
			return false;
		}
		if (utilisateur.getVille().trim().isEmpty()) {
			// TODO: Message d'erreur
			return false;
		}
		if (utilisateur.getMotDePasse().trim().isEmpty()) {
			// TODO: Message d'erreur
			return false;
		}
		if (utilisateur.getCredit() < 0) {
			// TODO: Message d'erreur
			return false;
		}
		return true;
	}
}
