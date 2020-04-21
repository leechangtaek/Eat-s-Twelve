package restaurant.model.dao;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

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
import restaurant.model.vo.Restaurant;
import restaurant.model.vo.RestaurantMap;

public class RestaurantDAO {
	
	private Properties prop = new Properties();
	
	public RestaurantDAO() {
		String fileName = PhotoDAO.class.getResource("/sql/restaurant/restaurant-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public List<Restaurant> selectOneByRName(Connection conn, String rName) {
		System.out.println(rName);
		String[] strArr = rName.split("/");
		System.out.println(strArr[0]);
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectOneByRName");
		Restaurant r = null;
		
		List<Restaurant> list = null;
		System.out.println("여기까지 실행됨11111");
		try {
			pstmt = conn.prepareStatement(query);
			list = new ArrayList<>();
			System.out.println("여기까지 실행됨22222");
			for(int i=0;i<strArr.length;i++) {
				System.out.println("여기까지 실행됨33333"+strArr[i]);
				
				pstmt.setString(1, strArr[i]);
				
				rset = pstmt.executeQuery();
				r = new Restaurant();
				if(rset.next()) {
					System.out.println("여기까지 실행됨44444");
					r.setrName(rset.getString("r_name"));
					r.setType(rset.getString("type"));
					r.setLocation(rset.getString("location"));
					r.setDescription(rset.getString("description"));
					r.setCategory(rset.getString("category"));
					r.setGrade(rset.getDouble("grade"));
					r.setrPhone(rset.getString("r_phone"));
					r.setParkingYN(rset.getString("parking_yn"));
					r.setOpenTime(rset.getInt("open_time"));
					r.setCloseTime(rset.getInt("close_time"));
					r.setHoliday(rset.getString("holiday"));
					System.out.println(r);
					list.add(r);
				}
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("selectOneByRname쿼리조회 실패");
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public List<RestaurantMap> selectMapOnebyRname(Connection conn, String rName) {

		String[] strArr = rName.split("/");

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMapOnebyRname");
		RestaurantMap m = null;
		List<RestaurantMap> list = null;
		try {
			pstmt = conn.prepareStatement(query);
			list = new ArrayList<>();
			for(int i=0;i<strArr.length;i++) {
				pstmt.setString(1, strArr[i]);

				rset = pstmt.executeQuery();
				m = new RestaurantMap();
				if(rset.next()) {
					m.setLat(rset.getString("lat"));
					m.setLng(rset.getString("lng"));
				}



				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("selectOneByRname쿼리조회 실패");
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}
	
	public List<Restaurant> selectByFood(String srchFood) {
		PreparedStatement pstmt = null;
		List<Restaurant> list = null;
		Connection conn = null;
		ResultSet rset = null;
		String query = prop.getProperty("searchSelectByFood");
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+srchFood+"%");
			pstmt.setString(2, "%"+srchFood+"%");
			pstmt.setString(3, "%"+srchFood+"%");
			pstmt.setString(4, "%"+srchFood+"%");
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rset.next()) {
				Restaurant r = new Restaurant();
				r.setrName(rset.getString("r_name"));
				r.setType(rset.getString("type"));
				r.setLocation(rset.getString("location"));
				r.setCategory(rset.getString("category"));
				
				list.add(r);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("Food조회 실패!",e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public List<Restaurant> recentRestaurant(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("recentRestaurantList");
		List<Restaurant> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,userId);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Restaurant r = new Restaurant();
				r.setrName(rset.getString("r_name"));
				r.setGrade(rset.getInt("grade"));
				r.setLocation(rset.getString("location"));
				r.setType(rset.getString("type"));
				
				list.add(r);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("최근 식당 조회 실패!",e);
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public List<Restaurant> wishRestaurant(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("wishRestaurantList");
		List<Restaurant> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,userId);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Restaurant r = new Restaurant();
				r.setrName(rset.getString("r_name"));
				r.setGrade(rset.getInt("grade"));
				r.setLocation(rset.getString("location"));
				r.setType(rset.getString("type"));
				
				list.add(r);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("wish식당 조회 실패!",e);
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public Restaurant favoriteRestaurant(Connection conn, String userFavorite) {
		PreparedStatement pstmt = null;
		Restaurant r = null;
		ResultSet rset = null;
		String query = prop.getProperty("favoriteRestaurant");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,"%"+userFavorite+"%");
			rset = pstmt.executeQuery();
			r = new Restaurant();
			if(rset.next()) {
				r.setrName(rset.getString("r_name"));
				r.setGrade(rset.getInt("grade"));
				r.setLocation(rset.getString("location"));
				r.setType(rset.getString("type"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("좋아요 식당 조회 실패!",e);
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return r;
	}
	
	//희진
	public Restaurant selectOne(Connection conn, String rName) {
		Restaurant r = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectOneByRName");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, rName);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				r = new Restaurant();
				r.setrName(rset.getString("r_name"));
				r.setType(rset.getString("type"));
				r.setLocation(rset.getString("location"));
				r.setBestYN(rset.getString("best_yn"));
				r.setDescription(rset.getString("description"));
				r.setCategory(rset.getString("category"));
				r.setGrade(rset.getDouble("grade"));
				r.setrPhone(rset.getString("r_phone"));
				r.setParkingYN(rset.getString("parking_yn"));
				r.setOpenTime(rset.getInt("open_time"));
				r.setCloseTime(rset.getInt("close_time"));
				r.setHoliday(rset.getString("holiday"));
				System.out.println(r);
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new MyException("식당조회 실패, 관리자에게 문의하세요!",e);
		} finally {
			close(rset);
			close(pstmt);
		} 
		
		return r;
	}
	
	public List<Restaurant> nearRestaurant(Connection conn, String location) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("nearRestaurantList");
		List<Restaurant> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+location+"%");
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Restaurant r = new Restaurant();
				
				r.setrName(rset.getString("r_name"));
				r.setGrade(rset.getInt("grade"));
				r.setCategory(rset.getString("category"));
				r.setDescription(rset.getString("description"));
				r.setOpenTime(rset.getInt("open_time"));
				r.setCloseTime(rset.getInt("close_time"));
				r.setType(rset.getString("type"));
				r.setLocation(rset.getString("location"));
				
				list.add(r);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("근처식당 조회실패, 관리자에게 문의하세요.",e);
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public List<Restaurant> selectRecent6List(Connection conn) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectRecent6List");
		List<Restaurant> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Restaurant r = new Restaurant();
				
				r.setrName(rset.getString("r_name"));
				r.setGrade(rset.getInt("grade"));
				r.setCategory(rset.getString("category"));
				r.setDescription(rset.getString("description"));
				r.setOpenTime(rset.getInt("open_time"));
				r.setCloseTime(rset.getInt("close_time"));
				r.setType(rset.getString("type"));
				r.setLocation(rset.getString("location"));
				r.setEnrollDate(rset.getDate("enrolldate"));
				
				
				list.add(r);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public List<Restaurant> selectBestGrade6List(Connection conn) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectBestGrade6List");
		List<Restaurant> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Restaurant r = new Restaurant();
				
				r.setrName(rset.getString("r_name"));
				r.setGrade(rset.getInt("grade"));
				r.setCategory(rset.getString("category"));
				r.setDescription(rset.getString("description"));
				r.setType(rset.getString("type"));
				r.setLocation(rset.getString("location"));
				
				list.add(r);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

}
