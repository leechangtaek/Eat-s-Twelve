package user.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Photo.model.service.PhotoService;
import Photo.model.vo.Photo;
import restaurant.model.service.RestaurantService;
import restaurant.model.vo.Restaurant;
import user.model.service.UserService;
import user.model.vo.User;

/**
 * Servlet implementation class userFormMainServlet
 */
@WebServlet(urlPatterns ="/user/userForm", name="userFormMainServlet")
public class userFormMainServlet extends HttpServlet {
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
			List<Restaurant> recentRestaurantList = new ArrayList<>();
			List<Photo> recentPhotoList = new ArrayList<>();
			List<Restaurant> wishRestaurantList = new ArrayList<>();
			List<Photo> wishPhotoList = new ArrayList<>();
			List<Restaurant> favoriteRestaurantList = new ArrayList<>();
			List<Restaurant> restaurantTypeList = new ArrayList<>();
			List<Photo> favoritePhotoList = new ArrayList<>();
			RestaurantService restaurantService = new RestaurantService();
			PhotoService photoService = new PhotoService();
			UserService userService = new UserService();
			
			int count =0;
			recentRestaurantList = restaurantService.recentRestaurant(userId);
			if(recentRestaurantList!=null) {
				for(int i=0; i<recentRestaurantList.size(); i++) {
					Photo p = photoService.selectRecentRName(recentRestaurantList.get(i).getrName());
					recentPhotoList.add(p);
					count++;
					if(count==8) {
						break;
					}
				}
			}
			int count1 =0;
			wishRestaurantList = restaurantService.wishRestaurant(userId);
			if(wishRestaurantList!=null) {
				for(int i=0; i<wishRestaurantList.size(); i++) {
					Photo p = photoService.selectRecentRName(wishRestaurantList.get(i).getrName());
					wishPhotoList.add(p);
					count1++;
					if(count1==8) {
						break;
					}
				}
			}
			User u = userService.selectOne(userId);
			String[]userFavorite = u.getFavorite().split(",");
			
			if(userFavorite!=null) {
				for(int i=0; i<userFavorite.length; i++) {
//					System.out.println(userFavorite[i]);
					Restaurant r = restaurantService.favoriteRestaurant(userFavorite[i]);
					favoriteRestaurantList.add(r);
					
				}
			
				System.out.println("123123123123123123"+favoriteRestaurantList.toString());
				for(int j=0; j<favoriteRestaurantList.size(); j++) {
					Photo p = photoService.selectRecentRName(favoriteRestaurantList.get(j).getrName());
					favoritePhotoList.add(p);
				}
//				System.out.println(favoritePhotoList.toString());
				
			}
			
			
			
			request.setAttribute("recentRestaurantList", recentRestaurantList);
			request.setAttribute("recentPhotoList", recentPhotoList);
			request.setAttribute("wishRestaurantList", wishRestaurantList);
			request.setAttribute("wishPhotoList", wishPhotoList);
			request.setAttribute("favoriteRestaurantList", favoriteRestaurantList);
			request.setAttribute("favoritePhotoList", favoritePhotoList);
			
			RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/user/userView.jsp");
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
