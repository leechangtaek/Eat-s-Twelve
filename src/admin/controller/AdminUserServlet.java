package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import user.model.vo.User;
import admin.model.service.AdminService;

/**
 * Servlet implementation class AdminUserServlet
 */
@WebServlet("/admin/adminList")
public class AdminUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AdminUserServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.setCharacterEncoding("utf-8");
			AdminService adminService=new AdminService();
			
			//1.파라미터 핸들링
			final int numPerPage = 5;
			int cPage = 1;
			try {
				cPage = Integer.parseInt(request.getParameter("cPage"));			
			}catch(NumberFormatException e) {
			}
			//2.업무로직
			//a.컨텐츠영역
			List<User> list
				= adminService.selectUserList(cPage, numPerPage);
//			System.out.println("list@servlet="+list);
			//b.페이징바영역
			//전체게시글수, 전체페이지수
			int totalContent = adminService.selectMemberCount();
			int totalPage =  (int)Math.ceil((double)totalContent/numPerPage);//(공식2)
			String pageBar = "";
			int pageBarSize = 5;
			int pageStart = ((cPage-1)/pageBarSize)*pageBarSize+1;//(공식3)
			int pageEnd = pageStart+pageBarSize-1;
			int pageNo = pageStart;
			//[이전] section
			if(pageNo == 1 ){
				//pageBar += "<span>[이전]</span>";
			}
			else {
				pageBar += "<a href='"+request.getContextPath()+"/admin/adminList?cPage="+(pageNo-1)+"'>[이전]</a> ";
			}
			// pageNo section
			while(pageNo<=pageEnd && pageNo<=totalPage){
				if(cPage == pageNo ){
					pageBar += "<span class='cPage'>"+pageNo+"</span> ";
				}
				else {
					pageBar += "<a href='"+request.getContextPath()+"/admin/adminList?cPage="+pageNo+"'>"+pageNo+"</a> ";
				}
				pageNo++;
			}
			//[다음] section
			if(pageNo > totalPage){
			} else {
				pageBar += "<a href='"+request.getContextPath()+"/admin/adminList?cPage="+pageNo+"'>[다음]</a>";
			}
			//4.뷰단 포워딩		
			RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/adminMemberInfo.jsp");
			
			request.setAttribute("list",list);
			request.setAttribute("pageBar",pageBar);
			request.setAttribute("cPage",cPage);
			
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
