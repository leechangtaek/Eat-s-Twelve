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
import restaurant.model.vo.Inquire;
import user.model.vo.User;

/**
 * Servlet implementation class AdminChkServlet
 */
@WebServlet("/admin/adminInqChk")
public class AdminChkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			String r_name=request.getParameter("r_name");
			
			AdminService adminService=new AdminService();
			Inquire inq=adminService.inqInfo(r_name);
			List<Upload> upload=adminService.searchByr_name(r_name);
			request.setAttribute("upload", upload);
			request.setAttribute("r_name",r_name);
			request.setAttribute("inq",inq);
			RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/adminInqChk.jsp");
			
			
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
