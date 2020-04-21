package chatroom.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

/**
 * Servlet implementation class UserListServlet
 */
@WebServlet("/chat/userList")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.접속자 목록
			Set<String> userSet = HelloWebSocket.clients.keySet();
			Iterator<String> iter = userSet.iterator();
			
			JSONArray jsonArr = new JSONArray();
			while(iter.hasNext()) {
				jsonArr.add(iter.next());
			}
			
			//2.JSON문자열로 응답객체에 출력
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(jsonArr);
		}catch(Exception e) {
			throw e;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
