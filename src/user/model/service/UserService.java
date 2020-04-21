package user.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import comment.model.vo.Comment;
import restaurant.model.vo.Inquire;
import user.model.dao.UserDAO;
import user.model.vo.User;
import user.model.vo.recentUser;

public class UserService {

	public int userRecent(recentUser ru) {
		Connection conn = getConnection();
		int result = new UserDAO().userRecent(conn, ru);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	public int insertUser(User u) {
		Connection conn=getConnection();
		int result=new UserDAO().insertUser(conn,u);
		close(conn);
		return	result;
	}
	public User selectOne(String memberId) {

		Connection conn=getConnection();
		User user=new UserDAO().selectOne(conn,memberId);
		close(conn);
		return	user;


	}
	
	public int updateUser(User u) {
		Connection conn = getConnection();
		int result = new UserDAO().updateUser(conn, u);
		
		//트랜잭션 처리
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	public int updatePassword(User u) {
		Connection conn = getConnection();
		int result = new UserDAO().updatePassword(conn, u);
		
		//트랜잭션 처리
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	public int deleteUser(String userId) {
		Connection conn = getConnection();
		int result = new UserDAO().deleteUser(conn, userId);
		
		//트랜잭션 처리
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	public int inquire(Inquire inquire) {
		Connection conn=getConnection();
		int result=new UserDAO().inquire(conn,inquire);
		close(conn);
		return	result;
	}
	
	public int insertPhoto(String originalFileName, String renamedFileName, String r_name, String location) {
		Connection conn=getConnection();
		int result=new UserDAO().insertPhoto(conn,originalFileName,renamedFileName,r_name,location);
		close(conn);
		return	result;
	}
	
	public int userFavorite(recentUser ru) {
		Connection conn = getConnection();
		int result = new UserDAO().userFavorite(conn, ru);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	public int userNoneFavorite(recentUser ru) {
		Connection conn = getConnection();
		int result = new UserDAO().userNoneFavorite(conn, ru);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	public String findId(String name, String phone) {
		Connection conn=getConnection();
		String id=new UserDAO().findId(conn,name,phone);
		close(conn);
		return	id;
	}
	
	public String findPassword(String userId, String email) {
		Connection conn=getConnection();
		String password=new UserDAO().findPassword(conn,userId,email);
		close(conn);
		return	password;
	}
	
	public List<Comment> selectComment(String userId) {
		Connection conn=getConnection();
		List<Comment> list=new UserDAO().selectComment(conn,userId);
		close(conn);
		return	list;
	}
	
	public recentUser userViewFavorite(String userId, String rName) {
		Connection conn=getConnection();
		recentUser ru=new UserDAO().userViewFavorite(conn,userId,rName);
		close(conn);
		return	ru;
	}
	
	public int changePassword(String userId, String password) {
		Connection conn=getConnection();
		int result=new UserDAO().changePassword(conn,userId,password);
		close(conn);
		return	result;
	}
}
