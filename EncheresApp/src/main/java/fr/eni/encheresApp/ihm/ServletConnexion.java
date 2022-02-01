package fr.eni.encheresApp.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheresApp.bll.UtilisateurManager;
import fr.eni.encheresApp.bo.Utilisateur;
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Cookie[] cookies = request.getCookies();
		UtilisateurManager manager = new UtilisateurManager();
		String pseudo = request.getParameter("pseudo");
		String motDePasse = request.getParameter("motDePasse");
		boolean connect = manager.selectByPseudoOrMailAndPsw(pseudo, CryptagePassword.crypteString(motDePasse));
		if (connect) {
			if(request.getParameter("souvenir")!=null) {
				Cookie souvenirPseudo = new Cookie("pseudo", pseudo);
				Cookie souvenirMdp = new Cookie("Mdp", motDePasse);
				souvenirPseudo.setMaxAge(3600*24*30); // Durée de vie de 30 Jours
				souvenirMdp.setMaxAge(3600*24*30); // Durée de vie de 30 Jours
				response.addCookie(souvenirPseudo);
				response.addCookie(souvenirMdp);
			}
			Utilisateur utilisateurCourrant = manager.selectByPseudo(pseudo);
			HttpSession sessionCourrante = request.getSession();
			sessionCourrante.setAttribute("utilisateurCourant", utilisateurCourrant);
			System.out.println(sessionCourrante);
			request.getRequestDispatcher("/ServletAccueil").forward(request, response);
			
		} else {
			request.getRequestDispatcher("/ServletConnexion").forward(request, response);
		}
	}
}
