package exception.candidate;

public class DuplicateCandidateEmailException extends RuntimeException {
  public DuplicateCandidateEmailException(String email) {
    super("Candidate email already exists: " + email);
  }
}
