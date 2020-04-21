package user.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import comment.model.service.CommentService;
import comment.model.vo.Comment;
import user.model.service.UserService;
import user.model.vo.User;

/**
 * Servlet implementation class userViewServlet
 */
@WebServlet("/user/userCommentView")
public class userCommentViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.encoding
			request.setCharacterEncoding("utf-8");
			
			//2.parameter handling
			String userId = request.getParameter("userId");
			//3.business logic
			List<Comment> comment = new UserService().selectComment(userId);
			
			
		
				request.setAttribute("comment",comment);
				
				RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/user/userCommentView.jsp");
				
				
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
