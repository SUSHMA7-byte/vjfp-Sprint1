package exception.application;

public class InvalidApplicationStatusException extends RuntimeException {
  public InvalidApplicationStatusException(String status) {
    super("Invalid application status: " + status);
  }
}
