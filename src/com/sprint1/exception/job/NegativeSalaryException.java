
package exception.job;

public class NegativeSalaryException extends RuntimeException {
  public NegativeSalaryException(double salary) {
    super("Salary package cannot be negative: " + salary);
  }
}
