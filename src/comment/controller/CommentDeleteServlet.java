package comment.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.model.service.CommentService;

/**
 * Servlet implementation class BoardCommentDeleteServlet
 */
@WebServlet("/comment/commentDelete")
public class CommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 파라미터값 가져오기
		try {
			int boardNo = 0;
			
			if(request.getParameter("boardNo")!=null) {
				boardNo = Integer.parseInt((String)request.getParameter("boardNo"));
			}
			
			String rName = request.getParameter("rName");
			
			int result = new CommentService().deleteComment(boardNo);
			
			String view = "/WEB-INF/views/common/msg.jsp";
			String msg = "";
			String loc = "/restaurants?rName="+rName;
	
			if(result>0)
				msg = "댓글 삭제 성공!";
				
			else 
				msg = "댓글 삭제 실패!";	
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
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
