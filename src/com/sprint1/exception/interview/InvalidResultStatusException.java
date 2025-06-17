package exception.interview;

public class InvalidResultStatusException extends RuntimeException {
  public InvalidResultStatusException(String status) {
    super("Invalid result status: " + status);
  }
}
