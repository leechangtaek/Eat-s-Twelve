package board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Photo.model.service.PhotoService;
import Photo.model.vo.Photo;
import restaurant.model.service.RestaurantMapService;
import restaurant.model.service.RestaurantService;
import restaurant.model.vo.Restaurant;
import restaurant.model.vo.RestaurantMap;
import user.model.service.UserService;
import user.model.vo.User;
import user.model.vo.recentUser;

/**
 * Servlet implementation class BoardRestaurantsViewServlet
 */
@WebServlet("/restaurants")
public class BoardRestaurantsViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.setCharacterEncoding("utf-8");
			
			//파라미터 핸들링
			String rName = request.getParameter("rName");
			HttpSession session = request.getSession();
			
			String userId = "";
			if(session.getAttribute("userLoggedIn")!=null) {
				userId = ((User)session.getAttribute("userLoggedIn")).getUserId();
			}
	
			//2. 업무로직
			UserService userService = new UserService();
			int result = 0;
			if(!userId.equals("")) {
				recentUser ru = new recentUser(userId, rName, null);
				
				result = userService.userRecent(ru);	
				
				recentUser ru2 = new recentUser();
				ru2 = userService.userViewFavorite(userId, rName);
				if(ru2==null) {
					request.setAttribute("check_yn","N");
				}else {
					request.setAttribute("check_yn",ru2.getCheck_yn());
				}
			}
			
			//2. 업무로직
			Restaurant restaurant = new RestaurantService().selectOne(rName);
			List<Photo> photo = new PhotoService().selectPhoto(rName);
			RestaurantMap restaurantMap = new RestaurantMapService().selectOneMap(rName);
			int index = restaurant.getLocation().indexOf("구 ");
			String goo = restaurant.getLocation().substring(index-2, index+1);
			//주변맛집
			List<Restaurant> rList = new ArrayList<>();
			rList = new RestaurantService().nearRestaurant(goo);
			
			String rNames = "";
			if(rList!=null) {
				rNames += rList.get(0).getrName();
				if(rList.size()<4) {
					for(int i=0;i<rList.size();i++) {
						if(i==0) continue;
						rNames += "/" + rList.get(i).getrName();
					}
				}
				else {
					for(int i=1;i<4;i++) {
						rNames += "/" + rList.get(i).getrName();
					}				
				}
			}
			List<Photo> nearPhotos = new ArrayList<>();
			
			nearPhotos = new PhotoService().selectOneByRName(rNames);
	//		C
			request.setAttribute("restaurant", restaurant);
			request.setAttribute("photo", photo);
			request.setAttribute("nearPhotos", nearPhotos);
			request.setAttribute("rList", rList);
	//		request.setAttribute("p", p);
			request.setAttribute("restaurantMap", restaurantMap);
			
			request.getRequestDispatcher("/WEB-INF/views/board/boardView.jsp")
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
