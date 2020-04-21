package chatroom.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HonbabChatRoomServlet
 */
@WebServlet(urlPatterns="/honbab/honbabChatRoom", name="HonbabChatRoomServlet")
public class HonbabChatRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.parmeterhandling
			String userId = request.getParameter("userId");
					
			//2.아이디 세션 속성등록
			//@ServerEndPoint
			//Configurator 구현 클래스: websocket session에서 사용할 사용자 아이디를 http session에서 가져와 등록
			HttpSession session = request.getSession();
			session.setAttribute("userId", userId);
			//3.view단처리
			request.getRequestDispatcher("/WEB-INF/views/honbabChatRoom/honbabChatRoom.jsp").forward(request, response);
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
