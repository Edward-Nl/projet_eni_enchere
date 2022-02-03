package fr.eni.encheresApp.dal;

import fr.eni.encheresApp.bo.Utilisateur;

public interface UtilisateurDAO {

	public void insert(Utilisateur u);

	public Utilisateur selectById(int id);

	public Utilisateur selectByPseudoAndPsw(String pseudoOrMail, String password);

	public String selectByIdentifiantAndPsw(String indantifiant, String password);

	public Utilisateur selectByPseudo(String pseudo);

	public Utilisateur selectByMail(String email);

	public boolean updateUser(Utilisateur utilisateur);

	public boolean deleteUser(String pseudo);

}
