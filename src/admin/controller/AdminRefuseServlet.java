package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Photo.model.vo.Upload;
import admin.model.service.AdminService;

/**
 * Servlet implementation class AdminRefuseServlet
 */
@WebServlet("/admin/refuse")
public class AdminRefuseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.setCharacterEncoding("utf-8");
			String r_name=request.getParameter("r_name");
			AdminService adminService=new AdminService();
			int result =adminService.refuse(r_name);
			
		
			String view = "/WEB-INF/views/common/msg.jsp";
			String msg = "";
			
			String loc = "/admin/adminInq";
	
			if(result>0)
			{
				msg = "거절하셨습니다.";
			}
			else
			{
				msg = "거절실패";	
			}
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
			RequestDispatcher reqDispatcher = request.getRequestDispatcher(view);
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
