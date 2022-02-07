package fr.eni.encheresApp.bo;

import java.util.Date;

public class Enchere {

	private int no_utilisateur;
	private int no_article;
	private Date dateEnchere;
	private int montant_enchere;
	private String pseudo;
	
	/**
	 * Constructeur vide
	 */
	public Enchere() {
		super();
	}
	
	/**
	 * Constructeur sans n°utilisateur et article
	 * @param dateEnchere
	 * @param montant_enchere
	 */
	public Enchere(Date dateEnchere, int montant_enchere) {
		super();
		this.dateEnchere = dateEnchere;
		this.montant_enchere = montant_enchere;
	}
	
	/**
	 * Constructeur complet
	 * @param no_utilisateur
	 * @param no_article
	 * @param dateEnchere
	 * @param montant_enchere
	 */
	public Enchere(int no_utilisateur, int no_article, Date dateEnchere, int montant_enchere) {
		super();
		this.no_utilisateur = no_utilisateur;
		this.no_article = no_article;
		this.dateEnchere = dateEnchere;
		this.montant_enchere = montant_enchere;
	}
	
	

	public Enchere(int no_utilisateur, int no_article, Date dateEnchere, int montant_enchere, String pseudo) {
		super();
		this.no_utilisateur = no_utilisateur;
		this.no_article = no_article;
		this.dateEnchere = dateEnchere;
		this.montant_enchere = montant_enchere;
		this.pseudo = pseudo;
	}

	/* GETTER AND SETTER */ 
	
	public int getNo_utilisateur() {
		return no_utilisateur;
	}

	public void setNo_utilisateur(int no_utilisateur) {
		this.no_utilisateur = no_utilisateur;
	}

	public int getNo_article() {
		return no_article;
	}

	public void setNo_article(int no_article) {
		this.no_article = no_article;
	}

	public Date getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getMontant_enchere() {
		return montant_enchere;
	}

	public void setMontant_enchere(int montant_enchere) {
		this.montant_enchere = montant_enchere;
	}
	
	
	

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	/* Methode ToString */
	@Override
	public String toString() {
		return "Enchere [no_utilisateur=" + no_utilisateur + ", no_article=" + no_article + ", dateEnchere="
				+ dateEnchere + ", montant_enchere=" + montant_enchere + "]";
	}
	
	
	
	
}
