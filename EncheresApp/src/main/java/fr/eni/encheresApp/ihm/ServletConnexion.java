package fr.eni.encheresApp.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheresApp.bll.UtilisateurManager;
import fr.eni.encheresApp.dal.CryptagePassword;

/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/ServletConnexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/jspConnexion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UtilisateurManager manager = new UtilisateurManager();
		String pseudo = request.getParameter("pseudo");
		String motDePasse = request.getParameter("motDePasse");
		boolean connect = manager.selectByPseudoOrMailAndPsw(pseudo, CryptagePassword.crypteString(motDePasse));
		if(connect) {
			request.getRequestDispatcher("/WEB-INF/views/jspAccueil.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/views/jspConnexion.jsp").forward(request, response);
		}
		System.out.println(pseudo + motDePasse);  // a supprimer par la suite 

	}

}
