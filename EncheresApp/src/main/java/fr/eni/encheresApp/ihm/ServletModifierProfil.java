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
		String pseudo = (String) request.getSession().getAttribute("utilisateurCourant");
		Utilisateur utilisateurCourantComplet = null;
		UtilisateurManager manager = new UtilisateurManager();
		try {
			utilisateurCourantComplet = manager.selectByPseudo(pseudo);
		} catch (BusinessException e) {
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
		}
		if (utilisateurCourantComplet != null) {
			utilisateurCourantComplet.setNoUtilisateur(-1);
			utilisateurCourantComplet.setMotDePasse("");
			utilisateurCourantComplet.setCredit(-1);
			utilisateurCourantComplet.setAdministrateur(false);
		}
		request.setAttribute("utilisateurCourantComplet", utilisateurCourantComplet);
		request.getRequestDispatcher("/WEB-INF/views/jspModifierProfil.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UtilisateurManager manager = new UtilisateurManager();
		BusinessException businessException = new BusinessException();
		HttpSession session = request.getSession();
		boolean alreadyRedirect = false;
		
		String pseudo = (String) session.getAttribute("utilisateurCourant");
		//Récupération de l'utilisateur Courant Complet stocker en BDD
		Utilisateur utilisateurCourant = null;
		try {
			utilisateurCourant = manager.selectByPseudo(pseudo);
		} catch (BusinessException e1) {
			businessException.ajouterToutesErreurs(e1.getListeCodesErreur());
		}
		
		//Recuperation du mot de passe de confirmation (ancien mot de passe
		String mdpO = request.getParameter("mdpO").trim();
		try {
			//controle que la paire pseudo/mot de passe de confirmation correspond bien a un utilisateur
			if (manager.selectByPseudoAndPswBoolean(pseudo, mdpO)) {
				//Switch entre la modification de l'utilisateur et la supression de celui ci
				if (request.getParameter("update") != null) {
					String mdp = request.getParameter("mdp").trim();
					String mdpC = request.getParameter("mdpC").trim();
					// Si au moins un des deux champs nouveaux mot de passe est n'es pas vide
					if (!mdp.isEmpty() || !mdpC.isEmpty()) {
						Utilisateur utilisateurModifier = null;
						//vérification que les deux champs sont equals
						if (mdp.equals(mdpC)) {
							utilisateurModifier = utilisateurParser(request, mdp, utilisateurCourant.getNoUtilisateur(),
									utilisateurCourant.getCredit(), utilisateurCourant.isAdministrateur());
							utilisateurModifier.setMotDePasse(mdp);
							if (manager.updateUtilisateur(utilisateurCourant, utilisateurModifier)) {
								request.setAttribute("ModificationValider", true);
								session.setAttribute("utilisateurCourant", utilisateurModifier.getPseudo());
							}
						//Sinon on remonte une Erreur
						} else {
							businessException.ajouterErreur(CodesResultatIHM.MOT_DE_PASSE_NON_IDENTIQUE);
							utilisateurModifier = utilisateurParser(request, "", utilisateurCourant.getNoUtilisateur(),
									utilisateurCourant.getCredit(), utilisateurCourant.isAdministrateur());
							request.setAttribute("utilisateurModifier", utilisateurModifier);
						}
					} else {
						Utilisateur utilisateurModifier = utilisateurParser(request, mdpO,
								utilisateurCourant.getNoUtilisateur(), utilisateurCourant.getCredit(),
								utilisateurCourant.isAdministrateur());

						utilisateurModifier.setMotDePasse(mdpO);
						if (manager.updateUtilisateur(utilisateurCourant, utilisateurModifier)) {
							request.setAttribute("ModificationValider", true);
							session.setAttribute("utilisateurCourant", utilisateurModifier.getPseudo());
						}

					}
					//supression du compte et redirection vers la servlet de deconnexion supprimant la session 
				} else if (request.getParameter("delete") != null) {
					manager.supprimerUtilisateur(pseudo);
					alreadyRedirect = true;
					response.sendRedirect(request.getContextPath() + "/Profil/Deconnexion");
				}
			} else {
				Utilisateur utilisateurModifier = utilisateurParser(request, "", utilisateurCourant.getNoUtilisateur(),
						utilisateurCourant.getCredit(), utilisateurCourant.isAdministrateur());
				request.setAttribute("utilisateurModifier", utilisateurModifier);
			}
		} catch (BusinessException e) {
			businessException.ajouterToutesErreurs(e.getListeCodesErreur());
		}
		if (businessException.hasErreurs()) {
			request.setAttribute("listeCodesErreur", businessException.getListeCodesErreur());
		}
		if (!alreadyRedirect) {
			doGet(request, response);
		}

	}
//Parser servant a creer un utilisateur avec les champ formulaire dans la request
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
