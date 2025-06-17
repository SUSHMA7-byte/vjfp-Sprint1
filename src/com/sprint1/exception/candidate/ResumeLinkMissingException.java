package exception.candidate;

public class ResumeLinkMissingException extends RuntimeException {
  public ResumeLinkMissingException() {
    super("Resume link is required but missing.");
  }
}
