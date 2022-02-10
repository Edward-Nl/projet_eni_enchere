
package fr.eni.encheresApp.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheresApp.BusinessException;
import fr.eni.encheresApp.bll.UtilisateurManager;

/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/connexion")
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
		String identifiant = request.getParameter("identifiant");
		String motDePasse = request.getParameter("motDePasse");
		BusinessException businessException = new BusinessException();
		String pseudo = null;
		try {
			pseudo = manager.connectionUtilisateur(identifiant, motDePasse);
		} catch (BusinessException e) {
			businessException.ajouterToutesErreurs(e.getListeCodesErreur());
		}
		if (pseudo != null) {
			if (request.getParameter("souvenir") != null) {
				Cookie souvenirPseudo = new Cookie("pseudo", pseudo);
				Cookie souvenirMdp = new Cookie("Mdp", motDePasse);
				souvenirPseudo.setMaxAge(3600 * 24 * 30); // Durée de vie de 30 Jours
				souvenirMdp.setMaxAge(3600 * 24 * 30); // Durée de vie de 30 Jours
				response.addCookie(souvenirPseudo);
				response.addCookie(souvenirMdp);
			}
			HttpSession sessionCourrante = request.getSession();
			sessionCourrante.setAttribute("utilisateurCourant", pseudo);
			response.sendRedirect(request.getContextPath() + "/");
		} else {
			businessException.ajouterErreur(CodesResultatIHM.PSEUDO_OU_MOT_DE_PASSE_FAUX);
			request.setAttribute("listeCodesErreur", businessException.getListeCodesErreur());
			doGet(request, response);
		}
	}
}
