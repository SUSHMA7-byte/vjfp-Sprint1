package exception.employee;

public class EmployeeNotFoundException extends RuntimeException {
  public EmployeeNotFoundException(int id) {
    super("Employee not found with ID: " + id);
  }
}
