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

	public boolean ajouterUtilisateur(Utilisateur u) {
		if (u == null) {
			// TODO: Message erreur
		} else {
			this.utilisateurDAO.insert(u);
			return true;
		}
		return false;

	}

	private boolean valideInput(String input) {
		if (!input.trim().isEmpty() && input != null) {
			return true;
		}
		return false;
	}
}
