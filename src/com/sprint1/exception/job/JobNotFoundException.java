
package exception.job;

public class JobNotFoundException extends RuntimeException {
  public JobNotFoundException(int jobId) {
    super("Job not found with ID: " + jobId);
  }
}
