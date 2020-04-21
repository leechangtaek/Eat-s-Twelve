package admin.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import admin.model.service.AdminService;

/**
 * Servlet implementation class AdminInquireServlet
 */
@WebServlet("/admin/adminInquire")
public class AdminInquireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String accept=request.getParameter("accept");
	
		final int numPerPage = 5;
		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));	
		}catch(NumberFormatException e) {
			
		}
			AdminService adminService=new AdminService();
			List<Map<String,Object>> list =adminService.inquire(cPage,numPerPage,accept);
			System.out.println("sdfsad"+list);
			int totalContent = adminService.inquire_count(accept);
			int totalPage =  (int)Math.ceil((double)totalContent/numPerPage);//(����2)
			String pageBar = "";
			int pageBarSize = 5;
			int pageStart = ((cPage-1)/pageBarSize)*pageBarSize+1;//(����3)
			int pageEnd = pageStart+pageBarSize-1;
			int pageNo = pageStart;
			
			if(accept.equals("Y"))
			{
			if(pageNo == 1 ){
				//pageBar += "<span>[����]</span>";
			}
			else {
				pageBar += "<input type='button' value='[이전]' onclick='call("+(pageNo-1)+");' style='border:none; background-color:white;'/>";
				//pageBar += "<a href='' onclick='call("+pageNo+")'>"+(pageNo-1)+"</a> ";
//				pageBar += "<a href='"+request.getContextPath()+"/admin/adminList?cPage="+(pageNo-1)+"'>[이전]</a> ";
			}
			// pageNo section
			while(pageNo<=pageEnd && pageNo<=totalPage){
				if(cPage == pageNo ){
					pageBar += "<span class='cPage'>"+cPage+"</span> ";
				}
				else {
					pageBar += "<input type='button' value="+pageNo+" onclick='call("+pageNo+");' style='border:none; background-color:white;'/>";
//					pageBar += "<a href='#' onclick='call("+pageNo+")'>"+(pageNo)+"</a> ";
//					pageBar += "<span onclick='call('+cPage+');'>"+pageNo+"</span> ";
				}
				pageNo++;
			}
			//[����] section
			if(pageNo > totalPage){
			} else {
				pageBar += "<input type='button' value='[더보기]' onclick='call("+pageNo+");' style='border:none; background-color:white;'/>";
//				pageBar += "<input type='button' value='더보기' onclick='call("+pageNo+");' style='border:none; background-color:white;'/>";
				//pageBar += "<a href='"+request.getContextPath()+"/admin/adminvisit?cPage="+pageNo+"'>[더보기]</a>";
			}
			}else {
				if(pageNo == 1 ){
					//pageBar += "<span>[����]</span>";
				}
				else {
					pageBar += "<input type='button' value='[이전]' onclick='call1("+(pageNo-1)+");' style='border:none; background-color:white;'/>";
					//pageBar += "<a href='' onclick='call("+pageNo+")'>"+(pageNo-1)+"</a> ";
//					pageBar += "<a href='"+request.getContextPath()+"/admin/adminList?cPage="+(pageNo-1)+"'>[이전]</a> ";
				}
				// pageNo section
				while(pageNo<=pageEnd && pageNo<=totalPage){
					if(cPage == pageNo ){
						pageBar += "<span class='cPage'>"+cPage+"</span> ";
					}
					else {
						pageBar += "<input type='button' value="+pageNo+" onclick='call1("+pageNo+");' style='border:none; background-color:white;'/>";
//						pageBar += "<a href='#' onclick='call("+pageNo+")'>"+(pageNo)+"</a> ";
//						pageBar += "<span onclick='call('+cPage+');'>"+pageNo+"</span> ";
					}
					pageNo++;
				}
				//[����] section
				if(pageNo > totalPage){
				} else {
					pageBar += "<input type='button' value='[더보기]' onclick='call1("+pageNo+");' style='border:none; background-color:white;'/>";
//					pageBar += "<input type='button' value='더보기' onclick='call("+pageNo+");' style='border:none; background-color:white;'/>";
					//pageBar += "<a href='"+request.getContextPath()+"/admin/adminvisit?cPage="+pageNo+"'>[더보기]</a>";
				}
			}
		JSONArray jsonArr = new JSONArray();
		System.out.println("pageBar="+pageBar);
		for(Map<String,Object> map: list){
				JSONObject json = new JSONObject();
				json.put("id", map.get("id"));
				json.put("r_name", map.get("r_name"));
				json.put("type", map.get("type"));
				json.put("location", map.get("location"));
				json.put("description", map.get("description"));
				json.put("category", map.get("category"));
				json.put("r_phone", map.get("r_phone"));
				json.put("parking_yn", map.get("parking_yn"));
				json.put("open_time", map.get("open_time"));
				json.put("close_time", map.get("close_time"));
				json.put("holiday", map.get("holiday"));
				json.put("inq_date", map.get("inq_date").toString());
				json.put("accept_yn", map.get("accept_yn"));
				
				json.put("pageBar", pageBar);
				jsonArr.add(json);
		}
		System.out.println("rankList="+jsonArr);
		
//		map.put("id", rset.getString("id"));
//		map.put("r_name", rset.getString("r_name"));
//		map.put("type", rset.getString("type"));
//		map.put("location",rset.getString("location"));
//		map.put("description",rset.getString("description"));
//		map.put("category",rset.getString("category"));
//		map.put("r_phone",rset.getString("r_phone"));
//		map.put("parking_yn",rset.getString("parking_yn"));
//		map.put("open_time",rset.getInt("open_time"));
//		map.put("close_time",rset.getInt("close_time"));
//		map.put("inq_date",rset.getDate("inq_date"));
//		map.put("accept_yn",rset.getDate("accept_yn"));
		
		
		
		
		//4.���䰴ü�� ���
		response.setContentType("application/json; charset=utf-8"); 
//		PrintWriter out = response.getWriter();
//		out.print(jsonArr);
		response.getWriter().append(jsonArr.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}