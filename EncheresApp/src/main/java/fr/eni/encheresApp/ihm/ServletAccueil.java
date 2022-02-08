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

import fr.eni.encheresApp.bll.ArticlesVenduManager;
import fr.eni.encheresApp.bll.CategorieManager;
import fr.eni.encheresApp.bo.ArticleVendu;
import fr.eni.encheresApp.bo.Categorie;

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
		ArticlesVenduManager managerArticle = new ArticlesVenduManager();
		CategorieManager managerCategorie = new CategorieManager();
		List<List<ArticleVendu>> articles = new ArrayList<List<ArticleVendu>>();
		List<Categorie> categories = null;
		articles.add(managerArticle.selectAvecFiltre(0, "", "", 0));
		categories = managerCategorie.selectAll();
		if (articles != null) {
			request.setAttribute("articles", articles);
		}
		if (categories != null) {
			request.setAttribute("categories", categories);
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

		List<List<ArticleVendu>> articles = new ArrayList<List<ArticleVendu>>();

		int categorie = Integer.parseInt(request.getParameter("catg"));

		String filtre = request.getParameter("filtre");

		if (request.getParameter("filtreRadio") != null) {
			List<Boolean> filtreChkBox = new ArrayList<Boolean>();
			if (request.getParameter("filtreRadio").trim().equals("Achats")) {
				if (request.getParameter("chkAchat1") != null) {
					filtreChkBox.add(true);
					articles.add(managerArticle.selectAvecFiltre(0, "", filtre, categorie));
				} else {
					filtreChkBox.add(false);
					articles.add(null);
				}
				if (request.getParameter("chkAchat2") != null) {
					filtreChkBox.add(true);
					articles.add(managerArticle.selectAvecFiltre(1,
							(String) request.getSession().getAttribute("utilisateurCourant"), filtre, categorie));
				} else {
					filtreChkBox.add(false);
					articles.add(null);
				}
				if (request.getParameter("chkAchat3") != null) {
					filtreChkBox.add(true);
					articles.add(managerArticle.selectAvecFiltre(2,
							(String) request.getSession().getAttribute("utilisateurCourant"), filtre, categorie));
				} else {
					filtreChkBox.add(false);
					articles.add(null);
				}
			} else {
				if (request.getParameter("chkVente1") != null) {
					filtreChkBox.add(true);
					articles.add(managerArticle.selectAvecFiltre(3,
							(String) request.getSession().getAttribute("utilisateurCourant"), filtre, categorie));
				} else {
					filtreChkBox.add(false);
					articles.add(null);
				}
				if (request.getParameter("chkVente2") != null) {
					filtreChkBox.add(true);
					articles.add(managerArticle.selectAvecFiltre(4,
							(String) request.getSession().getAttribute("utilisateurCourant"), filtre, categorie));
				} else {
					filtreChkBox.add(false);
					articles.add(null);
				}
				if (request.getParameter("chkVente3") != null) {
					filtreChkBox.add(true);
					articles.add(managerArticle.selectAvecFiltre(5,
							(String) request.getSession().getAttribute("utilisateurCourant"), filtre, categorie));
				} else {
					filtreChkBox.add(false);
					articles.add(null);
				}
			}
			request.setAttribute("filtreRadio", request.getParameter("filtreRadio"));
			request.setAttribute("filtreChkBox", filtreChkBox);
		} else {
			articles.add(managerArticle.selectAvecFiltre(1, "", filtre, categorie));
		}

		List<Categorie> categories = null;
		categories = managerCategorie.selectAll();

		if (articles != null) {
			request.setAttribute("articles", articles);
		}

		if (categories != null) {
			request.setAttribute("categories", categories);
		}

		request.setAttribute("filtre", filtre);
		request.setAttribute("catg", categorie);

		request.getRequestDispatcher("/WEB-INF/views/jspAccueil.jsp").forward(request, response);
	}

}
