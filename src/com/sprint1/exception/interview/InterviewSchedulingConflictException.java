package exception.interview;

public class InterviewSchedulingConflictException extends RuntimeException {
  public InterviewSchedulingConflictException(String details) {
    super("Interview scheduling conflict: " + details);
  }
}
