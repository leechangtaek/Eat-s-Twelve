package Photo.model.service;

import static common.JDBCTemplate.*;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import Photo.model.dao.PhotoDAO;
import Photo.model.vo.Photo;

public class PhotoService {

	public int selectPhotoCount(String searchKeyword) {
		
		Connection conn = getConnection();
		
		int totalPage = 0;
		
		totalPage = new PhotoDAO().selectPhotoCount(conn, searchKeyword);
		
		close(conn);
		
		return totalPage;
	}

	public String selectPhotoList(String searchKeyword) {
		String rNameList = "";
		Connection conn = getConnection();
		
		rNameList = new PhotoDAO().selectPhotoList(conn,searchKeyword);
		
		close(conn);
		
		return rNameList;
	}

	public String selectPhotoListByLike(String searchKeyword) {
		String rNameList = "";
		Connection conn = getConnection();
		
		rNameList = new PhotoDAO().selectPhotoListByLike(conn,searchKeyword);
		
		close(conn);
		
		return rNameList;
	}
	public List<Photo> selectOneByRName(String rName) {

		List<Photo> list = new ArrayList<>();
		Connection conn = getConnection();

		list = new PhotoDAO().selectOneByRName(conn,rName);

		close(conn);

		return list;
	}
	public List<Photo> searchByType20(String type) {

		Connection conn = getConnection();

		List<Photo> list = new ArrayList<>();

		list = new PhotoDAO().searchByType20(conn, type);

		close(conn);

		return list;
	}
	public Photo selectRecentRName(String rName) {

		Photo p = new Photo();
		Connection conn = getConnection();

		p = new PhotoDAO().selectRecentRName(conn,rName);

		close(conn);
		

		return p;
	}
	
	//희진
		public List<Photo> selectPhoto(String rName) {
			
			List<Photo> list = new ArrayList<>();
			Connection conn = getConnection();

			list = new PhotoDAO().selectPhoto(conn,rName);

			close(conn);
			
			return list;
		}

		public int insertPhotoByComment(String rName, String location, String renamedFileName) {
			int result = 0;
			Connection conn = getConnection();
			
			result = new PhotoDAO().insertPhotoByComment(conn, rName, location, renamedFileName);
			
			if(result>0) {
				commit(conn);
			}else {
				rollback(conn);
			}
			
			
			close(conn);
			
			return result;
		}

	

}
