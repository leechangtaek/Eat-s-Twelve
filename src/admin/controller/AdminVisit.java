package admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import admin.model.dao.AdminDAO;
import admin.model.service.AdminService;
import user.model.vo.User;

/**
 * Servlet implementation class AdminVisit
 */
@WebServlet("/admin/adminvisit")
public class AdminVisit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		
			request.setCharacterEncoding("utf-8");
			final int numPerPage = 18;
			int cPage = 1;
			try {
				cPage = Integer.parseInt(request.getParameter("cPage"));	
			}catch(NumberFormatException e) {
				
			}
				AdminService adminService=new AdminService();
				List<Map<String,Object>> list =adminService.adminVisit(cPage,numPerPage);
				
				int totalContent = adminService.selectUserCount();
				int totalPage =  (int)Math.ceil((double)totalContent/numPerPage);//(����2)
				String pageBar = "";
				int pageBarSize = 5;
				int pageStart = ((cPage-1)/pageBarSize)*pageBarSize+1;//(����3)
				int pageEnd = pageStart+pageBarSize-1;
				int pageNo = pageStart;
				if(pageNo == 1 ){//1~5
					//pageBar += "<span>[����]</span>";
				}
				else {//1페이지가 아니면
					pageBar += "<input type='button' value='[이전]' onclick='call("+(pageNo-1)+");' style='border:none; background-color:white;'/>";
//				pageBar += "<a href='' onclick='call("+pageNo+")'>[이전]</a> ";
//					pageBar += "<a href='"+request.getContextPath()+"/admin/adminList?cPage="+(pageNo-1)+"'>[이전]</a> ";
				}
				// pageNo section
				while(pageNo<=pageEnd && pageNo<=totalPage){
					if(cPage == pageNo ){
					//	pageBar+="<input type='button' value="+pageNo+" onclick='call("+pageNo+");' style='border:none; background-color:white;'/>";
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
//					pageBar += "<a href='"+request.getContextPath()+"/admin/adminvisit?cPage="+pageNo+"'>[더보기]</a>";
				}
			
			JSONArray jsonArr = new JSONArray();
			for(Map<String,Object> map: list){
					JSONObject json = new JSONObject();
					json.put("id", map.get("id"));
					json.put("total", map.get("total"));
					json.put("visit_time", map.get("visit_time"));
					json.put("pageBar", pageBar);
					jsonArr.add(json);
			}
			
			//4.���䰴ü�� ���
			response.setContentType("application/json; charset=utf-8"); 
	//		PrintWriter out = response.getWriter();
	//		out.print(jsonArr);
			response.getWriter().append(jsonArr.toString());
		} catch(Exception e) {
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
