
package exception.job;

public class InvalidApplicationDatesException extends RuntimeException {
  public InvalidApplicationDatesException() {
    super("Application end date must be after start date.");
  }
}
