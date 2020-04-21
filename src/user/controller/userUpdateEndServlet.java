package user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.model.service.UserService;
import user.model.vo.User;

/**
 * Servlet implementation class userUpdateEndServlet
 */
@WebServlet("/user/userUpdateEnd")
public class userUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.enconding
				request.setCharacterEncoding("utf-8");
				//2.prameter
				String userId = request.getParameter("userId");
				String name = request.getParameter("name");
				int age = Integer.parseInt(request.getParameter("age"));
				String email = request.getParameter("email");
				String phone = request.getParameter("phone");
				String address = request.getParameter("address");
				String gender = request.getParameter("gender");
				String[] favorite_ = request.getParameterValues("favorite");
				String favorite = "";
				if(favorite_!=null) {
					favorite = favorite_[0];
					for(int i=1; i<favorite_.length; i++) {
						if(favorite_.length>2) {
							favorite += ","+favorite_[i];
						}
					}
				}
				
				//3.business logic
				UserService userService = new UserService();
				User u = new User(userId, null,  name,  email, address, phone, age, favorite,  gender);
				System.out.println(u.toString());
				
				int result = userService.updateUser(u);
				System.out.println("tjqmfflt"+u.getUserId());
				//4.view단 처리 : msg.jsp -> /mvc
				String msg="";
				String loc = "";
				
				if(result>0){
					msg = "정보수정 성공!";
					loc = "/user/userView?userId="+u.getUserId();
					
					User userLoggedIn = (User)request.getSession().getAttribute("userLoggedIn");
					HttpSession session = request.getSession();
					if(!"admin".equals(userLoggedIn.getUserId())) {
						//세션에 로그인한 회원객체 속성으로 저장
						session.setAttribute("userLoggedIn", u);
					}
				}
				else{
					msg = "정보수정 실패!";
				}
				
				
				
				//session유효시간을 설정할 수 있음(초단위 web.xml보다 우선순위가 높음)
				//web.xml에서 할때는 분단위
				
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				
				RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
				reqDispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
