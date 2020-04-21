package restaurant.controller;

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
import restaurant.model.service.RestaurantService;
import restaurant.model.vo.Restaurant;


/**
 * Servlet implementation class recentRestaurantServlet
 */
@WebServlet("/restaurant/recentRestaurant")
public class recentRestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			
			String userId = request.getParameter("userId");
			
			List<Restaurant> restaurantList = new ArrayList<>();
			List<Photo> photoList = new ArrayList<>();
			RestaurantService restaurantService = new RestaurantService();
			PhotoService photoService = new PhotoService();
			
			restaurantList = restaurantService.recentRestaurant(userId);
			if(restaurantList!=null) {
				for(int i=0; i<restaurantList.size(); i++) {
					Photo p = photoService.selectRecentRName(restaurantList.get(i).getrName());
					photoList.add(p);
				}
			}
	
			JSONArray jsonArray = new JSONArray();
			int count =0;
			for(int i=0; i<photoList.size(); i++) {
				JSONObject jsonPhoto = new JSONObject();
				jsonPhoto.put("rName", restaurantList.get(i).getrName());
				jsonPhoto.put("grade", restaurantList.get(i).getGrade());
				jsonPhoto.put("location", restaurantList.get(i).getLocation());
				jsonPhoto.put("type", restaurantList.get(i).getType());
				jsonPhoto.put("imgName", photoList.get(i).getImgName());
				jsonArray.add(jsonPhoto);
				count++;
				if(count==5) {
					break;
				}
			}
	
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().append(jsonArray.toString());
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
