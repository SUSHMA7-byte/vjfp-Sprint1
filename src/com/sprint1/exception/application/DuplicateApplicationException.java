package exception.application;

public class DuplicateApplicationException extends RuntimeException {
  public DuplicateApplicationException() {
    super("Candidate has already applied to this job.");
  }
}
