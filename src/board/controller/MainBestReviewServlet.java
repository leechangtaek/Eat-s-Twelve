package board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Photo.model.service.PhotoService;
import Photo.model.vo.Photo;
import comment.model.service.CommentService;
import comment.model.vo.Comment;

/**
 * Servlet implementation class MainBestReviewServlet
 */
@WebServlet("/main/bestReview")
public class MainBestReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//베스트리뷰
		List<Comment> cList = new ArrayList();
		cList = new CommentService().selectBestReview4List();
		String value = "";

		
		for(int i=0; i<cList.size();i++) {
			if(i==0) {
				value = cList.get(i).getrName();
			} else {
				value += "/"+cList.get(i).getrName();
			}
		}
		
		List<Photo> pList = new ArrayList<>();
		pList = new PhotoService().selectOneByRName(value);
		JSONArray jArr = new JSONArray();
		
		for(int i=0;i<cList.size();i++) {
			JSONObject obj = new JSONObject();
			obj.put("rName", cList.get(i).getrName());
			obj.put("grade", cList.get(i).getGrade());
			obj.put("id", cList.get(i).getId());
			obj.put("boarding", cList.get(i).getBoarding());
			obj.put("bDate", (cList.get(i).getbDate()).toString());
			obj.put("imgName", pList.get(i).getImgName());
			jArr.add(obj);
		}
		
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().append(jArr.toString());
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
