package fr.eni.encheresApp.ihm;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
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
import fr.eni.encheresApp.bll.EnchereManager;
import fr.eni.encheresApp.bll.RetraitManager;
import fr.eni.encheresApp.bll.UtilisateurManager;
import fr.eni.encheresApp.bo.ArticleVendu;
import fr.eni.encheresApp.bo.Enchere;
import fr.eni.encheresApp.bo.Retrait;
import fr.eni.encheresApp.bo.Utilisateur;

/**
 * Servlet implementation class ServletDetailsArticle
 */
@WebServlet("/ServletDetailsArticle")
public class ServletDetailsArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDetailsArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticlesVenduManager managerArticle = new ArticlesVenduManager();
		RetraitManager managerRetrait = new RetraitManager();
		String noArticleString = request.getParameter("noArticle");
		int noArticle = Integer.parseInt(noArticleString);
		if(noArticle != 0) {
			ArticleVendu article = null;
			Retrait retrait = null;
			try {
				article = managerArticle.selectArticleById(noArticle);
				retrait = managerRetrait.selectById(noArticle);
				article.modificationEtatVente(article.getDateDebutEncheres(), article.getDateFinEncheres());
				request.setAttribute("article", article);
				request.setAttribute("retrait", retrait);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher("/WEB-INF/views/jspDetailArticle.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		UtilisateurManager managerUtils = new UtilisateurManager();
		EnchereManager managerEnchere = new EnchereManager();
		int montantEnchere = Integer.valueOf(request.getParameter("enchere"));
		int noArticle = Integer.valueOf(request.getParameter("noArticle"));
		Date dateNow = Date.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		String pseudo = (String) session.getAttribute("utilisateurCourant");
		
		
		try {
			Utilisateur utils = managerUtils.selectByPseudo(pseudo);
			int idUtils = utils.getNoUtilisateur();
			Enchere enchere = new Enchere(idUtils,noArticle,dateNow,montantEnchere);
			managerEnchere.insert(enchere);
		} catch(BusinessException e) {
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		doGet(request, response);
	}

}
