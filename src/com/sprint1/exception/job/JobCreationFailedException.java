
package exception.job;

public class JobCreationFailedException extends RuntimeException {
  public JobCreationFailedException(String reason) {
    super("Job creation failed: " + reason);
  }
}
