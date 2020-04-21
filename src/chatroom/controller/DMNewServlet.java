package chatroom.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chatroom.model.service.ChatRoomService;
import user.model.vo.User;

/**
 * Servlet implementation class DMNewServlet
 */
@WebServlet("/chat/dmNew")
public class DMNewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.setCharacterEncoding("utf-8");
			
			String userLoginId = "";
			int newCnt = 0;
			
			userLoginId = request.getParameter("userLoginId");
			newCnt = new ChatRoomService().countDMNew(userLoginId);
			response.setContentType("text/csv; charset=utf-8");
			response.getWriter().append(String.valueOf(newCnt));
			
		} catch(Exception e) {
			throw e;
		}
		
		
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
