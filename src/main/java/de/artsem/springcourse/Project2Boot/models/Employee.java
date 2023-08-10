package de.artsem.springcourse.Project2Boot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 30 characters")
    private String name;

    @Column(name = "surname")
    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 30 characters")
    private String surname;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateOfBirth;

    @Column(name = "employee_type")
    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "employee_office",
            joinColumns =
    @JoinColumn(name = "employee_id"),
    inverseJoinColumns =
    @JoinColumn(name = "office_id"))
    private List<Office> officeList;

    @OneToMany(mappedBy = "employee")
    private List<EmployeeOfficeJoinEntity> employeeOfficeJoinEntityList;

    public Employee() {
    }

    public Employee(String name, String surname, Date dateOfBirth, EmployeeType employeeType) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.employeeType = employeeType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public List<Office> getOfficeList() {
        return officeList;
    }

    public void setOfficeList(List<Office> officeList) {
        this.officeList = officeList;
    }

    public List<EmployeeOfficeJoinEntity> getEmployeeOfficeJoinEntityList() {
        return employeeOfficeJoinEntityList;
    }

    public void setEmployeeOfficeJoinEntityList(List<EmployeeOfficeJoinEntity> employeeOfficeJoinEntityList) {
        this.employeeOfficeJoinEntityList = employeeOfficeJoinEntityList;
    }

    public double countWorkLoad(){
        double result = 0;
        for (Office office : officeList) {
            result += office.getOfficeFirstDifficulty();
        }
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", employeeType='" + employeeType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (id != employee.id) return false;
        if (!Objects.equals(name, employee.name)) return false;
        if (!Objects.equals(surname, employee.surname)) return false;
        if (!Objects.equals(dateOfBirth, employee.dateOfBirth))
            return false;
        return employeeType == employee.employeeType;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (employeeType != null ? employeeType.hashCode() : 0);
        return result;
    }
}
