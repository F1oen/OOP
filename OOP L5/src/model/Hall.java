package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hall {
    private String hallName;
    private int capacity;
    private final List<Student> students;
    private final List<Employee> employees;

    public Hall(String hallName, int capacity) {
        this.hallName = hallName;
        this.capacity = capacity;
        this.students = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }

    public List<Employee> getEmployees() {
        return Collections.unmodifiableList(employees);
    }

    public void clearAssignments() {
        students.clear();
        employees.clear();
    }

    public boolean addStudent(Student s) {
        if (students.size() < capacity) {
            students.add(s);
            return true;
        }
        return false;
    }

    public void addEmployee(Employee e) {
        employees.add(e);
    }

    @Override
    public String toString() {
        return String.format("Hall %s (Capacity=%d, Students=%d, Employees=%d)",
                hallName, capacity, students.size(), employees.size());
    }
}
