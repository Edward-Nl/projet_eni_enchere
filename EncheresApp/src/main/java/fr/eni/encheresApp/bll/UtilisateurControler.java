package fr.eni.encheresApp.bll;

import fr.eni.encheresApp.BusinessException;
import fr.eni.encheresApp.bo.Utilisateur;

public class UtilisateurControler {
	public static void controlerUtilisateur(Utilisateur utilisateur, BusinessException bussineException) {
		UtilisateurControler.validePseudo(utilisateur.getPseudo(), bussineException);
		UtilisateurControler.valideNom(utilisateur.getNom(), bussineException);
		UtilisateurControler.validePrenom(utilisateur.getPrenom(), bussineException);
		UtilisateurControler.valideEmail(utilisateur.getEmail(), bussineException);
		UtilisateurControler.valideTelephone(utilisateur.getTelephone(), bussineException);
		UtilisateurControler.valideRue(utilisateur.getRue(), bussineException);
		UtilisateurControler.valideCodePostal(utilisateur.getCodePostal(), bussineException);
		UtilisateurControler.valideVille(utilisateur.getVille(), bussineException);
		UtilisateurControler.valideMotDePasse(utilisateur.getMotDePasse(), bussineException);
	}

	public static void validePseudo(String pseudo, BusinessException businessException) {
		pseudo = pseudo.trim();
		boolean pseudoControle = pseudo.matches("(?=(^[A-Za-z][a-zA-Z0-9]+[_-]?[0-9a-zA-Z]+$))^.{5,30}$");
		if (pseudo.length() < 5 || pseudo.length() > 30) {
			pseudoControle = false;
		}
		if (!pseudoControle) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_PSEUDO_ERREUR);
		}

	}

	public static void valideNom(String nom, BusinessException businessException) {
		nom = nom.trim();
		boolean nomControle = nom.matches("(?=^([a-zA-Z]+([-\\s][a-zA-Z]+)*)$)^.{1,30}$");
		if (nom.isEmpty() || nom.length() > 30) {
			nomControle = false;
		}
		if (!nomControle) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_NOM_ERREUR);
		}

	}

	public static void validePrenom(String prenom, BusinessException businessException) {
		prenom = prenom.trim();
		boolean prenomControle = prenom.matches("(?=^([a-zA-Z]+([-\\s][a-zA-Z]+)*)$)^.{1,30}$");
		if (prenom.isEmpty() || prenom.length() > 30) {
			prenomControle = false;
		}
		if (!prenomControle) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_PRENOM_ERREUR);
		}

	}

	public static void valideEmail(String email, BusinessException businessException) {
		email = email.trim();
		boolean emailControle = email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
		if (email.isEmpty() || email.length() > 50) {
			emailControle = false;
		}
		if (!emailControle) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_EMAIL_ERREUR);
		}

	}

	public static void valideTelephone(String telephone, BusinessException businessException) {
		telephone = telephone.trim();
		// Pattern numéro francais
		boolean telephoneControle = telephone
				.matches("(?:^(?:0|\\(?\\+33\\)?\\s?|0033\\s?)[1-79](?:[\\.\\-\\s]?\\d\\d){4}$)");
		if (telephone.isEmpty() || telephone.length() > 15) {
			telephoneControle = false;
		}
		if (!telephoneControle) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_TELEPHONE_ERREUR);
		}

	}

	public static void valideRue(String rue, BusinessException businessException) {
		rue = rue.trim();
		boolean rueControle = rue.matches("(?=(^[A-Za-z][a-zA-Z0-9]+[-\\s]?[0-9a-zA-Z]+$))^.{5,30}$");
		if (rue.isEmpty()) {
			rueControle = false;
		}
		if (!rueControle) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_RUE_ERREUR);
		}

	}

	public static void valideCodePostal(String codePostal, BusinessException businessException) {
		codePostal = codePostal.trim();
		// Regex Code postal FR
		boolean codePostalControle = codePostal.matches("^(([1-95]{2}|2A|2B)[0-9]{3})$|^[971-974]$");
		if (codePostal.isEmpty() || codePostal.length() > 10) {
			codePostalControle = false;
		}
		if (!codePostalControle) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_CODE_POSTAL_ERREUR);
		}

	}

	public static void valideVille(String ville, BusinessException businessException) {
		ville = ville.trim();
		boolean villeControle = ville.matches("(?=^([a-zA-Z]+([-\\s][a-zA-Z]+)*)$)^.{1,30}$");
		if (ville.isEmpty() || ville.length() > 30) {
			villeControle = false;
		}
		if (!villeControle) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_VILLE_ERREUR);
		}

	}

	public static void valideMotDePasse(String motDePasse, BusinessException businessException) {
		motDePasse = motDePasse.trim();

		boolean motDePasseControle = motDePasse
				.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
		if (motDePasse.isEmpty()) {
			motDePasseControle = false;
		}
		if (!motDePasseControle) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_MOT_DE_PASSE_ERREUR);
		}

	}

	public static void valideCredit(int credit, BusinessException businessException) {
		boolean creditControle = true;
		if (credit < 0) {
			creditControle = false;
		}
		if (!creditControle) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_CREDIT_ERREUR);
		}

	}
}
