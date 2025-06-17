package exception.company;

public class CompanyNotFoundException extends RuntimeException {
  public CompanyNotFoundException(int companyId) {
    super("Company not found with ID: " + companyId);
  }
}
