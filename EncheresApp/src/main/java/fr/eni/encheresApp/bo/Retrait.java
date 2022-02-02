package fr.eni.encheresApp.bo;

public class Retrait {
	
	private int no_article;
	private String rue;
	private String codePostal;
	private String ville;
	
	/**
	 * Constructeur vide
	 */
	public Retrait() {
		super();
	}

	/**
	 * Constructeur sans no_article
	 * @param rue
	 * @param codePostal
	 * @param ville
	 */
	public Retrait(String rue, String codePostal, String ville) {
		super();
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	/**
	 * Constructeur avec tout les parametre
	 * @param no_article
	 * @param rue
	 * @param codePostal
	 * @param ville
	 */
	public Retrait(int no_article, String rue, String codePostal, String ville) {
		super();
		this.no_article = no_article;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}
	
	/* GETTER AND SETTER */

	public int getNo_article() {
		return no_article;
	}

	public void setNo_article(int no_article) {
		this.no_article = no_article;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	/* methode ToString */ 
	@Override
	public String toString() {
		return "Retrait [no_article=" + no_article + ", rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville
				+ "]";
	}
	
	

}
