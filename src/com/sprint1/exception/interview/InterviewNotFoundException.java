package exception.interview;

public class InterviewNotFoundException extends RuntimeException {
  public InterviewNotFoundException(int interviewId) {
    super("Interview not found with ID: " + interviewId);
  }
}
