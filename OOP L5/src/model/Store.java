package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Store {
    private final List<Person> records;
    private int currentIndex;
    private final List<Hall> halls;
    private final SimpleDateFormat dateFormat;

    public Store() {
        this.records = new ArrayList<>();
        this.currentIndex = 0;
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.halls = new ArrayList<>();
        halls.add(new Hall("Ground Floor Hall", 5));
        halls.add(new Hall("Dietary Support Hall", 10));
        halls.add(new Hall("Standard Hall", 20));
        halls.add(new Hall("Staff Hall", 10));
    }

    public void addRecord(Person p) {
        if (p instanceof Student) {
            Student student = (Student) p;
            Hall hall = chooseHallForStudent(student);
            if (hall != null && hall.addStudent(student)) {
                student.assignHall(hall);
            }
        } else if (p instanceof Employee) {
            Hall staffHall = halls.stream()
                    .filter(h -> "Staff Hall".equals(h.getHallName()))
                    .findFirst().orElse(null);
            if (staffHall != null) {
                staffHall.addEmployee((Employee) p);
            }
        }
        records.add(p);
    }

    private Hall chooseHallForStudent(Student student) {
        String needs = student.getSpecialNeeds();
        String diet = student.getDiet();
        if (needs != null && needs.toLowerCase().contains("groundfloor")) {
            return halls.stream().filter(h -> "Ground Floor Hall".equals(h.getHallName())).findFirst().orElse(null);
        }
        if (diet != null) {
            String lowered = diet.toLowerCase();
            if (lowered.contains("vegan") || lowered.contains("vegetarian")) {
                return halls.stream().filter(h -> "Dietary Support Hall".equals(h.getHallName())).findFirst().orElse(null);
            }
        }
        return halls.stream().filter(h -> "Standard Hall".equals(h.getHallName())).findFirst().orElse(null);
    }

    public Person nextRecord() {
        if (records.isEmpty()) {
            return null;
        }
        Person next = records.get(currentIndex);
        currentIndex = (currentIndex + 1) % records.size();
        return next;
    }

    public void saveToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Person person : records) {
                if (person instanceof Student) {
                    Student student = (Student) person;
                    writer.write(String.join(";",
                            "STUDENT",
                            safe(student.getFirstName()),
                            safe(student.getLastName()),
                            dateFormat.format(student.getDateOfBirth()),
                            dateFormat.format(student.getRegistrationDate()),
                            safe(student.getCourse()),
                            safe(student.getSpecialNeeds()),
                            safe(student.getDiet())));
                } else if (person instanceof Employee) {
                    Employee employee = (Employee) person;
                    writer.write(String.join(";",
                            "EMPLOYEE",
                            safe(employee.getFirstName()),
                            safe(employee.getLastName()),
                            dateFormat.format(employee.getDateOfBirth()),
                            dateFormat.format(employee.getRegistrationDate()),
                            safe(employee.getJobTitle()),
                            Double.toString(employee.getSalary())));
                } else {
                    continue;
                }
                writer.newLine();
            }
        }
    }

    private String safe(String value) {
        return value == null ? "" : value;
    }

    public void loadFromFile(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            throw new IOException("File not found: " + filename);
        }
        records.clear();
        currentIndex = 0;
        halls.forEach(Hall::clearAssignments);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split(";");
                if (parts.length < 7) {
                    continue;
                }
                String type = parts[0];
                String firstName = parts[1];
                String lastName = parts[2];
                Date dob = parseDate(parts[3]);
                Date regDate = parseDate(parts[4]);
                if ("STUDENT".equalsIgnoreCase(type)) {
                    String course = parts[5];
                    String specialNeeds = parts[6];
                    String diet = parts.length > 7 ? parts[7] : "None";
                    Student student = new Student(firstName, lastName, dob, regDate, course, specialNeeds, diet);
                    addRecord(student);
                } else if ("EMPLOYEE".equalsIgnoreCase(type)) {
                    String jobTitle = parts[5];
                    double salary = 0.0;
                    try {
                        salary = Double.parseDouble(parts[6]);
                    } catch (NumberFormatException ignore) {
                    }
                    Employee employee = new Employee(firstName, lastName, dob, regDate, jobTitle, salary);
                    addRecord(employee);
                }
            }
        }
    }

    private Date parseDate(String text) {
        try {
            return dateFormat.parse(text);
        } catch (ParseException e) {
            return new Date();
        }
    }

    public List<Person> getRecords() {
        return records;
    }

    public String getHallSummary() {
        StringBuilder summary = new StringBuilder();
        for (Hall hall : halls) {
            summary.append(hall.toString()).append("\n");
        }
        summary.append("Total records: ").append(records.size()).append("\n");
        return summary.toString();
    }
}
