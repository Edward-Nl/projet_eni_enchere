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
@WebServlet("/Profil/Modifier")
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
			if (request.getSession().getAttribute("utilisateurModifier") != null) {
				request.getSession().removeAttribute("utilisateurModifier");
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UtilisateurManager manager = new UtilisateurManager();
		HttpSession session = request.getSession();
		if (session.getAttribute("utilisateurCourant") != null) {
			Utilisateur utilisateurCourant = (Utilisateur) session.getAttribute("utilisateurCourant");

			String mdpO = request.getParameter("mdpO").trim();

			if (manager.selectByIdAndPassword(utilisateurCourant.getNoUtilisateur(),
					CryptagePassword.crypteString(mdpO))) {
				String mdp = request.getParameter("mdp").trim();
				String mdpC = request.getParameter("mdpC").trim();

				if (!mdp.isEmpty() || !mdpC.isEmpty()) {
					Utilisateur utilisateurModifier = null;
					if (mdp.equals(mdpC)) {
						utilisateurModifier = utilisateurParser(request, mdp, utilisateurCourant.getNoUtilisateur(),
								utilisateurCourant.getCredit(), utilisateurCourant.isAdministrateur());
						if (!utilisateurCourant.equals(utilisateurModifier)) {
							if (manager.updateUtilisateur(utilisateurModifier)) {
								session.setAttribute("utilisateurCourant", utilisateurModifier);
							}
						}
					} else {
						utilisateurModifier = utilisateurParser(request, "", utilisateurCourant.getNoUtilisateur(),
								utilisateurCourant.getCredit(), utilisateurCourant.isAdministrateur());
						session.setAttribute("utilisateurModifier", utilisateurModifier);
					}

				} else {
					Utilisateur utilisateurModifier = utilisateurParser(request, mdpO,
							utilisateurCourant.getNoUtilisateur(), utilisateurCourant.getCredit(),
							utilisateurCourant.isAdministrateur());
					if (!utilisateurCourant.equals(utilisateurModifier)) {
						if (manager.updateUtilisateur(utilisateurModifier)) {
							session.setAttribute("utilisateurCourant", utilisateurModifier);
						}
					}
				}
			} else {
				Utilisateur utilisateurModifier = utilisateurParser(request, "", utilisateurCourant.getNoUtilisateur(),
						utilisateurCourant.getCredit(), utilisateurCourant.isAdministrateur());
				session.setAttribute("utilisateurModifier", utilisateurModifier);
			}
			doGet(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/");
		}

	}

	private Utilisateur utilisateurParser(HttpServletRequest request, String password, int noUtilisateur, int credit,
			boolean administrateur) {
		String motDePasse = CryptagePassword.crypteString(password);
		String pseudo = request.getParameter("pseudo").trim();
		String nom = request.getParameter("nom").trim();
		String prenom = request.getParameter("prenom").trim();
		String email = request.getParameter("email").trim();
		String telephone = request.getParameter("telephone").trim();
		String rue = request.getParameter("rue").trim();
		String code_postal = request.getParameter("codePostal").trim();
		String ville = request.getParameter("ville").trim();
		return new Utilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville,
				motDePasse, credit, administrateur);
	}
}
