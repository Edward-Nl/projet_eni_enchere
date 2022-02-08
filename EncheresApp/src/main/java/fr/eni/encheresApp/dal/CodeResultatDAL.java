package fr.eni.encheresApp.dal;

public abstract class CodeResultatDAL {
	
	public static final int REGLE_UTILISATEURS_DAL_INSERT_ERREUR = 30000;
	public static final int REGLE_UTILISATEURS_DAL_SELECT_ID_ERREUR = 30001;
	public static final int REGLE_UTILISATEURS_DAL_SELECT_PSE_PWD_ERREUR = 30002;
	public static final int REGLE_UTILISATEURS_DAL_SELECT_ID_PWD_ERREUR = 30003;
	public static final int REGLE_UTILISATEURS_DAL_SELECT_PSE_ERREUR = 30004;
	public static final int REGLE_UTILISATEURS_DAL_SELECT_MAIL_ERREUR = 30005;
	public static final int REGLE_UTILISATEURS_DAL_UPDATE_ERREUR = 30006;
	public static final int REGLE_UTILISATEURS_DAL_DELETE_ERREUR = 30007;
	public static final int REGLE_UTILISATEURS_DAL_CAGNOTTE_UPDATE_ERREUR = 30008;
	public static final int REGLE_UTILISATEURS_DAL_PARSER_ERREUR = 30009;
	
	public static final int REGLE_RETRAIT_DAL_INSERT_ERREUR = 30020;
	public static final int REGLE_RETRAIT_DAL_SELECT_ID_ERREUR = 30021;
	public static final int REGLE_RETRAIT_DAL_UPDATE_ERREUR = 30022;
	public static final int REGLE_RETRAIT_DAL_DELETE_ERREUR = 30023;
	
	public static final int REGLE_ENCHERE_DAL_INSERT_ERREUR = 30040;
	public static final int REGLE_ENCHERE_DAL_SELECT_ID_ERREUR = 30041;
	public static final int REGLE_ENCHERE_DAL_UPDATE_ERREUR = 30042;
	
	public static final int REGLE_CAT_DAL_SELECT_ALL_ERREUR = 30060;
	
	public static final int REGLE_ARTICLE_DAL_INSERT_ERREUR = 30080;
	public static final int REGLE_ARTICLE_DAL_SELECT_ALL_ERREUR = 30081;
	public static final int REGLE_ARTICLE_DAL_SELECT_COND_ERREUR = 30082;
	public static final int REGLE_ARTICLE_DAL_SELECT_ID_ERREUR = 30083;
	public static final int REGLE_ARTICLE_DAL_UPDATE_ERREUR = 30084;
	public static final int REGLE_ARTICLE_DAL_ENCHERE_ERREUR = 30085;
	public static final int REGLE_ARTICLE_DAL_DELETE_ERREUR = 30086;
}
