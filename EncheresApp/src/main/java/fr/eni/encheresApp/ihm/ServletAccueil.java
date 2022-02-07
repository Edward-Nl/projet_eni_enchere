package fr.eni.encheresApp.ihm;

import java.io.IOException;
import java.util.ArrayList;
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
		List<ArticleVendu> articles = null;
		List<Categorie> categories = null;
		articles = managerArticle.selectAvecFiltre(0, "", "", 0, "En cours");
		categories = managerCategorie.selectAll();
		if (articles != null) {
			request.setAttribute("articles", articles);
		}
		if (categories != null) {
			request.setAttribute("categories", categories);
		}
		request.setAttribute("filtreRadio", "Achats");

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

		List<ArticleVendu> articles = new ArrayList<ArticleVendu>();
		List<Categorie> categories = null;

		int categorie = Integer.parseInt(request.getParameter("catg"));
		System.out.println(categorie);
		String filtre = request.getParameter("filtre");

		if (request.getParameter("filtreRadio") != null) {
			List<Boolean> filtreChkBox = new ArrayList<Boolean>();
			if (request.getParameter("filtreRadio").trim().equals("Achats")) {
				if (request.getParameter("chkAchat1") != null || (request.getParameter("chkAchat1") == null
						&& request.getParameter("chkAchat2") == null && request.getParameter("chkAchat3") == null)) {
					filtreChkBox.add(true);
					articles.addAll(managerArticle.selectAvecFiltre(0, "", filtre, categorie, "En cours"));
				} else {
					filtreChkBox.add(false);
				}
				if (request.getParameter("chkAchat2") != null) {
					filtreChkBox.add(true);
					articles.addAll(managerArticle.selectAvecFiltre(1,
							(String) request.getSession().getAttribute("utilisateurCourant"), filtre, categorie,
							"En cours/Ench�rie"));
				} else {
					filtreChkBox.add(false);
				}
				if (request.getParameter("chkAchat3") != null) {
					filtreChkBox.add(true);
					articles.addAll(managerArticle.selectAvecFiltre(2,
							(String) request.getSession().getAttribute("utilisateurCourant"), filtre, categorie,
							"Ench�res Remporter"));
				} else {
					filtreChkBox.add(false);
				}
			} else {
				if (request.getParameter("chkVente1") != null) {
					filtreChkBox.add(true);
					articles.addAll(managerArticle.selectAvecFiltre(3,
							(String) request.getSession().getAttribute("utilisateurCourant"), filtre, categorie,
							"Mes ventes en cours"));
				} else {
					filtreChkBox.add(false);
				}
				if (request.getParameter("chkVente2") != null) {
					filtreChkBox.add(true);
					articles.addAll(managerArticle.selectAvecFiltre(4,
							(String) request.getSession().getAttribute("utilisateurCourant"), filtre, categorie,
							"Mes ventes non d�but�es"));
				} else {
					filtreChkBox.add(false);
				}
				if (request.getParameter("chkVente3") != null) {
					filtreChkBox.add(true);
					articles.addAll(managerArticle.selectAvecFiltre(5,
							(String) request.getSession().getAttribute("utilisateurCourant"), filtre, categorie,
							"Mes ventes terminer"));
				} else {
					filtreChkBox.add(false);
				}
			}
			request.setAttribute("filtreRadio", request.getParameter("filtreRadio"));
			request.setAttribute("filtreChkBox", filtreChkBox);
		} else {
			articles = managerArticle.selectAvecFiltre(1, "", filtre, categorie, "En cours");
		}

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
