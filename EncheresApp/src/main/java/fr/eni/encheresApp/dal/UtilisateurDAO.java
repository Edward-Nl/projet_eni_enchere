package fr.eni.encheresApp.dal;

import fr.eni.encheresApp.BusinessException;
import fr.eni.encheresApp.bo.Utilisateur;

public interface UtilisateurDAO {

	public void insert(Utilisateur u) throws BusinessException;

	public Utilisateur selectById(int id) throws BusinessException;

	public Utilisateur selectByPseudoAndPsw(String pseudoOrMail, String password) throws BusinessException;

	public String selectByIdentifiantAndPsw(String indantifiant, String password) throws BusinessException;

	public Utilisateur selectByPseudo(String pseudo) throws BusinessException;

	public Utilisateur selectByMail(String email) throws BusinessException;

	public boolean updateUser(Utilisateur utilisateur) throws BusinessException;

	public boolean deleteUser(String pseudo) throws BusinessException;

	public void nouvelleCagnotte(int no_utilisateur, int nouvelleCagnotte) throws BusinessException;
}
