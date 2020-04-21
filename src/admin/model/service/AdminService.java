package admin.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import Photo.model.vo.Upload;
import admin.model.dao.AdminDAO;
import restaurant.model.vo.Inquire;
import user.model.vo.User;

public class AdminService {

	public List<User> selectUserList(int cPage, int numPerPage) {
		Connection conn=getConnection();
		List<User>list=new AdminDAO().selectUserList(conn,cPage,numPerPage);
		close(conn);
		return	list;
	}

	public int selectMemberCount() {
		Connection conn=getConnection();
		int result=new AdminDAO().selectMemberCount(conn);
		close(conn);
		return	result;
	}

	public List<User> selectByNameId(String srchName) {
		Connection conn=getConnection();
		List<User>list=new AdminDAO().selectByNameId(conn,srchName);
		close(conn);
		return	list;
	}

	public User searchById(String id) {
		Connection conn=getConnection();
		User user=new AdminDAO().searchById(conn,id);
		close(conn);
		return	user;
	}

	public int insertId(String memberId, String time) {
		Connection conn=getConnection();
		int result=new AdminDAO().insertId(conn,memberId,time);
		close(conn);
		return result;
	}

	public List<Map<String, Object>> adminVisit(int cPage, int numPerPage) {
		Connection conn=getConnection();
		List<Map<String, Object>> list=new AdminDAO().adminVisit(conn,cPage,numPerPage);
		close(conn);
		return list;
	}

	public List<Map<String, Object>> totalvisit() {
		Connection conn=getConnection();
		
		List<Map<String, Object>> list=new AdminDAO().totalvisit(conn);
		close(conn);
		return list;
	}

	public int adminDelete(String id) {
		Connection conn=getConnection();
		int result=new AdminDAO().adminDelete(conn,id);
		close(conn);
		return result;
	}

	public int selectUserCount() {
		Connection conn=getConnection();
		int result=new AdminDAO().selectUserCount(conn);
		close(conn);
		return	result;
	}

	public List<Map<String, Object>> inquire(int cPage, int numPerPage, String accept) {
		Connection conn=getConnection();
		List<Map<String, Object>> list=new AdminDAO().inquire(conn,cPage,numPerPage, accept);
		close(conn);
		return list;
	}

	public int inquire_count(String accept) {
		Connection conn=getConnection();
		int result=new AdminDAO().inquire_count(conn,accept);
		close(conn);
		return	result;
	}

	public List<Upload> searchByr_name(String r_name) {
		Connection conn=getConnection();
		List<Upload> upload=new AdminDAO().searchByr_name(conn,r_name);
		close(conn);
		return upload;
	}

	public int admit(String r_name) {
		Connection conn=getConnection();
		int result=new AdminDAO().admit(conn,r_name);
		close(conn);
		return	result;
	}

	public Inquire inqInfo(String r_name) {
		Connection conn=getConnection();
		Inquire inq=new AdminDAO().inqInfo(conn,r_name);
		close(conn);
		return inq;
	}

	public List<Upload> getUpload(String r_name) {
		Connection conn=getConnection();
		List<Upload> inq=new AdminDAO().getUpload(conn,r_name);
		close(conn);
		return inq;
	}

	public int insertPhoto(Upload u) {
		Connection conn=getConnection();
		int result2=new AdminDAO().insertPhoto(conn,u);
		close(conn);
		return	result2;
	}

	public int refuse(String r_name) {
		Connection conn=getConnection();
		int result=new AdminDAO().refuse(conn,r_name);
		close(conn);
		return	result;
	}

}
