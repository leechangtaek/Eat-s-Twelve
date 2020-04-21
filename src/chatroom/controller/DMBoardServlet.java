package chatroom.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chatroom.model.service.ChatRoomService;
import chatroom.model.vo.DM;

/**
 * Servlet implementation class DMBoardServlet
 */
@WebServlet(urlPatterns = "/chat/dmboard", name = "DMBoardServlet")
public class DMBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			request.setCharacterEncoding("utf-8");
			String userLoginId = request.getParameter("userId");
			
			//1.파라미터 핸들링
			final int numPerPage = 5;
			int cPage = 1;
			try {
				cPage= Integer.parseInt(request.getParameter("cPage"));
			} catch (NumberFormatException e) {
				
			}
			
			//2.업무로직
			List<DM> list = new ChatRoomService().selectDMList(userLoginId, cPage,numPerPage);
			int totalContent = new ChatRoomService().selectDMCount(userLoginId);
			int totalPage = (int)Math.ceil((double)totalContent/numPerPage);
			int pageBarSize = 5;
			
			int pageStart = ((cPage-1)/pageBarSize)*pageBarSize+1;
			int pageEnd = pageStart+pageBarSize-1;
			int pageNo = pageStart;
			
			String pageBar = "";
			
			if(pageNo!=1) {
				pageBar += "<a href='"+request.getContextPath()+"/chat/dmboard??userId="+userLoginId+"&cPage="+(pageNo-1)+"'>[이전]</a>";
			}
			
			while(pageNo<=pageEnd&&pageNo<=totalPage) {
				if(pageNo==cPage) {
					pageBar += "<span class='cPage'>"+pageNo+"</span>";
				}
				else {
					pageBar += "<a href='"+request.getContextPath()+"/chat/dmboard??userId="+userLoginId+"&cPage="+pageNo+"'>"+pageNo+"</a>";
				}
				
				pageNo++;
			}
			
			if(pageNo<=totalPage) {
				pageBar += "<a href='"+request.getContextPath()+"/chat/dmboard??userId="+userLoginId+"&cPage="+pageNo+"'>[다음]</a>";
			}
			request.setAttribute("list", list);
			request.setAttribute("pageBar", pageBar);
			request.setAttribute("userId", userLoginId);
			request.getRequestDispatcher("/WEB-INF/views/honbabChatRoom/dmboard.jsp").forward(request, response);
		}catch(Exception e) {
			throw e;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
