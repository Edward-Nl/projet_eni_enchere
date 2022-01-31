package fr.eni.encheresApp.dal;

import fr.eni.encheresApp.bo.Utilisateur;

public interface UtilisateurDAO {

	public void insert(Utilisateur u);

	public Utilisateur selectByPseudoOrMailAndPsw(String pseudoOrMail, String password);

	public boolean selectByMailAndPseudp(String mail, String pseudo);

}
