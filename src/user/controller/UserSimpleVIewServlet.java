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
 * Servlet implementation class UserSimpleVIewServlet
 */
@WebServlet(urlPatterns="/user/userSimpleView", name="UserSimpleVIewServlet")
public class UserSimpleVIewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			
			String userId = request.getParameter("userId");
			
			User u = new UserService().selectOne(userId);
			
			request.setAttribute("user", u);
			
			request.getRequestDispatcher("/WEB-INF/views/user/userSimpleView.jsp").forward(request, response);
		}catch(Exception e) {
			throw e;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
