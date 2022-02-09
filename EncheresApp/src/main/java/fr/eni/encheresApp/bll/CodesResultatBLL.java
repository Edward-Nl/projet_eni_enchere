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
	
	
	public static final int REGLE_UTILISATEURS_BLL_INSERT_ERREUR = 20100;
	public static final int REGLE_UTILISATEURS_BLL_SELECT_ID_ERREUR = 20101;
	public static final int REGLE_UTILISATEURS_BLL_SELECT_PSE_PWD_ERREUR = 20102;
	public static final int REGLE_UTILISATEURS_BLL_SELECT_ID_PWD_ERREUR = 20103;
	public static final int REGLE_UTILISATEURS_BLL_SELECT_PSE_ERREUR = 20104;
	public static final int REGLE_UTILISATEURS_BLL_SELECT_MAIL_ERREUR = 20105;
	public static final int REGLE_UTILISATEURS_BLL_UPDATE_ERREUR = 20106;
	public static final int REGLE_UTILISATEURS_BLL_DELETE_ERREUR = 20107;
	public static final int REGLE_UTILISATEURS_BLL_CAGNOTTE_UPDATE_ERREUR = 20108;
	public static final int REGLE_UTILISATEURS_BLL_PARSER_ERREUR = 20109;
	
	public static final int REGLE_RETRAIT_BLL_INSERT_ERREUR = 20120;
	public static final int REGLE_RETRAIT_BLL_SELECT_ID_ERREUR = 20121;
	public static final int REGLE_RETRAIT_BLL_UPDATE_ERREUR = 20122;
	public static final int REGLE_RETRAIT_BLL_DELETE_ERREUR = 20123;
	
	public static final int REGLE_ENCHERE_BLL_INSERT_ERREUR = 20140;
	public static final int REGLE_ENCHERE_BLL_SELECT_ID_ERREUR = 20141;
	public static final int REGLE_ENCHERE_BLL_UPDATE_ERREUR = 20142;
	
	public static final int REGLE_CAT_BLL_SELECT_ALL_ERREUR = 20160;
	
	public static final int REGLE_ARTICLE_BLL_INSERT_ERREUR = 20180;
	public static final int REGLE_ARTICLE_BLL_SELECT_ALL_ERREUR = 20181;
	public static final int REGLE_ARTICLE_BLL_SELECT_COND_ERREUR = 20182;
	public static final int REGLE_ARTICLE_BLL_SELECT_ID_ERREUR = 20183;
	public static final int REGLE_ARTICLE_BLL_UPDATE_ERREUR = 20184;
	public static final int REGLE_ARTICLE_BLL_ENCHERE_ERREUR = 20185;
	public static final int REGLE_ARTICLE_BLL_DELETE_ERREUR = 20186;
}
