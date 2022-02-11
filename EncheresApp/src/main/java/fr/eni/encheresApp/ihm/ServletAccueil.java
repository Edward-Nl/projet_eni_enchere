package fr.eni.encheresApp.ihm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheresApp.BusinessException;
import fr.eni.encheresApp.bll.ArticlesVenduManager;
import fr.eni.encheresApp.bll.CategorieManager;
import fr.eni.encheresApp.bll.EnchereManager;
import fr.eni.encheresApp.bo.ArticleVendu;
import fr.eni.encheresApp.bo.Categorie;
import fr.eni.encheresApp.bo.Enchere;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/accueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Déclaration de manager
		ArticlesVenduManager managerArticle = new ArticlesVenduManager();
		CategorieManager managerCategorie = new CategorieManager();
		EnchereManager managerEnchere = new EnchereManager();
		BusinessException businessException = new BusinessException();
		// Declaration Variable
		List<List<ArticleVendu>> articles = new ArrayList<List<ArticleVendu>>();
		List<Categorie> categories = null;
		try {
			// Récuperation des articles courant sans filtre et toutes les catégories
			if (request.getSession().getAttribute("utilisateurCourant") != null) {
				articles.add(managerArticle.selectAvecFiltre(6,
						(String) request.getSession().getAttribute("utilisateurCourant"), "", 0));
			} else {
				articles.add(managerArticle.selectAvecFiltre(0, "", "", 0));
			}
			//Recuperation des catégorie dans la base de donnée
			categories = managerCategorie.selectAll();
			//Double for dans le but de récuperer la meilleur enchere a fin de l'afficher en tant que prix en cours
			for (List<ArticleVendu> articleList : articles) {
				for (ArticleVendu article : articleList) {
					if (article != null) {
						Enchere enchere = managerEnchere.selectById(article.getNo_Article());
						if (enchere != null) {
							article.setPrixVente(enchere.getMontant_enchere());
						}
					}
				}
			}
		} catch (BusinessException e) {
			businessException.ajouterToutesErreurs(e.getListeCodesErreur());
		}
		//setup des attribut de session afin de l'affichage dans la JSP
		if (articles != null) {
			request.setAttribute("articles", articles);
		}
		if (categories != null) {
			request.setAttribute("categories", categories);
		}
		if (businessException.hasErreurs()) {
			request.setAttribute("listeCodesErreur", businessException.getListeCodesErreur());
		}
		request.setAttribute("filtreRadio", "Achats");
		request.setAttribute("filtreChkBox", Arrays.asList(true, false, false));
		request.getRequestDispatcher("/WEB-INF/views/jspAccueil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArticlesVenduManager managerArticle = new ArticlesVenduManager();
		CategorieManager managerCategorie = new CategorieManager();
		BusinessException businessException = new BusinessException();
		EnchereManager managerEnchere = new EnchereManager();

		List<List<ArticleVendu>> articles = new ArrayList<List<ArticleVendu>>();

		int categorie = Integer.parseInt(request.getParameter("catg"));
		try {
			String filtre = request.getParameter("filtre");
			if (request.getParameter("filtreRadio") != null) {
				// List de boolean servant a retranscrire les chk box checked
				List<Boolean> filtreChkBox = new ArrayList<Boolean>();
				// Switch Entre Achats et ventre filtre
				if (request.getParameter("filtreRadio").trim().equals("Achats")) {
					if (request.getParameter("chkAchat1") != null) {
						filtreChkBox.add(true);
						// "6" Requete affichant tous les article en cours qui n'appartienne pas a
						// l'utilisatuer courant
						articles.add(managerArticle.selectAvecFiltre(6,
								(String) request.getSession().getAttribute("utilisateurCourant"), filtre, categorie));
					} else {
						filtreChkBox.add(false);
						articles.add(null);
					}//"1" Enchere sur les quel j'ai deja investie
					if (request.getParameter("chkAchat2") != null) {
						filtreChkBox.add(true);
						articles.add(managerArticle.selectAvecFiltre(1,
								(String) request.getSession().getAttribute("utilisateurCourant"), filtre, categorie));
					} else {
						filtreChkBox.add(false);
						articles.add(null);
					}//"2" Enchère fini que j'ai remporter
					if (request.getParameter("chkAchat3") != null) {
						filtreChkBox.add(true);
						articles.add(managerArticle.selectAvecFiltre(2,
								(String) request.getSession().getAttribute("utilisateurCourant"), filtre, categorie));
					} else {
						filtreChkBox.add(false);
						articles.add(null);
					}
				} else {
						// "3"Mes objet en vente en cours
					if (request.getParameter("chkVente1") != null) {
						filtreChkBox.add(true);
						articles.add(managerArticle.selectAvecFiltre(3,
								(String) request.getSession().getAttribute("utilisateurCourant"), filtre, categorie));
					} else {
						filtreChkBox.add(false);
						articles.add(null);
					}// "4" Mes ventes non débutée
					if (request.getParameter("chkVente2") != null) {
						filtreChkBox.add(true);
						articles.add(managerArticle.selectAvecFiltre(4,
								(String) request.getSession().getAttribute("utilisateurCourant"), filtre, categorie));
					} else {
						filtreChkBox.add(false);
						articles.add(null);
					} // "5" Mes ventes terminer
					if (request.getParameter("chkVente3") != null) {
						filtreChkBox.add(true);
						articles.add(managerArticle.selectAvecFiltre(5,
								(String) request.getSession().getAttribute("utilisateurCourant"), filtre, categorie));
					} else {
						filtreChkBox.add(false);
						articles.add(null);
					}
				}
				// Réecriture des filtre dans les attribut afin de les reafficher dans la jsp
				request.setAttribute("filtreRadio", request.getParameter("filtreRadio"));
				request.setAttribute("filtreChkBox", filtreChkBox);
				// Si utilisateur deco alors filtreRadio == null alors on affiche tous les
				// article en cours
			} else {
				articles.add(managerArticle.selectAvecFiltre(0, null, filtre, categorie));
				request.setAttribute("filtreRadio", "Achats");
				request.setAttribute("filtreChkBox", Arrays.asList(true, false, false));
			}

			List<Categorie> categories = null;
			categories = managerCategorie.selectAll();

			if (articles != null) {
				for (List<ArticleVendu> articleList : articles) {
					if (articleList != null) {
						for (ArticleVendu article : articleList) {
							if (article != null) {
								Enchere enchere = managerEnchere.selectById(article.getNo_Article());
								if (enchere != null) {
									article.setPrixVente(enchere.getMontant_enchere());
								}
							}
						}
					}
				}
				request.setAttribute("articles", articles);
			}

			if (categories != null) {
				request.setAttribute("categories", categories);
			}

			request.setAttribute("filtre", filtre);
			request.setAttribute("catg", categorie);
		} catch (BusinessException e) {
			businessException.ajouterToutesErreurs(e.getListeCodesErreur());
		}
		if (businessException.hasErreurs()) {
			request.setAttribute("listeCodesErreur", businessException.getListeCodesErreur());
		}
		request.getRequestDispatcher("/WEB-INF/views/jspAccueil.jsp").forward(request, response);
	}

}
