package fr.eni.encheresApp.ihm;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheresApp.BusinessException;
import fr.eni.encheresApp.bll.ArticlesVenduManager;
import fr.eni.encheresApp.bll.RetraitManager;
import fr.eni.encheresApp.bll.UtilisateurManager;
import fr.eni.encheresApp.bo.ArticleVendu;
import fr.eni.encheresApp.bo.Retrait;
import fr.eni.encheresApp.bo.Utilisateur;

/**
 * Servlet implementation class ServletNouvelleVente
 */
@WebServlet("/ServletNouvelleVente")
public class ServletNouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pseudo = (String) request.getSession().getAttribute("utilisateurCourant");
		Utilisateur utilisateurCourantComplet = null;
		UtilisateurManager manager = new UtilisateurManager();
		try {
			utilisateurCourantComplet = manager.selectByPseudo(pseudo);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("utilisateurCourantComplet", utilisateurCourantComplet);
		request.getRequestDispatcher("/WEB-INF/views/jspNouvelleVente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Création des variable */
		Date dateDebut, dateFin = null;
		ArticleVendu article = null;
		Retrait retrait = null;
		/* Appelle des manager*/
		UtilisateurManager managerUtils = new UtilisateurManager();
		ArticlesVenduManager managerArticle = new ArticlesVenduManager();
		RetraitManager managerRetrait = new RetraitManager();
		/* ---------- */
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		/* Récuperation des données du form */
		String nom = request.getParameter("nom");
		String description = request.getParameter("description");
		String categorieString = request.getParameter("categorie");
		String prixString = request.getParameter("prix");
		String debut = request.getParameter("debut");
		String fin = request.getParameter("fin");
		String rue = request.getParameter("rue");
		String cPostal = request.getParameter("cPostal");
		String ville = request.getParameter("ville");
		String pseudo = (String) session.getAttribute("utilisateurCourant");
		/* ----- */
		try {
			Utilisateur utils = managerUtils.selectByPseudo(pseudo);
			int idUtils = utils.getNoUtilisateur();
			dateDebut = Date.valueOf(debut);
			dateFin = Date.valueOf(fin);
			System.out.println(fin);
			int categorie = Integer.parseInt(categorieString);
			int prix = Integer.parseInt(prixString);
			article = new ArticleVendu(nom,description,dateDebut,dateFin,prix,idUtils,categorie);
			try {
				managerArticle.insert(article);
				int no_article=article.getNo_Article();
				System.out.println("article" + article.getNo_Article());
				retrait = new Retrait(no_article,rue,cPostal,ville);
				managerRetrait.insert(retrait);
				System.out.println(no_article + "ici no ARTICLE");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		doGet(request, response);
	}

}
