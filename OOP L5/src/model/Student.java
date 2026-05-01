package model;

import java.util.Date;

public class Student extends Person {
    private String course;
    private String specialNeeds;
    private String diet;
    private Hall hallAssigned;

    public Student(String firstName, String lastName, Date dateOfBirth, Date registrationDate,
                   String course, String specialNeeds, String diet) {
        super(firstName, lastName, dateOfBirth, registrationDate);
        this.course = course;
        this.specialNeeds = specialNeeds;
        this.diet = diet;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSpecialNeeds() {
        return specialNeeds;
    }

    public void setSpecialNeeds(String specialNeeds) {
        this.specialNeeds = specialNeeds;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public Hall getHallAssigned() {
        return hallAssigned;
    }

    public void assignHall(Hall hall) {
        this.hallAssigned = hall;
    }

    @Override
    public String toString() {
        return String.format("STUDENT: %s %s, DOB=%s, Registered=%s, Course=%s, SpecialNeeds=%s, Diet=%s, Hall=%s",
                getFirstName(), getLastName(), formatDate(getDateOfBirth()), formatDate(getRegistrationDate()),
                course, specialNeeds == null || specialNeeds.isEmpty() ? "None" : specialNeeds,
                diet == null || diet.isEmpty() ? "None" : diet,
                hallAssigned == null ? "Unassigned" : hallAssigned.getHallName());
    }
}
