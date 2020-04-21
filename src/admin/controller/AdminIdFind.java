package admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import user.model.vo.User;

/**
 * Servlet implementation class AdminIdFind
 */
@WebServlet("/admin/Idfind")
public class AdminIdFind extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminIdFind() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			String id=request.getParameter("id");
			AdminService adminService=new AdminService();
			User user1=adminService.searchById(id);
	//		System.out.println(user1.toString());
			RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/adminMemberInfo.jsp");
			request.setAttribute("user1",user1);
			
			reqDispatcher.forward(request, response);
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
