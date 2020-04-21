package user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.model.service.UserService;
import user.model.vo.User;
import user.model.vo.recentUser;

/**
 * Servlet implementation class userFavoriteInsertServlet
 */
@WebServlet("/user/userFavorite")
public class userFavoriteInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			HttpSession session = request.getSession();
			String userId = ((User)session.getAttribute("userLoggedIn")).getUserId();
			String r_name = request.getParameter("rName");

			UserService userService = new UserService();
			recentUser ru = new recentUser(userId, r_name, null);

			int result = userService.userFavorite(ru);
			
			String msg="";
			String loc ="/";
			String view="";
			String check_yn ="Y";
			if(result>0){
				msg="'"+r_name+"' 을 찜목록에 넣으셨습니다.";
				loc="/restaurants?rName="+r_name+"&check_yn="+check_yn;
				view = "/WEB-INF/views/common/msg.jsp";
			}
			else{
				msg = "찜등록실패";
				view = "/WEB-INF/views/common/msg.jsp";
			}
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);

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
