 	package user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.model.service.UserService;
import user.model.vo.User;

/**
 * Servlet implementation class userCheckIdDuplicate
 */
@WebServlet("/user/checkIdDuplicate")
public class userCheckIdDuplicate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.encoding
			request.setCharacterEncoding("utf-8");
			
			//2.parameter handling
			String uid = request.getParameter("uid");
//			System.out.println("userId@checkIdDuplicateServlet="+uid);		
			
			//3.business logic
			User u = new UserService().selectOne(uid);
		
			boolean isUsable = u==null?true:false;
			
			//4.view단 처리
			request.setAttribute("isUsable", isUsable);
			request.getRequestDispatcher("/WEB-INF/views/user/checkIdDuplicate.jsp")
				   .forward(request, response);
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
