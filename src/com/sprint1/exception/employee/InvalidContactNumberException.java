package exception.employee;

public class InvalidContactNumberException extends RuntimeException {
  public InvalidContactNumberException(String contact) {
    super("Invalid contact number: " + contact);
  }
}
