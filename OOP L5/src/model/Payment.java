package model;

import java.util.Date;

public class Payment {
    private Person person;
    private double amount;
    private Date date;
    private String status;

    public Payment(Person person, double amount, Date date, String status) {
        this.person = person;
        this.amount = amount;
        this.date = date;
        this.status = status;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("Payment: %s, Amount=%.2f, Date=%s, Status=%s",
                person == null ? "Unknown" : person.getFirstName() + " " + person.getLastName(),
                amount, date == null ? "Unknown" : new java.text.SimpleDateFormat("yyyy-MM-dd").format(date),
                status == null ? "Unknown" : status);
    }
}
