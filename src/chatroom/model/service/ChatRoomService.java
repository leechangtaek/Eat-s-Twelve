package chatroom.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import chatroom.model.dao.ChatRoomDAO;
import chatroom.model.vo.DM;

public class ChatRoomService {

	public int insertSendMessage(JSONObject obj) {
		
		Connection conn=getConnection();
		int result = 0;
		result=new ChatRoomDAO().insertSendMessage(conn,obj);
	
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
		
	}

	public int countDMNew(String loggedId) {
		
		Connection conn = getConnection();
		int newCnt = 0;
		newCnt = new ChatRoomDAO().countDMNew(conn,loggedId);
		close(conn);
		
		return newCnt;
	}

	public List<DM> selectDMList(String userLoginId, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<DM> list = new ArrayList<>();
		
		list = new ChatRoomDAO().selectDMList(conn, userLoginId, cPage, numPerPage);
		
		close(conn);
		
		return list;
	}

	public int selectDMCount(String userLoginId) {
		
		Connection conn = getConnection();
		int totalContent = 0;
		totalContent = new ChatRoomDAO().selectDMCount(conn,userLoginId);
		close(conn);
		
		return totalContent;
	}

	public int DMReadYN(int dmNo) {

		Connection conn=getConnection();
		int result = 0;
		result=new ChatRoomDAO().DMReadYN(conn,dmNo);
	
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
		
	}

}
