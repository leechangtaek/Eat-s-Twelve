package chatroom.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import chatroom.model.service.ChatRoomService;

/**
 * Servlet implementation class DMSendByDMBoard
 */
@WebServlet("/chat/dmboard/sendDM")
public class DMSendByDMBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.setCharacterEncoding("utf-8");
			
			String sender = request.getParameter("userId");
			String receiver = request.getParameter("receiver");
			String msg = request.getParameter("msg");
			Calendar c = new GregorianCalendar();
			String time = c.get(Calendar.YEAR)+"-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.DATE);
			
			JSONObject obj = new JSONObject();
			obj.put("sender", sender);
			obj.put("receiver", receiver);
			obj.put("msg", msg);
			obj.put("time", time);
			
			int queryResult = new ChatRoomService().insertSendMessage(obj);
			
			String alert = "";
			String loc = "/WEB-INF/views/common/msg.jsp";
			
			if(queryResult>0) {
				alert="쪽지를 성공적으로 전송했습니다.";
			}
			else {
				alert="쪽지 보내기에 실패하였습니다.";
			}
			request.setAttribute("msg", alert);
			request.setAttribute("script", "self.close()");
			request.getRequestDispatcher(loc).forward(request, response);
		} catch(Exception e) {
			throw e;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
