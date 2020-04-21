package restaurant.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import restaurant.model.service.RestaurantService;
import restaurant.model.vo.Restaurant;


/**
 * Servlet implementation class autoSearchServlet
 */
@WebServlet("/restaurant/autoSearch")
public class autoSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/csv; charset=utf-8");
			List<Restaurant> foodList = new ArrayList<>();
			RestaurantService restaurantService = new RestaurantService();
			
			String srchFood = request.getParameter("srchFood");
//			System.out.println("srchFood="+srchFood);
			
			if(srchFood.trim().isEmpty()) return;
			
			foodList = restaurantService.selectByFood(srchFood);
			
			StringBuilder csv = new StringBuilder();
			if(foodList!=null && !foodList.isEmpty()) {
				for(int i=0; i<foodList.size(); i++) {
					csv.append("<span id="+"searchIcon"+"></span>"+foodList.get(i).getrName()+"&nbsp;&nbsp;/&nbsp;&nbsp;");
					csv.append(foodList.get(i).getLocation()+"&nbsp;&nbsp;/&nbsp;&nbsp;");
					csv.append(foodList.get(i).getType()+"&nbsp;&nbsp;/&nbsp;&nbsp;");
					csv.append(foodList.get(i).getCategory()+"<hr>^");
					if(i==5) {
						break;
					}
				}
			}
			request.setAttribute("foodList", foodList);
			response.getWriter().append(csv);		
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
