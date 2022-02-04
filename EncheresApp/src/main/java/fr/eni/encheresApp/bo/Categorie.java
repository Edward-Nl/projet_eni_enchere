package fr.eni.encheresApp.bo;

import java.io.Serializable;

public class Categorie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int no_categorie;
	private String libelle;

	/**
	 * Constructeur vide
	 */
	public Categorie() {
		super();
	}

	/**
	 * Constructeur complet
	 * 
	 * @param no_categorie
	 * @param libelle
	 */
	public Categorie(int no_categorie, String libelle) {
		super();
		this.no_categorie = no_categorie;
		this.libelle = libelle;
	}

	/* GETTER AND SETTER */

	public int getNo_categorie() {
		return no_categorie;
	}

	public void setNo_categorie(int no_categorie) {
		this.no_categorie = no_categorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/* ToString */

	@Override
	public String toString() {
		return "Categorie [no_categorie=" + no_categorie + ", libelle=" + libelle + "]";
	}
}
