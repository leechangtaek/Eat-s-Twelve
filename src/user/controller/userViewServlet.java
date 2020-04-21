package user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.model.service.UserService;
import user.model.vo.User;

/**
 * Servlet implementation class userViewServlet
 */
@WebServlet("/user/userView")
public class userViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.encoding
		try {
				request.setCharacterEncoding("utf-8");
				
				//2.parameter handling
				String userId = request.getParameter("userId");
				
				//3.business logic
				User u = new UserService().selectOne(userId);
//				System.out.println("member@memberViewServlet="+m);
				
				
				//4.view단 처리
				String view = "";
				//조호된 회원이 있는 경우
				if(u != null) {
					view = "/WEB-INF/views/user/userInfoView.jsp";
					
					request.setAttribute("user", u);
				}
				//조회된 회원이 없는 경우
				else {
					view = "/WEB-INF/views/common/msg.jsp";
					
					String loc = "/";
					String msg = "해당회원이 없습니다.";
					request.setAttribute("msg", msg);
					request.setAttribute("loc", loc);
				}
				
				RequestDispatcher reqDispatcher = request.getRequestDispatcher(view);
				reqDispatcher.forward(request, response);
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
