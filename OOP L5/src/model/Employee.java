package model;

import java.util.Date;

public class Employee extends Person {
    private String jobTitle;
    private double salary;

    public Employee(String firstName, String lastName, Date dateOfBirth, Date registrationDate,
                    String jobTitle, double salary) {
        super(firstName, lastName, dateOfBirth, registrationDate);
        this.jobTitle = jobTitle;
        this.salary = salary;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("EMPLOYEE: %s %s, DOB=%s, Registered=%s, JobTitle=%s, Salary=%.2f",
                getFirstName(), getLastName(), formatDate(getDateOfBirth()), formatDate(getRegistrationDate()),
                jobTitle, salary);
    }
}
