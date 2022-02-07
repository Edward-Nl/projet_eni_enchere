package fr.eni.encheresApp.bo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;

public class ArticleVendu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int no_Article;
	private String nomArticle;
	private String description;
	private Date dateDebutEncheres;
	private Date dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private String etatVente;
	private int no_Utilisateur;
	private int no_Categorie;
	private String pseudoUtilisateur;
	private String libelleCat;

	public String getLibelleCat() {
		return libelleCat;
	}

	public void setLibelleCat(String libelleCat) {
		this.libelleCat = libelleCat;
	}

	/**
	 * Constructeur vide
	 */
	public ArticleVendu() {
		super();
	}

	public ArticleVendu(int no_Article, String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, int miseAPrix, int prixVente, String pseudoUtilisateur, String libelleCat) {
		super();
		this.no_Article = no_Article;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.pseudoUtilisateur = pseudoUtilisateur;
		this.libelleCat = libelleCat;
	}

	/**
	 * Constructeur sans etat vente et no Article pour la BDD
	 * 
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param miseAPrix
	 * @param prixVente
	 * @param no_Utilisateur
	 * @param no_Categorie
	 */
	public ArticleVendu(String nomArticle, String description, Date dateDebutEncheres, Date dateFinEncheres,
			int miseAPrix, int no_Utilisateur, int no_Categorie) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.no_Utilisateur = no_Utilisateur;
		this.no_Categorie = no_Categorie;
	}

	public ArticleVendu(int no_Article, String nomArticle, Date dateDebutEncheres, Date dateFinEncheres, int miseAPrix,
			int prixVente, int no_Categorie, String pseudoUtilisateur) {
		super();
		modificationEtatVente(dateDebutEncheres, dateFinEncheres);
		this.no_Article = no_Article;
		this.nomArticle = nomArticle;
		this.dateFinEncheres = dateFinEncheres;
		this.dateDebutEncheres = dateDebutEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.no_Categorie = no_Categorie;
		this.pseudoUtilisateur = pseudoUtilisateur;
	}

	/**
	 * Constructeur sans les nn° utilisateur et categories
	 * 
	 * @param no_Article
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param miseAPrix
	 * @param prixVente
	 * @param etatVente
	 */
	public ArticleVendu(int no_Article, String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, int miseAPrix, int prixVente, String etatVente) {
		super();
		this.no_Article = no_Article;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
	}

	/**
	 * Constructeur Complet
	 * 
	 * @param no_Article
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param miseAPrix
	 * @param prixVente
	 * @param etatVente
	 * @param no_Utilisateur
	 * @param no_Categorie
	 */
	public ArticleVendu(int no_Article, String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, int miseAPrix, int prixVente, String etatVente, int no_Utilisateur,
			int no_Categorie) {
		super();
		this.no_Article = no_Article;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.no_Utilisateur = no_Utilisateur;
		this.no_Categorie = no_Categorie;
	}

	/**
	 * Constructeur sans etat de vente
	 * 
	 * @param no_Article
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param miseAPrix
	 * @param prixVente
	 * @param no_Utilisateur
	 * @param no_Categorie
	 */
	public ArticleVendu(int no_Article, String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, int miseAPrix, int prixVente, int no_Utilisateur, int no_Categorie) {
		super();
		this.no_Article = no_Article;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.no_Utilisateur = no_Utilisateur;
		this.no_Categorie = no_Categorie;
	}
	
	

	public ArticleVendu(int no_Article, String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, int miseAPrix, int no_Categorie) {
		super();
		this.no_Article = no_Article;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.no_Categorie = no_Categorie;
	}

	/* GETTER AND SETTER */

	public int getNo_Article() {
		return no_Article;
	}

	public void setNo_Article(int no_Article) {
		this.no_Article = no_Article;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(Date dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	public Date getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(Date dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public int getMiseAPrix() {
		return miseAPrix;
	}

	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	public String getEtatVente() {
		return etatVente;
	}

	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
	}

	public int getNo_Utilisateur() {
		return no_Utilisateur;
	}

	public void setNo_Utilisateur(int no_Utilisateur) {
		this.no_Utilisateur = no_Utilisateur;
	}

	public int getNo_Categorie() {
		return no_Categorie;
	}

	public void setNo_Categorie(int no_Categorie) {
		this.no_Categorie = no_Categorie;
	}

	/* METHODE TOSTRING */

	@Override
	public String toString() {
		return "ArticleVendu [no_Article=" + no_Article + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix="
				+ miseAPrix + ", prixVente=" + prixVente + ", etatVente=" + etatVente + ", no_Utilisateur="
				+ no_Utilisateur + ", no_Categorie=" + no_Categorie + "]";
	}

	public String getPseudoUtilisateur() {
		return pseudoUtilisateur;
	}

	public void setPseudoUtilisateur(String pseudoUtilisateur) {
		this.pseudoUtilisateur = pseudoUtilisateur;
	}

	public void modificationEtatVente(Date dateDebut, Date dateFin) {
		String etat = null;
		String dateNow = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		Date dateCourant = Date.valueOf(dateNow);
		if (dateCourant.before(dateDebut)) {
			etat = "NC";
		}
		if ((dateCourant.equals(dateDebut) || dateCourant.after(dateDebut)) && dateCourant.before(dateFin)) {
			etat = "EC";
		}
		System.out.println(dateDebut + "  " + dateFin + "  " + dateCourant);
		if (dateCourant.equals(dateFin) || dateCourant.after(dateFin)) {
			etat = "TE";
		}
		this.setEtatVente(etat);

	}
}
