package fr.eni.encheresApp.bll;

/**
 * Les codes disponibles sont entre 20000 et 29999
 */
public abstract class CodesResultatBLL {

	public static final int REGLE_UTILISATEURS_PSEUDO_ERREUR = 20000;
	public static final int REGLE_UTILISATEURS_NOM_ERREUR = 20001;
	public static final int REGLE_UTILISATEURS_PRENOM_ERREUR = 20002;
	public static final int REGLE_UTILISATEURS_EMAIL_ERREUR = 20003;
	public static final int REGLE_UTILISATEURS_TELEPHONE_ERREUR = 20004;
	public static final int REGLE_UTILISATEURS_RUE_ERREUR = 20005;
	public static final int REGLE_UTILISATEURS_CODE_POSTAL_ERREUR = 20006;
	public static final int REGLE_UTILISATEURS_VILLE_ERREUR = 20007;
	public static final int REGLE_UTILISATEURS_MOT_DE_PASSE_ERREUR = 20008;
	public static final int REGLE_UTILISATEURS_CREDIT_ERREUR = 20009;
	public static final int REGLE_UTILISATEURS_IDANTIFIANT_ERREUR = 20010;

	public static final int EMAIL_UTILISATEURS_DEJA_UTILISER = 20011;
	public static final int PSEUDO_UTILISATEURS_DEJA_UTILISER = 20012;
	public static final int TOUS_LES_CHAMPS_IDENTIQUE = 20013;
	
	public static final int REGLE_ARTICLE_VENDU_NOM_ERREUR = 20014;
	public static final int REGLE_ARTICLE_VENDU_DESCRIPTION_ERREUR = 20015;
	public static final int REGLE_ARTICLE_VENDU_CATEGORIE_ERREUR = 20016;
	public static final int REGLE_ARTICLE_VENDU_PHOTO_ERREUR = 20017;
	public static final int REGLE_ARTICLE_VENDU_PRIX_ERREUR = 20018;
	public static final int REGLE_ARTICLE_VENDU_DATE_DEBUT_ERREUR = 20019;
	public static final int REGLE_ARTICLE_VENDU_DATE_FIN_ERREUR = 20020;
	public static final int REGLE_RETRAIT_RUE_ERREUR = 20021;
	public static final int REGLE_RETRAIT_CP_ERREUR = 20022;
	public static final int REGLE_RETRAIT_VILLE_ERREUR = 20023;
	
	public static final int REGLE_ARTICLE_VENDU_NOUVELLE_ENCHERE = 20024;
}
