package restaurant.model.dao;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import Photo.model.dao.PhotoDAO;
import common.exception.MyException;
import restaurant.model.vo.Restaurant;
import restaurant.model.vo.RestaurantMap;

public class RestaurantMapDAO {
	
	private Properties prop = new Properties();
	
	public RestaurantMapDAO() {
		String fileName = RestaurantMapDAO.class.getResource("/sql/restaurant/restaurant-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public RestaurantMap selectOneMap(Connection conn, String rName) {
		
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMapOnebyRname");
		RestaurantMap rMap = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, rName);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				rMap = new RestaurantMap();
				rMap.setMapNo(rset.getInt("map_no"));
				rMap.setrName(rset.getString("r_name"));
				rMap.setLat(rset.getString("lat"));
				rMap.setLng(rset.getString("lng"));
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new MyException("좌표 조회 실패, 관리자에게 문의하세요.",e);
		} finally {
			close(rset);
			close(pstmt);
		}
			
		
		return rMap;
	}

}
