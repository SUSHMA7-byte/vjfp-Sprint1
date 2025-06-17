package com.sprint1.exception.applications;

public class CandidateNotEligibleException extends Exception {
  public CandidateNotEligibleException(String reason) {
    super("Candidate not eligible: " + reason);
  }
}
