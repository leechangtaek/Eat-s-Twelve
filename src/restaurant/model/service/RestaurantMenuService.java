package restaurant.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import restaurant.model.dao.RestaurantMenuDAO;
import restaurant.model.vo.RestaurantMenu;

public class RestaurantMenuService {

	public List<RestaurantMenu> selectYNByRName(String rName) {

		Connection conn = getConnection();
		List<RestaurantMenu> list = new ArrayList<>();

		list = new RestaurantMenuDAO().selectYNByRName(conn,rName);

		close(conn);

		return list;
	}
}
