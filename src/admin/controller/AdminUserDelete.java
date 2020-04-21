package admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;

/**
 * Servlet implementation class AdminUserDelete
 */
@WebServlet("/user/userDelete")
public class AdminUserDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			request.setCharacterEncoding("utf-8");
		
			String id=request.getParameter("Id");
			AdminService adminService=new AdminService();
			int result =adminService.adminDelete(id);
					String view = "/WEB-INF/views/common/msg.jsp";
					String msg = "";
					//javascript/html���� ����� url�� contextPath�� �����Ѵ�.
					String loc = "/admin/adminList";
	
					if(result>0)
						msg = "회원삭제 성공!";
						
					else 
						msg = "회원삭제 실패!";	
					
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
