package comment.model.exception;

public class CommentException extends RuntimeException {
	
	public CommentException() {
		super();
	}

	public CommentException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommentException(String message) {
		super(message);
	}

	public CommentException(Throwable cause) {
		super(cause);
	}

}
