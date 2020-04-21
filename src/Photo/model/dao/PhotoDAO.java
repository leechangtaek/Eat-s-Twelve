package Photo.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import Photo.model.vo.Photo;
import common.exception.MyException;

public class PhotoDAO {
	
	private Properties prop = new Properties();
	
	public PhotoDAO() {
		String fileName = PhotoDAO.class.getResource("/sql/photo/photo-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public int selectPhotoCount(Connection conn, String searchKeyword) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectPhotoCount");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setString(2, "%"+searchKeyword+"%");
			pstmt.setString(3, "%"+searchKeyword+"%");
			pstmt.setString(4, "%"+searchKeyword+"%");
			pstmt.setString(5, "%"+searchKeyword+"%");

			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("검색 사진 전체 수 조회 실패!");
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}
public String selectPhotoList(Connection conn, String searchKeyword) {
		
		List<String> list = null;
		String query = prop.getProperty("selectPhotoList");
		String str = "";
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			list = new ArrayList<>();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setString(2, "%"+searchKeyword+"%");
			pstmt.setString(3, "%"+searchKeyword+"%");
			pstmt.setString(4, "%"+searchKeyword+"%");

			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(rset.getString("r_name"));
			}
			if(list.size()!=0) {
				str= list.get(0);
				
				for(int i=1;i<list.size();i++) {
					str += "/"+list.get(i);
				}				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("검색 사진 로드 실패!",e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return str;
	}

	public String selectPhotoListByLike(Connection conn, String searchKeyword) {
		List<String> list = null;
		String query = prop.getProperty("selectPhotoListByLike");
		String str = "";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			list = new ArrayList<>();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setString(2, "%"+searchKeyword+"%");
			pstmt.setString(3, "%"+searchKeyword+"%");
			pstmt.setString(4, "%"+searchKeyword+"%");

			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(rset.getString("r_name"));
			}
			if(list.size()!=0) {
				str = list.get(0);
				for(int i=1;i<list.size();i++) {
					str += "/"+list.get(i);
					
				}				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("검색 사진 로드 실패!");
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return str;
	}

	
	public List<Photo> selectOneByRName(Connection conn, String rName) {

		String[] strArr = rName.split("/");
		List<Photo> list = null;
		Photo p = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectOneByRName");

		try {
			pstmt = conn.prepareStatement(query);
			list = new ArrayList<>();

			for(int i=0;i<strArr.length;i++) {
				pstmt.setString(1, strArr[i]);

				rset = pstmt.executeQuery();
				p = new Photo();
				if(rset.next()) {
					p.setrName(rset.getString("r_name"));
					p.setImgNo(rset.getInt("img_no"));
					p.setImgName(rset.getString("img_name"));
					p.setLocation(rset.getString("location"));
				}
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("selectOneByRname(photo)조회 실패");
		} finally {
			close(rset);
			close(pstmt);
		}


		return list;
	}
	
	public List<Photo> searchByType20(Connection conn, String type) {

		List<Photo> list = null;
		String query = prop.getProperty("searchByType20");

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			list = new ArrayList<>();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, type);

			rset = pstmt.executeQuery();

			while(rset.next()) {
				Photo p = new Photo();

				p.setImgNo(rset.getInt("img_no"));
				p.setrName(rset.getString("r_name"));
				p.setLocation(rset.getString("location"));
				p.setImgName(rset.getString("img_name"));

				list.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("검색 사진 로드 실패!");
		} finally {
			close(rset);
			close(pstmt);
		}


		return list;

	}

	public Photo selectRecentRName(Connection conn, String rName) {

		Photo p = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectRecentRName");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, rName);
			
			rset = pstmt.executeQuery();
			p = new Photo();
			if(rset.next()) {
				p.setrName(rName);
				p.setImgNo(rset.getInt("img_no"));
				p.setImgName(rset.getString("img_name"));
				p.setLocation(rset.getString("location"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("selectRecentRName(photo)조회 실패");
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return p;
	}
	
	public List<Photo> selectPhoto(Connection conn, String rName) {
		List<Photo> list = null;
		String query = prop.getProperty("selectPhoto");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			list = new ArrayList<>();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, rName);

			rset = pstmt.executeQuery();

			while(rset.next()) {
				Photo p = new Photo();

				p.setImgNo(rset.getInt("img_no"));
				p.setrName(rset.getString("r_name"));
				p.setLocation(rset.getString("location"));
				p.setImgName(rset.getString("img_name"));

				list.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("사진 로드 실패!");
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int insertPhotoByComment(Connection conn, String rName, String location, String renamedFileName) {
		
		int result = 0;
		String query = prop.getProperty("insertPhotoByComment");
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, rName);
			pstmt.setString(2, location);
			pstmt.setString(3, renamedFileName);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MyException("포토 테이블 입력 실패!, 관리자에게 문의하세요.",e);
		} finally {
			close(pstmt);
		}
		
		
		
		return result;
	}

}
