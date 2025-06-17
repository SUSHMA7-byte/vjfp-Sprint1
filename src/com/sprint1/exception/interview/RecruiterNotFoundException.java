package exception.interview;

public class RecruiterNotFoundException extends RuntimeException {
  public RecruiterNotFoundException(int recruiterId) {
    super("Recruiter not found with ID: " + recruiterId);
  }
}
