package common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import user.model.vo.User;

/**
 * Servlet Filter implementation class MemberAuthFilter
 */
@WebFilter(servletNames = {"userFormMainServlet", "userRecentInsertSevlet","wishRestaurantServlet", "DMBoardServlet" },
urlPatterns= {"/user/userForm", "/user/userRecent","/restaurant/wishRestaurant", "/chat/dmboard"}
)
public class MemberAuthFilter implements Filter {

    /**
     * Default constructor. 
     */
    public MemberAuthFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpSession session = ((HttpServletRequest)request).getSession();
		User userLoggedIn =(User)session.getAttribute("userLoggedIn");
		String userId = ((HttpServletRequest)request).getParameter("userId");
		String memberId = ((HttpServletRequest)request).getParameter("memberId");
		

		if(userLoggedIn!=null&&((userLoggedIn.getUserId().equals(userId))||(userLoggedIn.getUserId().equals(memberId)||(userLoggedIn.getUserId().equals("admin"))))) {
			
		} else {
			request.setAttribute("msg", "잘못된 경로로 접근하셨습니다.:로그인되지않음.");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
			
			return;
		}
		
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
