package user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import common.MvcFileRenamePolicy;
import restaurant.model.vo.Inquire;
import user.model.service.UserService;

/**
 * Servlet implementation class userInquireServlet
 */
@WebServlet("/user/userInquire")
public class userInquireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			
		String saveDirectory
		= getServletContext().getRealPath("/images");// / 웹루트 WebContent
		//파일최대업로드크기 제한: 10MB까지 제한
		//10MB = 1024 * 1024 * 10
		int maxPostSize = 1024 * 1024 * 10; 
		
		//파일명 재지정 정책 객체
		FileRenamePolicy mvcFileRenamePolicy
			= new MvcFileRenamePolicy();
		MultipartRequest multiReq = new MultipartRequest(request,
														 saveDirectory, 
														 maxPostSize, 
														 "utf-8",
														 mvcFileRenamePolicy);
		String userId = multiReq.getParameter("userId");
		String r_name = multiReq.getParameter("r_name");
		String type = multiReq.getParameter("type");
		String location = multiReq.getParameter("location");
		String description = multiReq.getParameter("description");
		String category = multiReq.getParameter("category");
		String r_phone = multiReq.getParameter("r_phone");
		String parking = multiReq.getParameter("parking");
		int open = Integer.parseInt(multiReq.getParameter("open"));
		int close = Integer.parseInt(multiReq.getParameter("close"));
		String holiday = multiReq.getParameter("holiday");
		
		
		
		Inquire inquire =new Inquire(0,userId,r_name,type,location,description,category,r_phone,parking,open,close,holiday,null,null);
		int result = new UserService().inquire(inquire);
		
		//XSS공격대비 &문자변환
		description = description.replaceAll("<", "&lt;")
								   .replaceAll(">", "&gt;")
								   .replaceAll("\\n", "<br/>");//개행문자처리
		if(multiReq.getOriginalFileName("upFile1")!=null)
		{
		String originalFileName1 
			= multiReq.getOriginalFileName("upFile1");//사용자 업로드한 파일명
		String renamedFileName1
			= multiReq.getFilesystemName("upFile1");
		int result1=new UserService().insertPhoto(originalFileName1,renamedFileName1,r_name,location);
		}
		
		
		if(multiReq.getOriginalFileName("upFile2")!=null)
		{
		String originalFileName2 
			= multiReq.getOriginalFileName("upFile2");//사용자 업로드한 파일명
		String renamedFileName2
			= multiReq.getFilesystemName("upFile2");
		int result1=new UserService().insertPhoto(originalFileName2,renamedFileName2,r_name,location);
		}
		
		
		if(multiReq.getOriginalFileName("upFile3")!=null)
		{
		String originalFileName3 
			= multiReq.getOriginalFileName("upFile3");//사용자 업로드한 파일명
		String renamedFileName3
			= multiReq.getFilesystemName("upFile3");
		int result1=new UserService().insertPhoto(originalFileName3,renamedFileName3,r_name,location);
		}
		
		
		if(multiReq.getOriginalFileName("upFile4")!=null)
		{
		String originalFileName4 
			= multiReq.getOriginalFileName("upFile4");//사용자 업로드한 파일명
		String renamedFileName4
			= multiReq.getFilesystemName("upFile4");
		int result1=new UserService().insertPhoto(originalFileName4,renamedFileName4,r_name,location);
		}
		
		
		String msg = "";
		String loc = "";
		
		if(result>0) {
			msg = "요청성공!";
			loc="/user/userForm?userId="+userId;
		}
		else {
			msg = "요청 실패 실패! 관리자에게 문의하세요.";
			loc="/user/userForm?userId="+userId;
		}
		
		
		//3.view단 처리
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
			   .forward(request, response);
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
