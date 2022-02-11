package fr.eni.encheresApp.bll;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import fr.eni.encheresApp.BusinessException;
import fr.eni.encheresApp.bo.ArticleVendu;
import fr.eni.encheresApp.bo.Retrait;

public class ArticleVenduControler {

	public static void ArticleVenduController(ArticleVendu article, BusinessException businessException) {
		ArticleVenduControler.valideNomArticle(article.getNomArticle(), businessException);
		article.setDescription(ArticleVenduControler.valideDescription(article.getDescription(), businessException));
		ArticleVenduControler.valideDateDebut(article.getDateDebutEncheres(), businessException);
		ArticleVenduControler.valideFin(article.getDateFinEncheres(), article.getDateDebutEncheres(),
				businessException);
	}

	public static void retraitController(Retrait retrait, BusinessException businessException) {
		ArticleVenduControler.valideCodePostal(retrait.getCodePostal(), businessException);
		ArticleVenduControler.valideRue(retrait.getRue(), businessException);
		ArticleVenduControler.valideVille(retrait.getVille(), businessException);
	}

	public static void valideNomArticle(String nom, BusinessException businessException) {
		boolean nomControle = nom.matches("(?=(^[a-zA-Z0-9_\\-\\s]+$))^.{5,30}$");
		if (nom.length() < 5 || nom.length() > 30) {
			nomControle = false;
		}
		if (!nomControle) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_VENDU_NOM_ERREUR);
		}

	}

	public static String valideDescription(String description, BusinessException businessException) {
		boolean descriptionControle = true;
		description = description.replace(";", ",");
		if (description.length() < 10 || description.length() > 300) {
			descriptionControle = false;
		}
		System.out.println(descriptionControle);
		if (!descriptionControle) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_VENDU_DESCRIPTION_ERREUR);
		}
		return description;
	}

	public static void valideDateDebut(Date dDebut, BusinessException businessException) {
		boolean dateDebutControler = true;
		String dateNow = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		Date dateCourant = Date.valueOf(dateNow);
		if (dDebut.before(dateCourant)) {
			dateDebutControler = false;
		}
		if (!dateDebutControler) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_VENDU_DATE_DEBUT_ERREUR);
		}
	}

	public static void valideFin(Date dfin, Date ddebut, BusinessException businessException) {
		boolean datefinControler = true;
		if (dfin.before(ddebut)) {
			datefinControler = false;
		}
		if (!datefinControler) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_VENDU_DATE_FIN_ERREUR);
		}
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
