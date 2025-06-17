package com.sprint1.exception.employee;

public class InvalidEmployeeRoleException extends Exception {
  public InvalidEmployeeRoleException(String role) {
    super("Invalid employee role: " + role);
  }
}
