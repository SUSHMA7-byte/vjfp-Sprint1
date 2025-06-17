package exception.company;

public class DuplicateCompanyEmailException extends RuntimeException {
  public DuplicateCompanyEmailException(String email) {
    super("Company email already exists: " + email);
  }
}
