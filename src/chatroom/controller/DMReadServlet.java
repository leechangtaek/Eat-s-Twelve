package chatroom.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chatroom.model.service.ChatRoomService;

/**
 * Servlet implementation class DMReadServlet
 */
@WebServlet("/chat/dmRead")
public class DMReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			int dmNo = Integer.parseInt((String)request.getParameter("dmNo"));
	//		System.out.println(dmNo);
			int result = new ChatRoomService().DMReadYN(dmNo);
	//		
			response.setContentType("text/csv; charset=utf-8");
			response.getWriter().append(String.valueOf(result));
		}catch(Exception e) {
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
