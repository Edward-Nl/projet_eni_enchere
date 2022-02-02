package fr.eni.encheresApp.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheresApp.bll.UtilisateurManager;
import fr.eni.encheresApp.bo.Utilisateur;

/**
 * Servlet implementation class ServletProfils
 */
@WebServlet("/Profil")
public class ServletProfils extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UtilisateurManager manager = new UtilisateurManager();
		HttpSession session = request.getSession();
		String userIdString = request.getParameter("userID");

		if (userIdString != null) {
			Utilisateur utilisateurShow = manager.selectAvecId(Integer.parseInt(userIdString));
			if (utilisateurShow != null) {
				request.setAttribute("utilisateurShow", utilisateurShow);
			}
		}
		request.getRequestDispatcher("/WEB-INF/views/jspProfil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
