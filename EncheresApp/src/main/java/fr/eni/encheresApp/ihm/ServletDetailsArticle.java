package fr.eni.encheresApp.ihm;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheresApp.bll.ArticlesVenduManager;
import fr.eni.encheresApp.bll.RetraitManager;
import fr.eni.encheresApp.bo.ArticleVendu;
import fr.eni.encheresApp.bo.Retrait;

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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
