package de.artsem.springcourse.Project2Boot.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class EmployeeOfficeKey implements Serializable {

    @Column(name = "employee_id")
    private int employeeId;

    @Column(name = "office_id")
    private int officeId;

    public EmployeeOfficeKey() {
    }

    public EmployeeOfficeKey(int employeeId, int officeId) {
        this.employeeId = employeeId;
        this.officeId = officeId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeOfficeKey that = (EmployeeOfficeKey) o;

        if (employeeId != that.employeeId) return false;
        return officeId == that.officeId;
    }

    @Override
    public int hashCode() {
        int result = employeeId;
        result = 31 * result + officeId;
        return result;
    }
}
