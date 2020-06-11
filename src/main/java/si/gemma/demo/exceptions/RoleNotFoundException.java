package si.gemma.demo.exceptions;

public class RoleNotFoundException extends Exception {


  private static final long serialVersionUID = -5898690349416198201L;

  public RoleNotFoundException(String message) {
    super(message);
  }

  public RoleNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }


}
