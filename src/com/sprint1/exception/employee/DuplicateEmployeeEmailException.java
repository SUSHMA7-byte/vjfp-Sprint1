package exception.employee;

public class DuplicateEmployeeEmailException extends RuntimeException {
  public DuplicateEmployeeEmailException(String email) {
    super("Employee email already exists: " + email);
  }
}
