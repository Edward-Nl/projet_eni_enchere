package fr.eni.encheresApp.ihm;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheresApp.BusinessException;
import fr.eni.encheresApp.bll.ArticlesVenduManager;
import fr.eni.encheresApp.bll.UtilisateurManager;
import fr.eni.encheresApp.bo.ArticleVendu;
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
		request.getRequestDispatcher("/WEB-INF/views/jspNouvelleVente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date dateDebut = null;
		Date dateFin = null;
		ArticleVendu article = null;
		UtilisateurManager managerUtils = new UtilisateurManager();
		ArticlesVenduManager managerArticle = new ArticlesVenduManager();
		HttpSession session = request.getSession();
		String nom = request.getParameter("nom");
		String description = request.getParameter("description");
		String catS = request.getParameter("categorie");
		int cat = Integer.parseInt(catS);
		String prixS = request.getParameter("prix");
		int prix = Integer.parseInt(prixS);
		String debut = request.getParameter("debut");
		String fin = request.getParameter("fin");
		String pseudo = (String) session.getAttribute("utilisateurCourant");
		try {
			Utilisateur utils = managerUtils.selectByPseudo(pseudo);
			int idUtils = utils.getNoUtilisateur();
			dateDebut = Date.valueOf(debut);
			dateFin = Date.valueOf(fin);
			article = new ArticleVendu(nom,description,dateDebut,dateFin,prix,idUtils,cat);
			try {
				managerArticle.insert(article);
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
