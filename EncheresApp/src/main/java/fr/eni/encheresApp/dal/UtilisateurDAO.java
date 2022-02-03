package fr.eni.encheresApp.dal;

import fr.eni.encheresApp.bo.Utilisateur;

public interface UtilisateurDAO {

	public void insert(Utilisateur u);

	public Utilisateur selectById(int id);

	public Utilisateur selectByPseudoAndPsw(String pseudoOrMail, String password);

	public Utilisateur selectByPseudo(String pseudo);

	public boolean selectByMailAndPseudp(String mail, String pseudo);

	public boolean selectByIdAndPsw(int id, String psw);

	public boolean updateUser(Utilisateur utilisateur);

	public boolean deleteUser(int id);

}
