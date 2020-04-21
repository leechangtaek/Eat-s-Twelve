package comment.model.dao;

import java.io.File;

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

import comment.model.exception.CommentException;
import comment.model.vo.Comment;
import common.exception.MyException;

import static common.JDBCTemplate.*;


public class CommentDAO {

	private Properties prop = new Properties();
	
	public CommentDAO() {
		String fileName = getClass().getResource("/sql/comment/comment-query.properties")
									.getPath();
		try {
			
			prop.load(new FileReader(new File(fileName)));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public int selectCommentCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectCommentCount");
		int totalContent = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			if(rset.next())
				totalContent = rset.getInt("cnt");
			
			System.out.println("totalContent@dao="+totalContent);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContent;
	}
	
	 public List<Comment> selectCommentMore(Connection conn, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		List<Comment> list = new ArrayList<>();
		String query = prop.getProperty("selectCommentMore");
		
		try{
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			
			rset = pstmt.executeQuery();
			while(rset.next()){
				Comment c = new Comment();
				c.setBoardNo(rset.getInt("board_no"));
				c.setId(rset.getString("id"));
				c.setrName(rset.getString("r_name"));
				c.setLocation(rset.getString("location"));
				c.setBoarding(rset.getString("boarding"));
				c.setGrade(rset.getInt("grade"));
				c.setBoardCommentLevel(rset.getInt("board_comment_level"));
				c.setBoardCommentRef(rset.getInt("board_comment_ref"));
				c.setOriginalFileName(rset.getString("original_filename"));
				c.setRenamedFileName(rset.getString("renamed_filename"));
				c.setbDate(rset.getDate("b_date"));
				list.add(c);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new MyException("댓글 조회 실패! 관리자에게 문의하세요.",e);
		}finally{
			close(rset);
			close(pstmt);
		}
		return list;
	}


	public int insertComment(Connection conn, Comment c) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertComment");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, c.getId());
			pstmt.setString(2, c.getrName());
			pstmt.setString(3, c.getLocation());
			pstmt.setString(4, c.getBoarding());
			pstmt.setInt(5, c.getGrade());
			pstmt.setString(6, c.getOriginalFileName());
			pstmt.setString(7, c.getRenamedFileName());

			
			result = pstmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("댓글 등록 실패, 관리자에게 문의하세요",e);
		} finally {
			close(pstmt);
		}
		return result;
	}


	public int selectLastSeq(Connection conn) {
		int boardNo = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectLastSeq");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				boardNo = rset.getInt("currval");
			}
			System.out.println("새로발급받은 댓글 번호=" + boardNo);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return boardNo;
	}


	public int deleteComment(Connection conn, int board_no) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteComment"); 
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, board_no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("댓글삭제 실패, 관리자에게 문의하세요!",e);
		} finally {
			close(pstmt);
		}

		
		return result;
	}


	public int updateComment(Connection conn, Comment c) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateComment");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, c.getBoarding());
			pstmt.setInt(2, c.getGrade());
			pstmt.setString(3, c.getOriginalFileName());
			pstmt.setString(4, c.getRenamedFileName());
			pstmt.setInt(5, c.getBoardNo());

			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("댓글 갱신 조회 실패!",e);
		} finally {
			close(pstmt);
		}
		return result;
	}


	public List<Comment> selectCommentList(Connection conn, String rName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectCommentList");
		List<Comment> list = null;
		try {
			list = new ArrayList<>();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, rName);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Comment c = new Comment();
				c.setBoardNo(rset.getInt("board_no"));
				c.setId(rset.getString("id"));
				c.setrName(rset.getString("r_name"));
				c.setLocation(rset.getString("location"));
				c.setBoarding(rset.getString("boarding"));
				c.setGrade(rset.getInt("grade"));
				c.setbDate(rset.getDate("b_date"));
				c.setBoardCommentLevel(rset.getInt("board_comment_level"));
				c.setBoardCommentRef(rset.getInt("board_comment_ref"));
				c.setOriginalFileName(rset.getString("original_filename"));
				c.setRenamedFileName(rset.getString("renamed_filename"));
				
				list.add(c);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("댓글 조회 실패, 관리자에게 문의하세요!",e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}


	public Comment selectByBoardNo(Connection conn, int boardRef) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectByBoardNo");
		Comment c = new Comment();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardRef);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				c.setBoardNo(rset.getInt("board_no"));
				c.setId(rset.getString("id"));
				c.setrName(rset.getString("r_name"));
				c.setLocation(rset.getString("location"));
				c.setBoarding(rset.getString("boarding"));
				c.setGrade(rset.getInt("grade"));
				c.setbDate(rset.getDate("b_date"));
				c.setBoardCommentLevel(rset.getInt("board_comment_level"));
				c.setBoardCommentRef(rset.getInt("board_comment_ref"));
				c.setOriginalFileName(rset.getString("original_filename"));
				c.setRenamedFileName(rset.getString("renamed_filename"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("board번호 조회 실패!, 관리자에게 문의하세요.",e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return c;
	}

	public List<Comment> selectBestReview4List(Connection conn) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectBestReview4List");
		List<Comment> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Comment c = new Comment();
				
				c.setId(rset.getString("id"));
				c.setrName(rset.getString("r_name"));
				c.setBoarding(rset.getString("boarding"));
				c.setGrade(rset.getInt("grade"));
				c.setbDate(rset.getDate("b_date"));
				
				list.add(c);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	
}





