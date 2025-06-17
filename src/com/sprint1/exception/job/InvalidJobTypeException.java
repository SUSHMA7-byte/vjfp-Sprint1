
package exception.job;

public class InvalidJobTypeException extends RuntimeException {
  public InvalidJobTypeException(String type) {
    super("Invalid job type: " + type);
  }
}
