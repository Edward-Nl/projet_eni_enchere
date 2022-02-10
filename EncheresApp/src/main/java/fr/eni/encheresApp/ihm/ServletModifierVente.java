package fr.eni.encheresApp.ihm;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheresApp.BusinessException;
import fr.eni.encheresApp.bll.ArticlesVenduManager;
import fr.eni.encheresApp.bll.RetraitManager;
import fr.eni.encheresApp.bll.UtilisateurManager;
import fr.eni.encheresApp.bo.ArticleVendu;
import fr.eni.encheresApp.bo.Retrait;
import fr.eni.encheresApp.bo.Utilisateur;

/**
 * Servlet implementation class ServletModifierVente
 */
@WebServlet("/ServletModifierVente")
public class ServletModifierVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticlesVenduManager managerArticle = new ArticlesVenduManager();
		RetraitManager managerRetrait = new RetraitManager();
		UtilisateurManager managerUtilisateur = new UtilisateurManager();
		String noArticleString = request.getParameter("noArticle");
		int noArticle = Integer.parseInt(noArticleString);
		if(noArticle != 0) {
			ArticleVendu article = null;
			Retrait retrait = null;
			try {
				article = managerArticle.selectArticleById(noArticle);
				retrait = managerRetrait.selectById(noArticle);
				article.modificationEtatVente(article.getDateDebutEncheres(), article.getDateFinEncheres());
				request.setAttribute("article", article);
				if(retrait != null) {
					request.setAttribute("retrait", retrait);
				} else {
					String pseudoVendeur = article.getPseudoUtilisateur();
					Utilisateur vendeur = managerUtilisateur.selectByPseudo(pseudoVendeur);
					retrait = new Retrait(vendeur.getRue(),vendeur.getCodePostal(),vendeur.getVille());
					request.setAttribute("retrait", retrait);
				}
				
			}catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher("/WEB-INF/views/jspModifVente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Création des variable */
		Date dateDebut, dateFin = null;
		ArticleVendu article = null;
		Retrait retrait = null;
		boolean alreadyRedirect = false;
		/* Appelle des manager*/
		ArticlesVenduManager managerArticle = new ArticlesVenduManager();
		RetraitManager managerRetrait = new RetraitManager();
		/* ---------- */
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String noArticleString = request.getParameter("noArticle");
		System.out.println(noArticleString);
		int noArticle = Integer.parseInt(noArticleString);
		/* Récuperation des données du form */
		String nom = request.getParameter("nom");
		String description = request.getParameter("description");
		String categorieString = request.getParameter("categorie");
		String prixString = request.getParameter("prix");
		String debut = request.getParameter("debut");
		String fin = request.getParameter("fin");
		String rue = request.getParameter("rue");
		String cPostal = request.getParameter("cPostal");
		String ville = request.getParameter("ville");
		/* ----- */
		dateDebut = Date.valueOf(debut);
		dateFin = Date.valueOf(fin);
		int categorie = Integer.parseInt(categorieString);
		int prix = Integer.parseInt(prixString);
		article = new ArticleVendu(noArticle,nom,description,dateDebut,dateFin,prix,categorie);
		try {
			retrait = new Retrait(noArticle,rue,cPostal,ville);
			Retrait verifRetrait = managerRetrait.selectById(noArticle);
			if (request.getParameter("delete") != null) {
				if(verifRetrait != null) {
					managerRetrait.delete(noArticle);
				}
				managerArticle.deleteArticle(article.getNo_Article());
				alreadyRedirect = true;
				response.sendRedirect(request.getContextPath() + "/");
			} else {
				managerArticle.update(article);
				if(verifRetrait != null) {
					managerRetrait.update(retrait);
				} else {
					managerRetrait.insert(retrait);
				}
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!alreadyRedirect) {
			doGet(request, response);
		}
	}

}
