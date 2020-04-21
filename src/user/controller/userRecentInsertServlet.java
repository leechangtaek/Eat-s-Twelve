package user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.model.service.UserService;
import user.model.vo.recentUser;

/**
 * Servlet implementation class userRecentInsertServlet
 */
@WebServlet(urlPatterns="/user/userRecent", name="userRecentInsertSevlet")
public class userRecentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String userId = request.getParameter("userId");
			String r_name = request.getParameter("r_name");
			
			UserService userService = new UserService();
			recentUser ru = new recentUser(userId, r_name, null);
			
			int result = userService.userRecent(ru);
			
			String msg="";
			String loc ="/";
			String view="";
			if(result>0){
				view = "/WEB-INF/views/board/boardView.jsp";
				request.setAttribute("r_name", r_name);
			}
			else{
				msg = "최신식당등록실패";
				view = "/WEB-INF/views/common/msg.jsp";
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
			}
			
			RequestDispatcher reqDispatcher = request.getRequestDispatcher(view);
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
