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
 * Servlet implementation class userUpdatePasswordEndServlet
 */
@WebServlet(urlPatterns="/user/updatePasswordEnd", name="userUpdatePasswordEndServlet")
public class userUpdatePasswordEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			HttpSession session = request.getSession();
			String userId = ((User)session.getAttribute("userLoggedIn")).getUserId();
			String password = request.getParameter("password");
			String password_new = request.getParameter("password_new");
			User user = new User();
			user.setUserId(userId);
			user.setPwd(password);
			UserService userService = new UserService();
			//2.서비스로직호출
			User u = userService.selectOne(userId);
			//3. 현재패스워드를 맞게 입력했으면, 비밀번호를 업데이트함.
			//그렇지 않으면, 다시 팝업창 url을 호출함.
			String msg = "";
			String loc = "";
			String view = "/WEB-INF/views/common/msg.jsp";
			if(u != null && u.getPwd().equals(password)){
				//현재 member객체에 갱신할 비밀번호를 업데이트
				u.setPwd(password_new);
				int result = userService.updatePassword(u);
				if(result>0){
					msg = "패스워드 변경 성공";
					String script = "self.close()";
					//팝업창을 닫기위한 코드 추가
					request.setAttribute("script",script);
				}
			}
			else {
				msg = "패스워드를 잘못 입력하셨습니다.";
				loc = "/user/updatePassword?userId="+userId;
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
