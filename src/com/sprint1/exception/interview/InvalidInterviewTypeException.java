package exception.interview;

public class InvalidInterviewTypeException extends RuntimeException {
  public InvalidInterviewTypeException(String type) {
    super("Invalid interview type: " + type);
  }
}
