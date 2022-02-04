package fr.eni.encheresApp.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fr.eni.encheresApp.bo.Retrait;
import fr.eni.encheresApp.dal.ConnectionProvider;
import fr.eni.encheresApp.dal.RetraitDAO;

public class RetraitDAOJdbcImpl implements RetraitDAO{
	
	private static final String INSERT = "INSERT INTO RETRAITS(no_article,rue,code_postal,ville) VALUES (?,?,?,?)";
	private static final String SELECT = "SELECT * FROM RETRAITS WHERE no_article = ?";
	private static final String UPDATE = "UPDATE RETRAITS SET rue = ?, code_postal = ?, ville = ? WHERE no_article = ?";
	private static final String DELETE = "DELETE FROM RETRAITS WHERE no_article = ?";

	@Override
	public void insert(Retrait lieu) {
		try(Connection cnx = ConnectionProvider.getConnection();PreparedStatement pstmt = cnx.prepareStatement(INSERT)) {
			pstmt.setInt(1, lieu.getNo_article());
			pstmt.setString(2, lieu.getRue());
			pstmt.setString(3, lieu.getCodePostal());
			pstmt.setString(4, lieu.getVille());
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Retrait selectById(int no_article) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Retrait retrait) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int no_article) {
		// TODO Auto-generated method stub
		
	}

}
