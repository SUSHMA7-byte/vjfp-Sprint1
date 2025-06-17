package exception.application;

public class ApplicationNotFoundException extends RuntimeException {
  public ApplicationNotFoundException(int applicationId) {
    super("Application not found with ID: " + applicationId);
  }
}
