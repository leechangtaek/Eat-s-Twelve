package restaurant.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import Photo.model.dao.PhotoDAO;
import common.exception.MyException;
import restaurant.model.vo.RestaurantMenu;

public class RestaurantMenuDAO {
	
	private Properties prop = new Properties();
	
	public RestaurantMenuDAO() {
		String fileName = PhotoDAO.class.getResource("/sql/restaurant/restaurantMenu-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public List<RestaurantMenu> selectYNByRName(Connection conn, String rName) {

		String[] strArr = rName.split("/");

		RestaurantMenu rm = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<RestaurantMenu> list = null;
		String query = prop.getProperty("selectYNByRName");

		try {
			pstmt = conn.prepareStatement(query);
			list = new ArrayList<>();
			for(int i=0;i<strArr.length;i++) {
				pstmt.setString(1, strArr[i]);
				rset = pstmt.executeQuery();
				rm = new RestaurantMenu();
				if(rset.next()) {
					rm.setrName(rName);
					if(rset.getInt("cnt")==0) {
						rm.setSingleYN("N");
					}
					else {
						rm.setSingleYN("Y");
					}

				}

				list.add(rm);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MyException("혼밥가능 조회 실패!",e);
		} finally {
			close(rset);
			close(pstmt);
		}


		return list;
	}
}
