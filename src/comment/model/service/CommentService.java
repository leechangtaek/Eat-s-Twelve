package comment.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import comment.model.dao.CommentDAO;
import comment.model.vo.Comment;

public class CommentService {

	public List<Comment> selectCommentList(String rName) {
		Connection conn = getConnection();
		List<Comment> list = new CommentDAO().selectCommentList(conn, rName);
		close(conn);
		return list;
	}
	
	public int selectCommentCount() {
		Connection conn = getConnection();
		int totalContent = new CommentDAO().selectCommentCount(conn);
		close(conn);
		return totalContent;
	}

	public List<Comment> selectCommentMore(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Comment> list = new CommentDAO().selectCommentMore(conn, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int insertComment(Comment c) {
		Connection conn = getConnection();
		int result = new CommentDAO().insertComment(conn, c);
		//트랜잭션 처리
		if(result>0) {
			//새로 발급된 게시글번호를 가져와서 board객체에 대입
			commit(conn);
		}
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	

	public int deleteComment(int board_no) {
		Connection conn = getConnection();
		int result = new CommentDAO().deleteComment(conn, board_no);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		
		return result;
	}

	public int updateComment(Comment c) {
		Connection conn = getConnection();
		int result = new CommentDAO().updateComment(conn, c);
		if(result>0){
			commit(conn);
		}
		else 
			rollback(conn);
		
		close(conn);
		
		return result;
	}

	public Comment selectOneByBoardNo(int boardRef) {
		
		Comment cBefore = new Comment();
		Connection conn = getConnection();
		
		cBefore = new CommentDAO().selectByBoardNo(conn,boardRef);
		
		close(conn);
		
		return cBefore;
	}

	public List<Comment> selectBestReview4List() {
		List<Comment> list = new ArrayList<>();
		Connection conn = getConnection();
		
		list = new CommentDAO().selectBestReview4List(conn);
		
		close(conn);
		
		return list;
	}
	


}
