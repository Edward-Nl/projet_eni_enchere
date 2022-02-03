package fr.eni.encheresApp.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheresApp.BusinessException;
import fr.eni.encheresApp.bll.UtilisateurControler;
import fr.eni.encheresApp.bll.UtilisateurManager;
import fr.eni.encheresApp.bo.Utilisateur;
import fr.eni.encheresApp.dal.CryptagePassword;

/**
 * Servlet implementation class ServletInscription
 */
@WebServlet("/inscription")
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
		BusinessException businessException = new BusinessException();
		Utilisateur utilisateur = null;
		String mdp = request.getParameter("mdp");
		String mdpC = request.getParameter("mdpC");

		boolean mdpValid = valideMdp(mdp, mdpC, businessException);
		if (mdpValid && !businessException.hasErreurs()) {
			UtilisateurManager manager = new UtilisateurManager();
			String pseudo = request.getParameter("pseudo");
			String email = request.getParameter("email");
			try {
				String prenom = request.getParameter("prenom");
				String nom = request.getParameter("nom");
				String telephone = request.getParameter("telephone");
				String rue = request.getParameter("rue");
				String codePostale = request.getParameter("codePostale");
				String ville = request.getParameter("ville");
				// TODO: changer le system crédit & amdin
				utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostale, ville, mdp, 500,
						false);
				manager.ajouterUtilisateur(utilisateur);
			} catch (BusinessException e) {
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			}

		}
		if (!businessException.hasErreurs() && request.getAttribute("listeCodesErreur") == null) {
			request.getSession().setAttribute("utilisateurCourant", utilisateur.getPseudo());
			response.sendRedirect(request.getContextPath() + "/");
		} else {
			if (request.getAttribute("listeCodesErreur") == null) {
				request.setAttribute("listeCodesErreur", businessException.getListeCodesErreur());
			}
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

	private boolean valideMdp(String mdp, String mdpC, BusinessException businessException) {
		if (mdp.trim().equals(mdpC)) {
			UtilisateurControler.valideMotDePasse(mdpC, businessException);
			return true;
		}
		return false;
	}

}
