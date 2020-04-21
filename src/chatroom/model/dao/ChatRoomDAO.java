package chatroom.model.dao;

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

import org.json.simple.JSONObject;

import board.model.vo.Board;
import chatroom.model.vo.DM;
import common.exception.MyException;

public class ChatRoomDAO {
	
	private Properties prop=new Properties();
	public ChatRoomDAO() {
		
		String fileName=ChatRoomDAO.class.getResource("/sql/chatroom/chatroom-query.properties").getPath();
	
		try {
			prop.load(new FileReader(fileName));
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int insertSendMessage(Connection conn, JSONObject obj) {
		
		int result = 0;
		
		String msg = (String)obj.get("msg");
		String receiver = (String)obj.get("receiver");
		String sender = (String)obj.get("sender");
		String time = (String)obj.get("time");
		
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertSendMessage");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, receiver);
			pstmt.setString(2, msg);
			pstmt.setString(3, sender);
			pstmt.setString(4, time);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MyException("쪽지 전송실패!",e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int countDMNew(Connection conn, String loggedId) {
		PreparedStatement pstmt = null;
		int newCnt = 0;
		ResultSet rset = null;
		String query = prop.getProperty("countDMNew");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, loggedId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				newCnt = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MyException("최근쪽지 조회 실패",e);
		} finally {
			close(rset);
			close(conn);
		}
		
		
		
		return newCnt;
	}

	public List<DM> selectDMList(Connection conn, String userLoginId, int cPage, int numPerPage) {
		
		List<DM> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectDMList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userLoginId);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				DM dm = new DM();
				
				dm.setDmNo(rset.getInt("dm_no"));
				dm.setUserId(rset.getString("user_id"));
				dm.setMsg(rset.getString("msg"));
				dm.setSender(rset.getString("sender"));
				dm.setTime(rset.getString("time"));
				dm.setCheck_yn(rset.getString("check_yn"));
				list.add(dm);
			}
//			System.out.println("list@adminDao="+list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MyException("DM조회 실패!",e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return list;
		
	}

	public int selectDMCount(Connection conn, String userLoginId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectDMCount");
		int totalContent = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userLoginId);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				totalContent = rset.getInt("cnt");
			}
//			System.out.println("list@adminDao="+list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MyException("DM조회 실패!, 관리자에게 문의하세요.",e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}

	public int DMReadYN(Connection conn, int dmNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("DMReadYN");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, dmNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MyException("읽은 목록 갱신 실패, 관리자에게 문의하세요!",e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
