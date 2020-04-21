package user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.model.service.UserService;
import user.model.vo.User;

/**
 * Servlet implementation class userEnrollServlet
 */
@WebServlet(urlPatterns="/user/userEnrollServlet", name="userEnrollServlet")
public class userEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.encoding
			request.setCharacterEncoding("utf-8");
			//2.parameter
			String userId = request.getParameter("uid");
			String password = request.getParameter("upw");
			String name = request.getParameter("uname");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			String phone = request.getParameter("phone");
			int age = Integer.parseInt(request.getParameter("age"));
			String[] favorite1 = request.getParameterValues("favorite");
			String gender = request.getParameter("gender");
			String favorite="";
			for(int i=0;i<favorite1.length;i++) {
				favorite+=favorite1[i];
				if(favorite1.length-1!=i)
				favorite+=",";
			}
			//3.businessLogic
			User u = new User(userId, password, name, email, address, phone, age, favorite, gender);
			int result= new UserService().insertUser(u);
//				System.out.println("member@loginServlet="+m);
			String msg = "";
			String loc = "";
			String script="";
			if(result==0) {
				msg="회원등록 실패";
				loc="/user/userEnrollForm";
			}else
			{
				msg="회원등록 성공";
				loc="/user/userLoginForm";
				script="resizeTo(400,210)";
			}
			request.setAttribute("msg", msg);
			request.setAttribute("loc",loc);
			request.setAttribute("script",script);
			RequestDispatcher reqDispatcher
				= request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
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
