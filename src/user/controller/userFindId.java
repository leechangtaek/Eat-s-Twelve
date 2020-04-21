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
@WebServlet("/user/userFindId")
public class userFindId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.encoding
			request.setCharacterEncoding("utf-8");
			//2.parameter
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
				
			//3.businessLogic
			String id=new UserService().findId(name,phone);
//				System.out.println("member@loginServlet="+m);
			String msg = "";
			String loc = "";
			String script="";
			System.out.println("dfdasfsd"+id);
			if(id=="") {
				msg="해당하는 회원이 없습니다.";
				loc="/user/userIdFind";
			}else
			{
				msg="회원 아이디는:"+id+" 입니다.";
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
