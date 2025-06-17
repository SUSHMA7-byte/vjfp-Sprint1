package exception.interview;

public class MissingApplicationReferenceException extends RuntimeException {
  public MissingApplicationReferenceException(int applicationId) {
    super("Missing application reference for ID: " + applicationId);
  }
}
