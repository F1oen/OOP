package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Date registrationDate;

    public Person(String firstName, String lastName, Date dateOfBirth, Date registrationDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.registrationDate = registrationDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    protected String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    @Override
    public String toString() {
        return String.format("Person: %s %s, DOB=%s, Registered=%s",
                firstName, lastName, formatDate(dateOfBirth), formatDate(registrationDate));
    }
}
