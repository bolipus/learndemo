package si.gemma.demo.exceptions;

public class CommentNotFoundException extends Exception {

  public CommentNotFoundException(String message) {
    super(message);
  }

  public CommentNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }



}
