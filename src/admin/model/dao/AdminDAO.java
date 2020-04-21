package admin.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import Photo.model.vo.Upload;
import common.exception.MyException;
import restaurant.model.vo.Inquire;
import user.model.vo.User;

public class AdminDAO {
	private Properties prop=new Properties();
	public AdminDAO() {
		
		String fileName=AdminDAO.class.getResource("/sql/admin/admin-query.properties").getPath();
	
		try {
			prop.load(new FileReader(fileName));
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<User> selectUserList(Connection conn, int cPage, int numPerPage) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String query =prop.getProperty("selectMemberList");
		List<User> list=new ArrayList<>();
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				User u=new User();
				u.setUserId(rset.getString("id"));
				u.setName(rset.getString("name"));
				u.setGender(rset.getString("gender"));
				u.setAge(rset.getInt("age"));
				u.setAddress(rset.getString("address"));
				u.setPhone(rset.getString("phone"));
			list.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MyException("유저 이름 조회 실패!",e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	public int selectMemberCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String query =prop.getProperty("selectMemberCount");
		int totalInfo=0;

		try {
			pstmt=conn.prepareStatement(query);

			rset=pstmt.executeQuery();
			if(rset.next()) {
				totalInfo=rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MyException("유저 이름 조회 실패!",e);
		} finally {
			close(rset);
			close(conn);
		}
		return totalInfo;

	}
	public List<User> selectByNameId(Connection conn, String srchName) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String query =prop.getProperty("searchUser");
		List<User> list=new ArrayList<>();
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, "%"+srchName+"%");
			pstmt.setString(2, "%"+srchName+"%");
			pstmt.setString(3, "%"+srchName+"%");
			pstmt.setString(4, "%"+srchName+"%");
			pstmt.setString(5, "%"+srchName+"%");
			pstmt.setString(6, "%"+srchName+"%");
			rset=pstmt.executeQuery();
			while(rset.next()) {
				User u=new User();
				u.setUserId(rset.getString("id"));
				u.setName(rset.getString("name"));
				u.setGender(rset.getString("gender"));
				u.setAge(rset.getInt("age"));
				u.setAddress(rset.getString("address"));
				u.setPhone(rset.getString("phone"));
			list.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MyException("유저 이름 조회 실패!",e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	public User searchById(Connection conn, String id) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String query =prop.getProperty("searchById");
		
		User u=null;
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			
			rset=pstmt.executeQuery();
			while(rset.next()) {
				 u=new User();
				u.setUserId(rset.getString("id"));
				u.setName(rset.getString("name"));
				u.setGender(rset.getString("gender"));
				u.setAge(rset.getInt("age"));
				u.setAddress(rset.getString("address"));
				u.setPhone(rset.getString("phone"));
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MyException("아이디 조회 에러!",e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return u;
	}
	public int insertId(Connection conn, String memberId, String time) {
		PreparedStatement pstmt=null;
		int result=0;
		String query =prop.getProperty("insertId");
		
		User u=null;
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, time);
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MyException("유저 접속 시간 입력 실패!",e);
		}
		return result;
	}
	public List<Map<String, Object>> adminVisit(Connection conn, int cPage, int numPerPage) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String query =prop.getProperty("selectUserList");
		List<Map<String, Object>> list = null;
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			list = new ArrayList<>();
			
			rset=pstmt.executeQuery();
			while(rset.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("id", rset.getString("id"));
				map.put("total", rset.getInt("total"));
				map.put("visit_time", rset.getString("visit_time"));
				map.put("rnum",rset.getString("rnum"));
				list.add(map);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MyException("유저 이름 조회 실패!",e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	public List<Map<String, Object>> totalvisit(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String query =prop.getProperty("totalvisit");
		List<Map<String, Object>> list = null;
		try {
			pstmt=conn.prepareStatement(query);	
			rset=pstmt.executeQuery();
			list = new ArrayList<>();
			
			while(rset.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("visit_time", rset.getString("visit_time"));
				map.put("total", rset.getInt("total"));
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("총 방문횟수 조회 에러!",e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	public int adminDelete(Connection conn, String id) {
		PreparedStatement pstmt=null;
		int result=0;
		String query =prop.getProperty("deleteUser");
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MyException("관리자 삭제 에러!",e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	public int selectUserCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String query =prop.getProperty("selectUserCount");
		int totalInfo=0;

		try {
			pstmt=conn.prepareStatement(query);

			rset=pstmt.executeQuery();
			if(rset.next()) {
				totalInfo=rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MyException("유저 이름 조회 실패!",e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalInfo;
	}
	
	public List<Map<String, Object>> inquire(Connection conn, int cPage, int numPerPage, String accept) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String query =prop.getProperty("inquirelist");
		List<Map<String, Object>> list = null;
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, accept);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			list = new ArrayList<>();
			
			rset=pstmt.executeQuery();
			while(rset.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("id", rset.getString("id"));
				map.put("r_name", rset.getString("r_name"));
				map.put("type", rset.getString("type"));
				map.put("location",rset.getString("location"));
				map.put("description",rset.getString("description"));
				map.put("category",rset.getString("category"));
				map.put("r_phone",rset.getString("r_phone"));
				map.put("parking_yn",rset.getString("parking_yn"));
				map.put("open_time",rset.getInt("open_time"));
				map.put("close_time",rset.getInt("close_time"));
				map.put("holiday",rset.getString("holiday"));
				map.put("inq_date",rset.getDate("inq_date"));
				map.put("accept_yn",rset.getString("accept_yn"));
				
				list.add(map);
			
			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new MyException("요청 조회 실패");
		}
		return list;
	}
	
	public int inquire_count(Connection conn, String accept) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String query =prop.getProperty("inquire_count");
		int totalInfo=0;

		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, accept);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				totalInfo=rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalInfo;
	}
	public List<Upload> searchByr_name(Connection conn, String r_name) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String query =prop.getProperty("searchR_name");
		List<Upload> list=new ArrayList<>();
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, r_name);
			
			
			rset=pstmt.executeQuery();
			while(rset.next()) {
				Upload upload=new Upload();
				upload.setUpNo(rset.getInt("up_no"));
				upload.setrName(rset.getString("r_name"));
				upload.setLocation(rset.getString("location"));
				upload.setOriginalFileName(rset.getString("original_filename"));
				upload.setReNamedFileName(rset.getString("renamed_filename"));
				
				list.add(upload);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MyException("음식점 조회 실패!, 관리자에게 문의하세요.",e);
		}
		return list;
	}
	public int admit(Connection conn, String r_name) {
		PreparedStatement pstmt=null;
		int result=0;
		String query =prop.getProperty("admit");
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, r_name);
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MyException("승인 실패 관리자에게 문의하세요.", e);
		}
		return result;
	}
	public Inquire inqInfo(Connection conn, String r_name) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String query =prop.getProperty("inqInfo");
		Inquire inq = null;
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, r_name);
			
			
			
			rset=pstmt.executeQuery();
			while(rset.next()) {
				inq =new Inquire();
				inq.setUserId(rset.getString("id"));
				inq.setrName(rset.getString("r_name"));
				inq.setType(rset.getString("type"));
				inq.setLocation(rset.getString("location"));
				inq.setDescription(rset.getString("description"));
				inq.setCategory(rset.getString("category"));
				inq.setrPhone(rset.getString("r_phone"));
				inq.setParkingYN(rset.getString("parking_yn"));
				inq.setOpenTime(rset.getInt("open_time"));
				inq.setCloseTime(rset.getInt("close_time"));
				inq.setHoliday(rset.getString("holiday"));
				
				
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MyException("inquire 조회 실패, 관리자에게 문의하세요.",e);
		}
		return inq;
	}
	public List<Upload> getUpload(Connection conn, String r_name) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String query =prop.getProperty("getUpload");
		List<Upload> list=new ArrayList<>();
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, r_name);
			
			
			rset=pstmt.executeQuery();
			while(rset.next()) {
				Upload upload=new Upload();
				upload.setUpNo(rset.getInt("up_no"));
				upload.setrName(rset.getString("r_name"));
				upload.setLocation(rset.getString("location"));
				upload.setOriginalFileName(rset.getString("original_filename"));
				upload.setReNamedFileName(rset.getString("renamed_filename"));
				
				list.add(upload);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MyException("업로드 실패!! 관리자에게 문의하세요.",e);
		}
		return list;
	}
	public int insertPhoto(Connection conn, Upload u) {
		PreparedStatement pstmt=null;
		int result2=0;
		String query =prop.getProperty("insertPhoto");
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, u.getrName());
			pstmt.setString(2, u.getLocation());
			pstmt.setString(3, u.getReNamedFileName());
			
			
			result2=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MyException("사진 입력 실패! 관리자에게 문의하세요",e);
		} finally {
			close(pstmt);
		}
		return result2;
	}
	
	public int refuse(Connection conn, String r_name) {
		PreparedStatement pstmt=null;
		int result=0;
		String query =prop.getProperty("refuse");
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, r_name);
			
			
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MyException("승인 거절 실패!",e);
		}
		return result;
	}
	
}

