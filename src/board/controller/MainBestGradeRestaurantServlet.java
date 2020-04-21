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
import restaurant.model.service.RestaurantService;
import restaurant.model.vo.Restaurant;

/**
 * Servlet implementation class MainBestReviewServlet
 */
@WebServlet("/main/bestGradeRestaurants")
public class MainBestGradeRestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
request.setCharacterEncoding("utf-8");
		
		//평점순
		List<Restaurant> rList = new ArrayList<>();
		rList = new RestaurantService().selectBestGrade6List();
		String value = "";
		
		for(int i=0;i<rList.size();i++) {
			if(i==0) {
				value = rList.get(i).getrName();
			}else {
				value += "/"+rList.get(i).getrName();
			}
		}
		
		List<Photo> pList = new ArrayList<>();
		pList = new PhotoService().selectOneByRName(value);
		JSONArray jArr = new JSONArray();
		
		for(int i=0;i<rList.size();i++) {
			JSONObject obj = new JSONObject();
			
			obj.put("rName", rList.get(i).getrName());
			obj.put("type", rList.get(i).getType());
			obj.put("location", rList.get(i).getLocation());
			obj.put("bestYN", rList.get(i).getBestYN());
			obj.put("description", rList.get(i).getDescription());
			obj.put("category", rList.get(i).getCategory());
			obj.put("grade", rList.get(i).getGrade());
			obj.put("imgName", pList.get(i).getImgName());
			jArr.add(obj);
		}
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().append(jArr.toString());
		System.out.println(jArr.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
