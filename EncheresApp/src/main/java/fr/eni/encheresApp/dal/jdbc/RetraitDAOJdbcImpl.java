package fr.eni.encheresApp.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.encheresApp.BusinessException;
import fr.eni.encheresApp.bo.Retrait;
import fr.eni.encheresApp.dal.CodeResultatDAL;
import fr.eni.encheresApp.dal.ConnectionProvider;
import fr.eni.encheresApp.dal.RetraitDAO;

public class RetraitDAOJdbcImpl implements RetraitDAO {

	private static final String INSERT = "INSERT INTO RETRAITS(no_article,rue,code_postal,ville) VALUES (?,?,?,?)";
	private static final String SELECT = "SELECT * FROM RETRAITS WHERE no_article = ?";
	private static final String UPDATE = "UPDATE RETRAITS SET rue = ?, code_postal = ?, ville = ? WHERE no_article = ?";
	//private static final String DELETE = "DELETE FROM RETRAITS WHERE no_article = ?";

	@Override
	public void insert(Retrait lieu) throws BusinessException{
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(INSERT)) {
			pstmt.setInt(1, lieu.getNo_article());
			pstmt.setString(2, lieu.getRue());
			pstmt.setString(3, lieu.getCodePostal());
			pstmt.setString(4, lieu.getVille());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDAL.REGLE_RETRAIT_DAL_INSERT_ERREUR);
			throw businessException;
		}

	}

	@Override
	public Retrait selectById(int no_article) throws BusinessException{
		Retrait retrait = null;
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT)) {
			pstmt.setInt(1, no_article);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					retrait = new Retrait(rs.getInt("no_article"), rs.getString("rue"), rs.getString("code_postal"),
							rs.getString("ville"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDAL.REGLE_RETRAIT_DAL_SELECT_ID_ERREUR);
			throw businessException;
		}
		return retrait;
	}

	@Override
	public void update(Retrait retrait) throws BusinessException{
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(UPDATE)) {
			pstmt.setString(1, retrait.getRue());
			pstmt.setString(2, retrait.getCodePostal());
			pstmt.setString(3, retrait.getVille());
			pstmt.setInt(4, retrait.getNo_article());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDAL.REGLE_RETRAIT_DAL_UPDATE_ERREUR);
			throw businessException;
		}

	}

	@Override
	public void delete(int no_article) {
		// TODO Auto-generated method stub

	}

}
