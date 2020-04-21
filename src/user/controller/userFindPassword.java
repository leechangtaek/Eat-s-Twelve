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
 * Servlet implementation class userFindId
 */
@WebServlet(urlPatterns="/user/userFindPassword", name="userFindPassword")
public class userFindPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userFindPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.encoding
			request.setCharacterEncoding("utf-8");
			//2.parameter
			String userId = request.getParameter("userId");
			String email = request.getParameter("email");
				
			
			//3.businessLogic
			String password=new UserService().findPassword(userId,email);
			String msg = "";
			String loc = "";
			String script="";
			if(password=="") {
				msg="해당하는 회원이 없습니다.";
				loc="/user/userPasswordFind";
			}else
			{
				msg="새롭게 변경하실 비밀번호 페이지로 이동합니다.";
				loc="/user/userFindPasswordUpdateForm?userId="+userId;
				script="resizeTo(500,350)";
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
