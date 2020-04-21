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
import restaurant.model.service.RestaurantMenuService;
import restaurant.model.service.RestaurantService;
import restaurant.model.vo.Restaurant;
import restaurant.model.vo.RestaurantMap;
import restaurant.model.vo.RestaurantMenu;


@WebServlet("/searchListFilter")
public class SearchListFilterServlet extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		//1.encoding
			request.setCharacterEncoding("utf-8");
			
			//2.parameter handling
			String value = request.getParameter("order");
			List<Restaurant> rList = new RestaurantService().selectOneByRName(value);
			//b-1 r_menu table list 가져오기(Single_yn에 Y가 하나라도 없으면 N)
			List<RestaurantMenu> rmList = new ArrayList<>();
			rmList = new RestaurantMenuService().selectYNByRName(value);
			//c-1 photo table에서 1.png 또는 1.jpg 가져오기
			List<Photo> pList = new ArrayList<>();
			pList = new PhotoService().selectOneByRName(value);
			
			//d-1 map table에서 좌표 가져오기
			List<RestaurantMap> mList = new ArrayList<>();
			mList = new RestaurantService().selectMapOnebyRname(value);
			
			//가져온 객체 jason에 담기
			//rList -> r_name, type, location, best_yn, grade, description, category, rPhone,
			//		parkingYN, openTime, closeTime, holiday
			//rmList -> singleYn
			//pList -> img_name
			JSONArray jArr = new JSONArray();
			
			for(int i=0;i<rList.size();i++) {
				JSONObject obj = new JSONObject();
				obj.put("rName", rList.get(i).getrName());
				obj.put("type", rList.get(i).getType());
				obj.put("location", rList.get(i).getLocation());
				obj.put("bestYN", rList.get(i).getBestYN());
				obj.put("description", rList.get(i).getDescription());
				obj.put("category", rList.get(i).getCategory());
				obj.put("rPhone", rList.get(i).getrPhone());
				obj.put("grade", rList.get(i).getGrade());
				obj.put("parkingYN", rList.get(i).getParkingYN());
				obj.put("openTime", rList.get(i).getOpenTime());
				obj.put("closeTime", rList.get(i).getCloseTime());
				obj.put("holiday", rList.get(i).getHoliday());
				obj.put("singleYN", rmList.get(i).getSingleYN());
				obj.put("imgName", pList.get(i).getImgName());
				obj.put("lat", mList.get(i).getLat());
				obj.put("lng", mList.get(i).getLng());
				
				jArr.add(obj);
			}
			
//			System.out.println(jArr.toString());
			
			//객체 response로 넘기기
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().append(jArr.toString());
		}catch(Exception e) {
			throw e;
		}
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
