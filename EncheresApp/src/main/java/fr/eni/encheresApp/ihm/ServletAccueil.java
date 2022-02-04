package fr.eni.encheresApp.ihm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheresApp.bll.ArticlesVenduManager;
import fr.eni.encheresApp.bll.CategorieManager;
import fr.eni.encheresApp.bo.ArticleVendu;
import fr.eni.encheresApp.bo.Categorie;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/")
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
		List<ArticleVendu> listeArticle = null;
		List<Categorie> categories = null;
		try {
			listeArticle = managerArticle.selectArticleEnCours();
			categories = managerCategorie.selectAll();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (listeArticle != null) {
			request.setAttribute("articles", listeArticle);
		}
		if (categories != null) {
			request.setAttribute("categories", categories);
		}

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
		List<ArticleVendu> listeArticle = null;
		List<Categorie> categories = null;
		String filtre = "";
		int cat = 0;
		try {
			filtre = request.getParameter("filtre");
			cat = Integer.parseInt(request.getParameter("catg"));
			listeArticle = managerArticle.selectArticleEnCoursFiltrer(filtre, cat);
			categories = managerCategorie.selectAll();

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (listeArticle != null) {
			request.setAttribute("articles", listeArticle);
		}
		if (categories != null) {
			request.setAttribute("categories", categories);
		}
		request.setAttribute("filtre", filtre);
		request.setAttribute("catg", cat);

		request.getRequestDispatcher("/WEB-INF/views/jspAccueil.jsp").forward(request, response);
	}

}
