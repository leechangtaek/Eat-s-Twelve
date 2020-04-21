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
 * Servlet implementation class userDeleteServlet
 */
@WebServlet("/user/delete")
public class userDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			//2.prameter
			String userId = request.getParameter("userId");
			
			//3.business logic
			UserService userService = new UserService();
			
			int result = userService.deleteUser(userId);
			//4.view단 처리 : msg.jsp -> /mvc
			String msg="";
			String loc = "/";
			if(result>0){
				msg = "회원탈퇴 성공!";
				User userLoggedIn = (User)request.getSession().getAttribute("userLoggedIn");
				if(!"admin".equals(userLoggedIn.getUserId())) {
					
					loc = "/user/logout";
				}
			}
			else{
				msg = "회원탈퇴 실패!";
			}
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
			RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			reqDispatcher.forward(request,response);
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
