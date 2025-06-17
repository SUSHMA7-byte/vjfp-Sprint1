
package exception.job;

public class InvalidOpeningsCountException extends RuntimeException {
  public InvalidOpeningsCountException(int count) {
    super("Total openings must be greater than 0: " + count);
  }
}
