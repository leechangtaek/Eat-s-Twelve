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

/**
 * Servlet implementation class ListPageServlet
 */
@WebServlet("/listPage")
public class ListPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.setCharacterEncoding("utf-8");
			
			//한식 20곳
			List<Photo> kList = new ArrayList<>();
			kList = new PhotoService().searchByType20("한식");
			JSONArray kr = new JSONArray();
			for(int i=0;i<kList.size();i++) {
				JSONObject obj = new JSONObject();
				obj.put("rName", kList.get(i).getrName());
				obj.put("location", kList.get(i).getLocation());
				obj.put("imgNo", kList.get(i).getImgNo());
				obj.put("imgName", kList.get(i).getImgName());
				
				kr.add(obj);
			}
			//중식 20곳
			List<Photo> cList = new ArrayList<>();
			cList = new PhotoService().searchByType20("중식");
			
			JSONArray chn = new JSONArray();
			for(int i=0;i<cList.size();i++) {
				JSONObject obj = new JSONObject();
				obj.put("rName", cList.get(i).getrName());
				obj.put("location", cList.get(i).getLocation());
				obj.put("imgNo", cList.get(i).getImgNo());
				obj.put("imgName", cList.get(i).getImgName());
				
				chn.add(obj);
			}
			//일식 20곳
			List<Photo> jList = new ArrayList<>();
			jList = new PhotoService().searchByType20("일식");
			JSONArray jpn = new JSONArray();
			for(int i=0;i<jList.size();i++) {
				JSONObject obj = new JSONObject();
				obj.put("rName", jList.get(i).getrName());
				obj.put("location", jList.get(i).getLocation());
				obj.put("imgNo", jList.get(i).getImgNo());
				obj.put("imgName", jList.get(i).getImgName());
				
				jpn.add(obj);
			}
			//양식 20곳
			List<Photo> wList = new ArrayList<>();
			wList = new PhotoService().searchByType20("양식");
			JSONArray wst = new JSONArray();
			for(int i=0;i<wList.size();i++) {
				JSONObject obj = new JSONObject();
				obj.put("rName", wList.get(i).getrName());
				obj.put("location", wList.get(i).getLocation());
				obj.put("imgNo", wList.get(i).getImgNo());
				obj.put("imgName", wList.get(i).getImgName());
				
				wst.add(obj);
			}
			
			
			
			request.setAttribute("kr", kr.toString());
			request.setAttribute("jpn", jpn.toString());
			request.setAttribute("chn", chn.toString());
			request.setAttribute("wst", wst.toString());
			
			request.getRequestDispatcher("/WEB-INF/views/listpage/listPage.jsp").forward(request, response);
		}catch(Exception e) {
			throw e;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
