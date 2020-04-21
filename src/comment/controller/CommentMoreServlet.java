package comment.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import comment.model.service.CommentService;
import comment.model.vo.Comment;

/**
 * Servlet implementation class PhotoMoreServlet
 */
@WebServlet("/comment/commentMore")
public class CommentMoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			// 1.parameterHandling
			int cPage = Integer.parseInt(request.getParameter("cPage"));
			final int numPerPage = 5;
			
			// 2.businessLogic
			List<Comment> list = new CommentService().selectCommentMore(cPage, numPerPage);
			 
			// 3.view단처리: json
			JSONArray jsonArray = new JSONArray();
			for(Comment c : list) {
				JSONObject jsonComment = new JSONObject();
				jsonComment.put("boardNo", c.getBoardNo());
				jsonComment.put("id", c.getId());
				jsonComment.put("rName", c.getrName());
				jsonComment.put("location", c.getLocation());
				jsonComment.put("boarding", c.getBoarding());
				jsonComment.put("grade", c.getGrade());
				jsonComment.put("bDate", c.getbDate().toString()); // toString호출하지 않으면 클라이언트에서 json parsing 오류남.
				jsonComment.put("boardCommentLevel", c.getBoardCommentLevel());
				jsonComment.put("boardCommentRef", c.getBoardCommentRef());
				jsonComment.put("OriginalFileName", c.getOriginalFileName());
				jsonComment.put("RenamedFileName", c.getRenamedFileName());
				jsonArray.add(jsonComment);
			}
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(jsonArray);
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
