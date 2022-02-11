package fr.eni.encheresApp.ihm;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheresApp.BusinessException;
import fr.eni.encheresApp.bll.ArticlesVenduManager;
import fr.eni.encheresApp.bll.EnchereManager;
import fr.eni.encheresApp.bll.RetraitManager;
import fr.eni.encheresApp.bll.UtilisateurManager;
import fr.eni.encheresApp.bo.ArticleVendu;
import fr.eni.encheresApp.bo.Enchere;
import fr.eni.encheresApp.bo.Retrait;
import fr.eni.encheresApp.bo.Utilisateur;

/**
 * Servlet implementation class ServletDetailsArticle
 */
@WebServlet("/article")
public class ServletDetailsArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletDetailsArticle() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArticlesVenduManager managerArticle = new ArticlesVenduManager();
		RetraitManager managerRetrait = new RetraitManager();
		EnchereManager managerEnchere = new EnchereManager();
		UtilisateurManager managerUtilisateur = new UtilisateurManager();
		BusinessException businessException = new BusinessException();
		String noArticleString = request.getParameter("noArticle");
		int noArticle = Integer.parseInt(noArticleString);
		String pseudo = (String) request.getSession().getAttribute("utilisateurCourant");
		if (noArticle != 0) {
			ArticleVendu article = null;
			Retrait retrait = null;
			Enchere enchere = null;
			List<Enchere> listeEnchere = new ArrayList<Enchere>();
			try {
				Utilisateur utilsCourant = managerUtilisateur.selectByPseudo(pseudo);
				int idUtilCourant = utilsCourant.getNoUtilisateur();
				article = managerArticle.selectArticleById(noArticle);
				retrait = managerRetrait.selectById(noArticle);
				enchere = managerEnchere.selectById(noArticle);
				listeEnchere = managerEnchere.selectAll(noArticle);
				article.modificationEtatVente(article.getDateDebutEncheres(), article.getDateFinEncheres());
				request.setAttribute("article", article);
				request.setAttribute("enchere", enchere);
				request.setAttribute("ListeEnchere", listeEnchere);
				request.setAttribute("noUtilCourant", idUtilCourant);
				if (retrait != null) {
					request.setAttribute("retrait", retrait);
				} else {
					String pseudoVendeur = article.getPseudoUtilisateur();
					Utilisateur vendeur = managerUtilisateur.selectByPseudo(pseudoVendeur);
					retrait = new Retrait(vendeur.getRue(), vendeur.getCodePostal(), vendeur.getVille());
					request.setAttribute("retrait", retrait);
				}
			} catch (BusinessException e) {
				businessException.ajouterToutesErreurs(e.getListeCodesErreur());
			}

		}
		if (!businessException.hasErreurs()) {
			request.setAttribute("listeCodesErreur", businessException.getListeCodesErreur());
		}
		request.getRequestDispatcher("/WEB-INF/views/jspDetailArticle.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		UtilisateurManager managerUtils = new UtilisateurManager();
		EnchereManager managerEnchere = new EnchereManager();
		ArticlesVenduManager managerArticle = new ArticlesVenduManager();
		int montantEnchere = Integer.valueOf(request.getParameter("enchere"));
		int noArticle = Integer.valueOf(request.getParameter("noArticle"));
		Date dateNow = Date.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		BusinessException businessException = new BusinessException();
		String pseudo = (String) session.getAttribute("utilisateurCourant");
		try {
			Enchere verifEnchere = managerEnchere.selectById(noArticle);
			Utilisateur utils = managerUtils.selectByPseudo(pseudo);
			ArticleVendu articleCourant = managerArticle.selectArticleById(noArticle);
			// Verifie que l'enchere est toujours ouverte
			if (articleCourant.getDateFinEncheres().after(dateNow)) {
				int idUtils = utils.getNoUtilisateur();
				// Solde de credit de l'encherisseur
				int creditDisponible = utils.getCredit();
				Enchere enchere = new Enchere(idUtils, noArticle, dateNow, montantEnchere);
				// Verif que l'utilisateur a les credit disponible pour l'enchere
				if (creditDisponible >= enchere.getMontant_enchere()) {
					if (verifEnchere == null) {
						managerEnchere.insert(enchere);
						// Insert de la methode pour deduire les crédit de la cagnotte utilisateur
						creditDisponible = creditDisponible - montantEnchere;
						managerUtils.nouvelleCagnotte(idUtils, creditDisponible);
						session.setAttribute("credit", creditDisponible);
					} else if (verifEnchere != null) {
						// Récup de la precedente enchere pour rembourser l'utilisateur
						int no_utilARembourser = verifEnchere.getNo_utilisateur();
						int montantARembourser = verifEnchere.getMontant_enchere();
						Utilisateur util_a_rembourser = managerUtils.selectAvecId(no_utilARembourser);
						int nouvelleCagnotte_a_rembourser = util_a_rembourser.getCredit() + montantARembourser;
						managerUtils.nouvelleCagnotte(no_utilARembourser, nouvelleCagnotte_a_rembourser);
						// Insert de la nouvelle enchere + deduire l'argent de sa cagnotte
						managerEnchere.insert(enchere);
						creditDisponible = creditDisponible - montantEnchere;
						managerUtils.nouvelleCagnotte(idUtils, creditDisponible);
						session.setAttribute("credit", creditDisponible);
					}
				}
			}

		} catch (BusinessException e) {
			businessException.ajouterToutesErreurs(e.getListeCodesErreur());
		}
		if (businessException.hasErreurs()) {
			request.setAttribute("listeCodesErreur", businessException.getListeCodesErreur());
		}

		doGet(request, response);
	}

}
