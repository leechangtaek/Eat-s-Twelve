package restaurant.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import restaurant.model.dao.RestaurantDAO;
import restaurant.model.vo.Restaurant;
import restaurant.model.vo.RestaurantMap;

public class RestaurantService {

	public List<Restaurant> selectOneByRName(String rName) {

		Connection conn = getConnection();
		List<Restaurant> list = new ArrayList<>();
		Restaurant r = null;

		list = new RestaurantDAO().selectOneByRName(conn, rName);

		close(conn);

		return list;
	}


	public List<RestaurantMap> selectMapOnebyRname(String rName) {

		Connection conn = getConnection();

		List<RestaurantMap> list = new ArrayList<>();

		list = new RestaurantDAO().selectMapOnebyRname(conn, rName);

		close(conn);

		return list;
	}

	
	public List<Restaurant> selectByFood(String srchFood) {
		Connection conn = getConnection();
		List<Restaurant> foodList = new ArrayList<>();
		RestaurantDAO restaurantDAO = new RestaurantDAO();
		
		foodList = restaurantDAO.selectByFood(srchFood);

		close(conn);
		
		
		return foodList;
	}
	
	public List<Restaurant> recentRestaurant(String userId) {
		Connection conn = getConnection();
		
		List<Restaurant> list = new ArrayList<>();
		RestaurantDAO restaurantDAO = new RestaurantDAO();
		
		list = restaurantDAO.recentRestaurant(conn, userId);
		
		close(conn);
		
		return list;
	}
	
	public List<Restaurant> wishRestaurant(String userId) {
		
		Connection conn = getConnection();

		List<Restaurant> list = new ArrayList<>();
		RestaurantDAO restaurantDAO = new RestaurantDAO();

		list = restaurantDAO.wishRestaurant(conn, userId);

		close(conn);

		return list;
	}


	public Restaurant favoriteRestaurant(String userFavorite) {
		Connection conn = getConnection();

		Restaurant r = new Restaurant();
		r = new RestaurantDAO().favoriteRestaurant(conn,userFavorite);

		close(conn);

		return r;
		
	}
	
	//희진
	public Restaurant selectOne(String rName) {
		
		Connection conn = getConnection();
		Restaurant r = new RestaurantDAO().selectOne(conn, rName);
		close(conn);
		return r;
	}
	
	public List<Restaurant> nearRestaurant(String location) {
		
		Connection conn = getConnection();
		
		List<Restaurant> list = new ArrayList<>();
		RestaurantDAO restaurantDAO = new RestaurantDAO();
		
		list = restaurantDAO.nearRestaurant(conn, location);
		
		close(conn);
		
		return list;
	}
	
	public List<Restaurant> selectRecent6List() {
		
		List<Restaurant> list = new ArrayList<>();
		Connection conn = getConnection();
		
		list = new RestaurantDAO().selectRecent6List(conn);
		
		close(conn);
		
		return list;
	}


	public List<Restaurant> selectBestGrade6List() {

		List<Restaurant> list = new ArrayList<>();
		Connection conn = getConnection();
		
		list = new RestaurantDAO().selectBestGrade6List(conn);
		
		close(conn);
		
		return list;
	}
	


}
