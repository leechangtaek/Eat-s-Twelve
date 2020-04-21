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
 * Servlet implementation class CommentListServlet
 */
@WebServlet("/comment/commentList")
public class CommentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
	    	CommentService commentService = new CommentService();
	    	
	    	//1.파라미터 핸들링
	    	String rName = request.getParameter("rName");
			List<Comment> list = commentService.selectCommentList(rName); 
			
			
			JSONArray jArr = new JSONArray();
			
			for(int i=0;i<list.size();i++) {
				String renamedFileName = "";
				if(list.get(i).getRenamedFileName()!=null) {
					renamedFileName = list.get(i).getRenamedFileName();
				}
				JSONObject obj = new JSONObject();
				obj.put("boardNo", list.get(i).getBoardNo());
				obj.put("id", list.get(i).getId());
				obj.put("rName", list.get(i).getrName());
				obj.put("location", list.get(i).getLocation());
				obj.put("boarding", list.get(i).getBoarding());
				obj.put("grade", list.get(i).getGrade());
				obj.put("bDate", (list.get(i).getbDate()).toString());
				obj.put("boardCommentLevel", list.get(i).getBoardCommentLevel());
				obj.put("boardCommentRef", list.get(i).getBoardCommentRef());
				obj.put("originalFileName", list.get(i).getOriginalFileName());
				obj.put("renamedFileName", renamedFileName);
				
				jArr.add(obj);
			}
			
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().append(jArr.toString());
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
