package exception.application;

public class CandidateNotEligibleException extends RuntimeException {
  public CandidateNotEligibleException(String reason) {
    super("Candidate not eligible: " + reason);
  }
}
