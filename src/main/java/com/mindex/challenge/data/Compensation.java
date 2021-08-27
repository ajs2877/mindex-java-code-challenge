package com.mindex.challenge.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Compensation {
    private String employeeId;
    private double salary;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date effectiveDate;

    public Compensation() {
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeId(){
        return this.employeeId;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }
}
