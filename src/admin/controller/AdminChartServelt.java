package admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

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
 * Servlet implementation class AdminChartServelt
 */
@WebServlet("/admin/adminchart")
public class AdminChartServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminChartServelt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			AdminService adminService=new AdminService();
			//3.��������
			List<Map<String,Object>> list = adminService.totalvisit();
			Calendar c1 = new GregorianCalendar();
			
//			System.out.println(list);
			String[]day=new String[7];
			c1.add(Calendar.DATE,0);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // ��¥ ���� 
			day[0] = sdf.format(c1.getTime()); // String���� ����
			System.out.println(day[0]);
			for(int i=1;i<7;i++){
				c1.add(Calendar.DATE, -1); // ���ó�¥�κ��� -1
				sdf = new SimpleDateFormat("yyyy-MM-dd"); // ��¥ ���� 
				day[i] = sdf.format(c1.getTime()); // String���� ����
//				System.out.println(day[i]);
			}
			boolean bool;
			int j=6;
			JSONArray jsonArr = new JSONArray();
			int count1=0;
			
			
			for(Map<String,Object> map: list){
				
				JSONObject json = null;		
				int count=0;
				String mapStr = (String)map.get("visit_time");
				
//				System.out.println("1111111111111");
				if((mapStr.trim()).equals(day[j].trim())) {
//					System.out.println("44444444444");
					count1=0;
					
					json = new JSONObject();
					json.put("visit_time", map.get("visit_time"));
					json.put("total", map.get("total"));
					jsonArr.add(json);
					j--;
					continue;
				}
				while(true) {
					if(!(mapStr.trim()).equals(day[j].trim())) {
//						System.out.println("22222222222");
						j--;
						count1++;		
						if(j==0)
							break;
						if((mapStr.trim()).equals(day[j].trim()))
							break;
					}
				}
				for(int i=0;i<count1;i++) {			
//					System.out.println("333333333333333");
					json = new JSONObject();
					json.put("visit_time", null);
					json.put("total", null);
					jsonArr.add(json);
				}
				if((mapStr.trim()).equals(day[j].trim())) {
//					System.out.println("44444444444");
					count1=0;
					
					json = new JSONObject();
					json.put("visit_time", map.get("visit_time"));
					json.put("total", map.get("total"));
					jsonArr.add(json);
					j--;
				}
				
			}
			System.out.println("total="+jsonArr);
			
			//4.���䰴ü�� ���
			response.setContentType("application/json; charset=utf-8"); 
			PrintWriter out = response.getWriter();
			out.print(jsonArr);	
			
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
