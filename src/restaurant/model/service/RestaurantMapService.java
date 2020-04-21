package restaurant.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;

import restaurant.model.dao.RestaurantMapDAO;
import restaurant.model.vo.RestaurantMap;

public class RestaurantMapService {

	public RestaurantMap selectOneMap(String rName) {
		
		Connection conn = getConnection();
		RestaurantMap rMap = new RestaurantMapDAO().selectOneMap(conn, rName);
		close(conn);
		
		return rMap;
	}

}
