package fr.eni.encheresApp.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheresApp.bo.Categorie;
import fr.eni.encheresApp.dal.CategorieDAO;
import fr.eni.encheresApp.dal.ConnectionProvider;

public class CategorieDAOJdbcImpl implements CategorieDAO {
	private static final String SELECT_ALL = "SELECT no_categorie, libelle FROM CATEGORIES ";

	@Override
	public List<Categorie> selectAll() {
		List<Categorie> categories = new ArrayList<Categorie>();
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL)) {
			Categorie categorie = null;
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
					categories.add(categorie);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}

}
