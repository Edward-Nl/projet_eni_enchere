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
import fr.eni.encheresApp.dal.CryptagePassword;

/**
 * Servlet implementation class ServletModifierProfil
 */
@WebServlet("/ServletModifierProfil")
public class ServletModifierProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("utilisateurCourant") != null) {
			request.getRequestDispatcher("/WEB-INF/views/jspModifierProfil.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/ServletAccueil");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UtilisateurManager manager = new UtilisateurManager();
		if (request.getSession().getAttribute("utilisateurCourant") != null) {
			HttpSession session = request.getSession();
			Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurCourant");

			String mdpO = request.getParameter("mdpO").trim();

			if (manager.selectByIdAndPassword(utilisateur.getNoUtilisateur(), CryptagePassword.crypteString(mdpO))) {
				String mdp = request.getParameter("mdp").trim();
				String mdpC = request.getParameter("mdpC").trim();
				String motDePasse = null;

				if (!mdp.isEmpty() || !mdpC.isEmpty()) {
					if (mdp.equals(mdpC)) {
						motDePasse = CryptagePassword.crypteString(mdp);
						String pseudo = request.getParameter("pseudo").trim();
						String nom = request.getParameter("nom").trim();
						String prenom = request.getParameter("prenom").trim();
						String email = request.getParameter("email").trim();
						String telephone = request.getParameter("telephone").trim();
						String rue = request.getParameter("rue").trim();
						String code_postal = request.getParameter("codePostal").trim();
						String ville = request.getParameter("ville").trim();

						Utilisateur tmpUtilisateur = new Utilisateur(utilisateur.getNoUtilisateur(), pseudo, nom,
								prenom, email, telephone, rue, code_postal, ville, motDePasse, utilisateur.getCredit(),
								utilisateur.isAdministrateur());
						if (!utilisateur.equals(tmpUtilisateur)) {
							if (manager.updateUtilisateur(tmpUtilisateur)) {
								session.setAttribute("utilisateurCourant", tmpUtilisateur);
							}
						}
					}

				} else {
					motDePasse = CryptagePassword.crypteString(mdpO);
					String pseudo = request.getParameter("pseudo").trim();
					String nom = request.getParameter("nom").trim();
					String prenom = request.getParameter("prenom").trim();
					String email = request.getParameter("email").trim();
					String telephone = request.getParameter("telephone").trim();
					String rue = request.getParameter("rue").trim();
					String code_postal = request.getParameter("codePostal").trim();
					String ville = request.getParameter("ville").trim();

					Utilisateur tmpUtilisateur = new Utilisateur(utilisateur.getNoUtilisateur(), pseudo, nom, prenom,
							email, telephone, rue, code_postal, ville, motDePasse, utilisateur.getCredit(),
							utilisateur.isAdministrateur());
					if (!utilisateur.equals(tmpUtilisateur)) {
						if (manager.updateUtilisateur(tmpUtilisateur)) {
							session.setAttribute("utilisateurCourant", tmpUtilisateur);
						}
					}
				}
			}
			doGet(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/ServletAccueil");
		}

	}
}
