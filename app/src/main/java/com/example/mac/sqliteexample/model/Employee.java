package com.example.mac.sqliteexample.model;

/**
 * Created by MAC on 11/13/16.
 */

public class Employee {

    private long id;
    private String firstName;
    private String lastName;
    private String department;
    private String designation;


    public Employee(String firstName, String lastName, String department, String designation) {
        this.id = 0;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.designation = designation;
    }

    public Employee() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getFullNameOfEmployee() {
        StringBuilder sb = new StringBuilder();
        sb.append(getFirstName()).append(" ").append(getLastName());
        return sb.toString();
    }
}
