package fr.eni.encheresApp.bll;

import fr.eni.encheresApp.BusinessException;
import fr.eni.encheresApp.bo.Retrait;

public class RetraitControler {

	public static void retraitController(Retrait retrait, BusinessException businessException) {
		RetraitControler.valideCodePostal(retrait.getCodePostal(), businessException);
		RetraitControler.valideRue(retrait.getRue(), businessException);
		RetraitControler.valideVille(retrait.getVille(), businessException);
	}

	/* PERMET DE VALIDER LE MONTANT D'UNE ENCHERE */
	public static void valideEnchere(int prix, int enchere, int proposition, BusinessException businessException) {
		boolean prixControler = true;
		if (enchere == 0) {
			if (proposition < prix) {
				prixControler = false;
			}
		} else {
			if (proposition <= enchere) {
				prixControler = false;
			}
		}

		if (!prixControler) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_VENDU_NOUVELLE_ENCHERE);
		}
	}

	public static void valideCodePostal(String codePostal, BusinessException businessException) {
		codePostal = codePostal.trim();
		boolean codePostalControle = codePostal.matches("^(([0-9]{2}|2A|2B)[0-9]{3})$|^[971-974]$");
		if (codePostal.isEmpty() || codePostal.length() > 10) {
			codePostalControle = false;
		}
		if (!codePostalControle) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_RETRAIT_CP_ERREUR);
		}

	}

	public static void valideVille(String ville, BusinessException businessException) {
		ville = ville.trim();
		boolean villeControle = ville.matches("(?=^([a-zA-Z]+([-\\s][a-zA-Z]+)*)$)^.{1,30}$");
		if (ville.isEmpty() || ville.length() > 30) {
			villeControle = false;
		}
		if (!villeControle) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_RETRAIT_VILLE_ERREUR);
		}

	}

	public static void valideRue(String rue, BusinessException businessException) {
		rue = rue.trim();
		boolean rueControle = rue.matches("(?=(^[A-Za-z0-9]*[\\s]?[a-zA-Z]+([-\\s]?[0-9a-zA-Z]*)+$))^.{5,30}$");
		if (rue.isEmpty()) {
			rueControle = false;
		}
		if (!rueControle) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_RETRAIT_RUE_ERREUR);
		}

	}

}
