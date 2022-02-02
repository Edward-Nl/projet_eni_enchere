package fr.eni.encheresApp.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheresApp.bll.UtilisateurManager;
import fr.eni.encheresApp.bo.Utilisateur;
import fr.eni.encheresApp.dal.CryptagePassword;

/**
 * Servlet implementation class ServletInscription
 */
@WebServlet("/Inscription")
public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/jspInscription.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Utilisateur utilisateur = null;
		String mdp = request.getParameter("mdp");
		String mdpC = request.getParameter("mdpC");

		boolean mdpValid = valideMdp(mdp, mdpC);

		if (mdpValid) {
			UtilisateurManager manager = new UtilisateurManager();
			String pseudo = request.getParameter("pseudo");
			String email = request.getParameter("email");
			if (!manager.selectByMailOrPseudo(email, pseudo)) {
				String prenom = request.getParameter("prenom");
				String nom = request.getParameter("nom");
				String telephone = request.getParameter("telephone");
				String rue = request.getParameter("rue");
				String codePostale = request.getParameter("codePostale");
				String ville = request.getParameter("ville");
				// TODO: changer le system crédit & amdin
				utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostale, ville,
						CryptagePassword.crypteString(mdp), 500, false);
				manager.ajouterUtilisateur(utilisateur);

			}

		}
		if (utilisateur != null) {
			request.getSession().setAttribute("utilisateurCourant", utilisateur);
			response.sendRedirect(request.getContextPath() + "/");
		} else {
			request.setAttribute("pseudo", request.getParameter("pseudo"));
			request.setAttribute("prenom", request.getParameter("prenom"));
			request.setAttribute("nom", request.getParameter("nom"));
			request.setAttribute("email", request.getParameter("email"));
			request.setAttribute("telephone", request.getParameter("telephone"));
			request.setAttribute("rue", request.getParameter("rue"));
			request.setAttribute("codePostale", request.getParameter("codePostale"));
			request.setAttribute("ville", request.getParameter("ville"));

			doGet(request, response);
		}
	}

	private boolean valideMdp(String mdp, String mdpC) {
		if (mdp.trim().equals(mdpC)) {
			return true;
		}
		return false;
	}

}
