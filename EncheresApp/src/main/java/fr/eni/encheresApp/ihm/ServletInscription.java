package fr.eni.encheresApp.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheresApp.bll.UtilisateurManager;
import fr.eni.encheresApp.bo.Utilisateur;

/**
 * Servlet implementation class ServletInscription
 */
@WebServlet("/ServletInscription")
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
		UtilisateurManager manager = new UtilisateurManager();

		String pseudo = request.getParameter("pseudo");
		String prenom = request.getParameter("prenom");
		String nom = request.getParameter("nom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostale = request.getParameter("codePostale");
		String ville = request.getParameter("ville");

		String mdp = request.getParameter("mdp");
		String mdpC = request.getParameter("mdpC");
		// TODO: Controlle des id unique
		boolean mdpValid = valideMdp(mdp, mdpC);
		boolean alreadyExist = manager.selectByMailOrPseudo(email, pseudo);
		System.out.println(alreadyExist);
		if (mdpValid && !alreadyExist) {
			// TODO: Crypter mot de passe changer le system crédit & amdin
			Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostale, ville,
					mdpC, 500, false);
			System.out.println(utilisateur.toString());
			System.out.println(manager.ajouterUtilisateur(utilisateur));
		}

		request.setAttribute("pseudo", pseudo);
		request.setAttribute("prenom", prenom);
		request.setAttribute("nom", nom);
		request.setAttribute("email", email);
		request.setAttribute("telephone", telephone);
		request.setAttribute("rue", rue);
		request.setAttribute("codePostale", codePostale);
		request.setAttribute("ville", ville);

		doGet(request, response);

	}

	private boolean valideMdp(String mdp, String mdpC) {
		if (mdp.trim().equals(mdpC)) {
			return true;
		}
		return false;
	}

}
