package de.artsem.springcourse.Project2Boot.models;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_office")
public class EmployeeOfficeJoinEntity {

    @EmbeddedId
    private EmployeeOfficeKey id;

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @MapsId("officeId")
    @JoinColumn(name = "office_id")
    private Office office;

    public EmployeeOfficeJoinEntity(EmployeeOfficeKey id, Employee employee, Office office) {
        this.id = id;
        this.employee = employee;
        this.office = office;
    }

    public EmployeeOfficeJoinEntity() {

    }

    public EmployeeOfficeKey getId() {
        return id;
    }

    public void setId(EmployeeOfficeKey id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }
}
