package admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import admin.model.service.AdminService;
import user.model.vo.User;

/**
 * Servlet implementation class AdminUserFindServlet
 */
@WebServlet("/admin/UserFind")
public class AdminUserFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.setCharacterEncoding("utf-8");
		
			AdminService adminService=new AdminService();
			
			//2.�Ķ�����ڵ鸵
			String srchName = request.getParameter("srchName");
			
			//��ȿ�� �˻�
			
			//3.��������
			List<User> list= adminService.selectByNameId(srchName);
	
			JSONArray jsonArray = new JSONArray();
			
			for(User u : list) {
					if(u!=null){
					JSONObject jsonMember = new JSONObject();
					jsonMember.put("id",u.getUserId());
					jsonMember.put("name", u.getName());
					jsonMember.put("gender",u.getGender());
					jsonMember.put("age",u.getAge()+"");
					jsonMember.put("address",u.getAddress());
					jsonMember.put("phone",u.getPhone());
					jsonArray.add(jsonMember);
				}
			}
			
			//4.���䰴ü�� ���
			response.setContentType("application/json; charset=utf-8"); 
			PrintWriter out = response.getWriter();
			out.print(jsonArray);		
		}catch(Exception e){
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
