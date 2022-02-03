package fr.eni.encheresApp.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheresApp.BusinessException;
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
		String userPseudoToShow = request.getParameter("userPseudo");

		if (userPseudoToShow != null) {
			Utilisateur utilisateurShow = null;
			try {
				utilisateurShow = manager.selectByPseudo(userPseudoToShow);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			if (utilisateurShow != null && utilisateurShow.getPseudo() != null) {

				utilisateurShow.setNoUtilisateur(-1);
				utilisateurShow.setMotDePasse(null);
				utilisateurShow.setCredit(-1);
				utilisateurShow.setAdministrateur(false);

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
