package user.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import comment.model.vo.Comment;
import common.exception.MyException;
import restaurant.model.vo.Inquire;
import user.model.vo.User;
import user.model.vo.recentUser;

public class UserDAO {
	private Properties prop = new Properties();
	
	public UserDAO() {
		String fileName = UserDAO.class.getResource("/sql/user/user-query.properties")
										 .getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int userRecent(Connection conn, recentUser ru) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("userRecent");
		
		try {
			//1.Statement객체 생성
			pstmt = conn.prepareStatement(query);//미완성쿼리전달
			//2.미완성 쿼리 값 대입
			pstmt.setString(1, ru.getUserId());
			pstmt.setString(2, ru.getR_name());
			//3.쿼리실행 => ResultSet
			result = pstmt.executeUpdate();
			//4.ResultSet => Member
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("유저 최근 조회 실패!",e);
		}finally{
			//5.자원반납
			close(pstmt);
		}
		
		return result;
		
	}
	public User selectOne(Connection conn, String memberId) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String query =prop.getProperty("selectOne");
		User u=null;
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, memberId);			
			rset=pstmt.executeQuery();
			while(rset.next()) {
				 u=new User();
				u.setName(rset.getString("name"));
				u.setUserId(rset.getString("id"));
				u.setPwd(rset.getString("pwd"));
				u.setGender(rset.getString("gender"));
				u.setAge(rset.getInt("age"));
				u.setAddress(rset.getString("address"));
				u.setEmail(rset.getString("email"));
				u.setPhone(rset.getString("phone"));
				u.setFavorite(rset.getString("favorite"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MyException("유저 조회 실패!",e);
		}finally {
			close(rset);
			close(pstmt);
		}
		return u;
	}


	public int insertUser(Connection conn, User u) {
		PreparedStatement pstmt=null;
		int result=0;
		String query =prop.getProperty("insertUser");
	
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, u.getUserId());
			pstmt.setString(2, u.getPwd());	
			pstmt.setString(3, u.getName());	
			pstmt.setString(4, u.getEmail());	
			pstmt.setString(5, u.getAddress());	
			pstmt.setString(6, u.getPhone());
			pstmt.setInt(7, u.getAge());
			pstmt.setString(8, u.getFavorite());
			pstmt.setString(9, u.getGender());			
			result=pstmt.executeUpdate();
	}
		
	 catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new MyException("유저 입력 조회 실패!",e);
	}finally {
		close(pstmt);
	}
	return result;

	}
	
	public int updateUser(Connection conn, User u) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateUser");
		
		try {
			
			//1. 미완성쿼리문을 가지고 PreparedStatement객체생성
			pstmt = conn.prepareStatement(query);
			//객체생성후 ? 부분 값대입.
//			pstmt.setString(1, m.getPassword());
			pstmt.setString(1, u.getName());
			pstmt.setString(2, u.getGender());
			pstmt.setInt(3, u.getAge());
			pstmt.setString(4, u.getEmail());
			pstmt.setString(5, u.getPhone());
			pstmt.setString(6, u.getAddress());
			pstmt.setString(7, u.getFavorite());
			pstmt.setString(8, u.getUserId());

			//2. 쿼리문 실행, 실행결과 받기
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();//콘솔로깅용으로 남겨둠
			//사용자 정의 예외 던짐.
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updatePassword(Connection conn, User u) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updatePassword");
		
		try {
			
			//1. 미완성쿼리문을 가지고 PreparedStatement객체생성
			pstmt = conn.prepareStatement(query);
			//객체생성후 ? 부분 값대입.
			pstmt.setString(1, u.getPwd());
			pstmt.setString(2, u.getUserId());

			//2. 쿼리문 실행, 실행결과 받기
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();//콘솔로깅용으로 남겨둠
			throw new MyException("비밀번호 변경 실패!, 관리자에게 문의하세요",e);
			//사용자 정의 예외 던짐.
		} 
		
		return result;
	}

	public int deleteUser(Connection conn, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteUser");
		
		try {
			
			//1. 미완성쿼리문을 가지고 PreparedStatement객체생성
			pstmt = conn.prepareStatement(query);
			//객체생성후 ? 부분 값대입.
			pstmt.setString(1, userId);

			//2. 쿼리문 실행, 실행결과 받기
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();//콘솔로깅용으로 남겨둠
			throw new MyException("회원 탈퇴 실패, 관리자에게 문의하세요!",e);
			//사용자 정의 예외 던짐.
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int inquire(Connection conn, Inquire inquire) {
		PreparedStatement pstmt=null;
		int result=0;
		String query =prop.getProperty("inquire");
	
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, inquire.getUserId());
			pstmt.setString(2, inquire.getrName());	
			pstmt.setString(3, inquire.getType());	
			pstmt.setString(4, inquire.getLocation());	
			pstmt.setString(5, inquire.getDescription());	
			pstmt.setString(6, inquire.getCategory());
			pstmt.setString(7, inquire.getrPhone());
			pstmt.setString(8, inquire.getParkingYN());
			pstmt.setInt(9, inquire.getOpenTime());			
			pstmt.setInt(10, inquire.getCloseTime());			
			pstmt.setString(11, inquire.getHoliday());			
			result=pstmt.executeUpdate();
	}
		
	 catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new MyException("등록 요청 실패, 관리자에게 문의하세요.",e);
	}finally {
		close(pstmt);
	}
	return result;
	}
	
	
	public int insertPhoto(Connection conn, String originalFileName, String renamedFileName, String r_name,
			String location) {
		PreparedStatement pstmt=null;
		int result=0;
		String query =prop.getProperty("insertPhoto");
	
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, r_name);
			pstmt.setString(2, location);	
			pstmt.setString(3, originalFileName);	
			pstmt.setString(4, renamedFileName);	
			result=pstmt.executeUpdate();
	}
		
	 catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new MyException("사진입력 실패, 관리자에게 문의하세요.",e);
	}finally {
		close(pstmt);
	}
	return result;
	}
	
	public int userFavorite(Connection conn, recentUser ru) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("userFavorite");
		
		try {
			//1.Statement객체 생성
			pstmt = conn.prepareStatement(query);//미완성쿼리전달
			//2.미완성 쿼리 값 대입
			pstmt.setString(1, ru.getUserId());
			pstmt.setString(2, ru.getR_name());
			//3.쿼리실행 => ResultSet
			result = pstmt.executeUpdate();
			//4.ResultSet => Member
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("유저 찜 목록 실패!",e);
		}finally{
			//5.자원반납
			close(pstmt);
		}
		
		return result;
	}

	public int userNoneFavorite(Connection conn, recentUser ru) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("userNoneFavorite");
		
		try {
			//1.Statement객체 생성
			pstmt = conn.prepareStatement(query);//미완성쿼리전달
			//2.미완성 쿼리 값 대입
			pstmt.setString(1, ru.getUserId());
			pstmt.setString(2, ru.getR_name());
			//3.쿼리실행 => ResultSet
			result = pstmt.executeUpdate();
			//4.ResultSet => Member
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("유저 찜 목록 실패!",e);
		}finally{
			//5.자원반납
			close(pstmt);
		}
		
		return result;
		
	}
	
	public String findId(Connection conn, String name, String phone) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String query =prop.getProperty("findId");
		String id="";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, phone);			
			pstmt.setString(2, name);			
			rset=pstmt.executeQuery();
			while(rset.next()) {
				 id=rset.getString("id");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//			throw new MyException("유저 조회 실패!",e);
		}finally {
			close(rset);
			close(pstmt);
		}
		return id;
	}
	
	public String findPassword(Connection conn, String userId, String email) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String query =prop.getProperty("findPassword");
		String password="";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, userId);			
			pstmt.setString(2, email);			
			rset=pstmt.executeQuery();
			while(rset.next()) {
				 password=rset.getString("pwd");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//			throw new MyException("유저 조회 실패!",e);
		}finally {
			close(rset);
			close(pstmt);
		}
		return password;
	}
	
	public List<Comment> selectComment(Connection conn, String userId) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String query =prop.getProperty("selectComment");
		List<Comment> list=new ArrayList<>();
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, userId);
			
			rset=pstmt.executeQuery();
			while(rset.next()) {
				Comment comment=new Comment();
				comment.setBoardNo(rset.getInt("board_no"));
				comment.setId(rset.getString("id"));
				comment.setrName(rset.getString("r_name"));
				comment.setLocation(rset.getString("location"));
				comment.setBoarding(rset.getString("boarding"));
				comment.setGrade(rset.getInt("grade"));
				comment.setbDate(rset.getDate("b_date"));
				comment.setBoardCommentLevel(rset.getInt("board_comment_level"));
				comment.setBoardCommentRef(rset.getInt("board_comment_ref"));
				comment.setOriginalFileName(rset.getString("original_filename"));
				comment.setRenamedFileName(rset.getString("renamed_filename"));
				
			list.add(comment);
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
	
	public recentUser userViewFavorite(Connection conn, String userId, String rName) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String query =prop.getProperty("userViewFavorite");
		recentUser ru=null;
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, userId);			
			pstmt.setString(2, rName);			
			rset=pstmt.executeQuery();
			if(rset.next()) {
				 ru=new recentUser();
				ru.setUserId(rset.getString("id"));
				ru.setR_name(rset.getString("r_name"));
				ru.setCheck_yn(rset.getString("check_yn"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MyException("찜목록실패!",e);
		}finally {
			close(rset);
			close(pstmt);
		}
		return ru;
	}
	
	public int changePassword(Connection conn, String userId, String password) {
		PreparedStatement pstmt=null;
		int result;
		String query =prop.getProperty("changePassword");
		
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, password);
			pstmt.setString(2, userId);
			
			result=pstmt.executeUpdate();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MyException("비밀번호 변경 실패!",e);
		} finally {
			
			close(pstmt);
		}
		return result;
	}
	
}
