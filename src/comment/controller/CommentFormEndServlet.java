package comment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import Photo.model.service.PhotoService;
import comment.model.service.CommentService;
import comment.model.vo.Comment;
import common.MvcFileRenamePolicy;
import user.model.vo.User;

/**
 * Servlet implementation class CommentFormEndServlet
 */
@WebServlet("/comment/commentFormEnd")
public class CommentFormEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		try {
			// 파일이 저장될 절대경로 가져오기
			String saveDirectory
				= getServletContext().getRealPath("/images");// / 웹루트 WebContent
			
			// 파일최대업로드크기 제한: 10MB까지 제한
			//10MB = 1024 * 1024 * 10
			int maxPostSize = 1024 * 1024 * 10; 
			
			// 파일명 재지정 정책 객체
			FileRenamePolicy mvcFileRenamePolicy = new MvcFileRenamePolicy();
			MultipartRequest multiReq = new MultipartRequest(request,
															 saveDirectory, 
															 maxPostSize, 
															 "utf-8",
															 mvcFileRenamePolicy);
			
			
			// 1.parameter handling
			HttpSession session = request.getSession();
			String userId = ((User)session.getAttribute("userLoggedIn")).getUserId();
			
			String boarding = multiReq.getParameter("boarding"); 
			int grade = Integer.parseInt(multiReq.getParameter("grade"));  
			String location = multiReq.getParameter("location");
			String rName = multiReq.getParameter("rName");
			// XSS공격대비 &문자변환
			boarding = boarding.replaceAll("<", "&lt;")
									   .replaceAll(">", "&gt;")
									   .replaceAll("\\n", "<br/>");//개행문자처리
			
			String originalFileName 
				= multiReq.getOriginalFileName("upFile");//사용자 업로드한 파일명
			String renamedFileName
				= multiReq.getFilesystemName("upFile");//실제 저장된 파일명
			Comment c = new Comment(0, userId, rName, location, boarding, grade, null, 0, 0, originalFileName, renamedFileName);
			
			//2.business logic
			int result = new CommentService().insertComment(c);
			int photoResult = 0;
			if(renamedFileName!=null) {
				photoResult = new PhotoService().insertPhotoByComment(rName,location,renamedFileName);
			}
			
			String msg = "";
			String loc = "/restaurants?rName="+rName;
			
			if(result>0) {
				msg = "게시글 등록 성공!";
			}
			else {
				msg = "게시글 등록 실패! 관리자에게 문의하세요.";
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
