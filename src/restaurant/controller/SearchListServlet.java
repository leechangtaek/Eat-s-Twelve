package restaurant.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Photo.model.service.PhotoService;
import Photo.model.vo.Photo;

/**
 * Servlet implementation class SearchListServlet
 */
@WebServlet("/searchList")
public class SearchListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			
			//파라미터핸들링
			String searchKeyword = request.getParameter("searchKeyword");
			//업무로직
			String orderByGrade = new PhotoService().selectPhotoList(searchKeyword);
			String orderByLike = new PhotoService().selectPhotoListByLike(searchKeyword);
			
			request.setAttribute("orderByGrade", orderByGrade);
			request.setAttribute("orderByLike", orderByLike);
			request.setAttribute("searchKeyword", searchKeyword);
			request.getRequestDispatcher("/WEB-INF/views/searchlist/search.jsp").forward(request, response);
	
		} catch(Exception e) {
			throw e;
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
