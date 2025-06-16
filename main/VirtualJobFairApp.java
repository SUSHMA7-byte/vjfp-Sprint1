package com.sprint1.main;

import com.sprint1.model.Candidate;

import java.util.Scanner;

public class VirtualJobFairApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== VIRTUAL JOB FAIR PORTAL =====");
            System.out.println("1. Candidate");
            System.out.println("2. Company");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int mainChoice = sc.nextInt();
            sc.nextLine();

            switch (mainChoice) {
                case 1:
                    Candidate candidate = new Candidate();
                    candidate.candidateFlow(sc);
                    break;
                case 2:
                    System.out.println("Company functionality coming soon...");
                    break;
                case 3:
                    System.out.println("Thank you for using the Virtual Job Fair Portal!");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
