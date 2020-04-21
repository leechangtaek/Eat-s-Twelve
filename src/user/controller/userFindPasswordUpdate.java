package user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.model.service.UserService;

/**
 * Servlet implementation class userFindPasswordUpdate
 */
@WebServlet(urlPatterns="/user/userFindPasswordUpdate", name="userFindPasswordUpdate")
public class userFindPasswordUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.encoding
			request.setCharacterEncoding("utf-8");
			//2.parameter
			String password = request.getParameter("password");
			String userId = request.getParameter("userId");
			System.out.println("아디확인:"+userId);
			System.out.println("암호화 됫나 안됫나:"+password);
				
		
			
			//3.businessLogic
			int result=new UserService().changePassword(userId,password);
			
			String msg = "";
			String loc = "";
			String script="";
			System.out.println("dfdasfsd"+password);
			if(result>0) {
				msg="비밀번호 변경 성공!";
				loc="/user/userLoginForm";
				script="resizeTo(600,400)";
			}else
			{
				msg="비밀번호 변경 실패!";
				loc="/user/userFindPasswordUpdateForm?userId="+userId;
				
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
