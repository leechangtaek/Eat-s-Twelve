package chatroom.controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import chatroom.model.service.ChatRoomService;

/**
 * Servlet implementation class DMSendServlet
 */
@WebServlet(urlPatterns="/chat/sendDM", name="DMSendServlet")
public class DMSendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1. encoding
			request.setCharacterEncoding("utf-8");
			response.setContentType("application/json; charset=utf-8");
			
			//2. parameter
			String dm = request.getParameter("dm");
			System.out.println("dm=="+dm);
			JSONParser parser = new JSONParser();
			JSONObject obj = null;
			try {
				obj = (JSONObject)parser.parse(dm);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//3. business
			String receiver = (String)obj.get("receiver");
	//		System.out.println(receiver);
			Set<String> clientSet = HelloWebSocket.clients.keySet();
			String result = "";
			int queryResult = 0;
			
			if(clientSet.contains(receiver)) {
				Session receiverSession = HelloWebSocket.clients.get(receiver);
				
				receiverSession.getBasicRemote().sendText(dm);
				
				System.out.println(obj);
				queryResult = new ChatRoomService().insertSendMessage(obj);
				if(queryResult>0) {
					result="쪽지를 성공적으로 전송했습니다.";
				}
				else {
					result="쪽지 보내기에 실패하였습니다.";
				}
			}else {
				result="해당 사용자가 현재 접속중이 아닙니다.";
			}			
		
			
			//4.view(json)
			JSONObject resultObj = new JSONObject();
			resultObj.put("result", result);
			
			response.getWriter().print(resultObj);
		}catch(Exception e) {
			throw e;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
