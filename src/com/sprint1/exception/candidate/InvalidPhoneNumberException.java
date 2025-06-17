package exception.candidate;

public class InvalidPhoneNumberException extends RuntimeException {
  public InvalidPhoneNumberException(String phone) {
    super("Invalid phone number: " + phone);
  }
}
