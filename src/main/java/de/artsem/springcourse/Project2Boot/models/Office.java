package de.artsem.springcourse.Project2Boot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "office")
public class Office {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    @Column(name = "name",
            updatable = false)
    @NotEmpty(message = "Name shouldn't be empty")
    private String name;

    @Column(name = "address")
    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{1,3}",
            message = "Address should be in format: City, Street, House number (1-3 digits)")
    private String address;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "officeList")
    private List<Employee> employeeList;

    @OneToMany(mappedBy = "office")
    private List<EmployeeOfficeJoinEntity> employeeOfficeJoinEntityList;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "device_office",
            joinColumns =
            @JoinColumn(name = "office_id"),
            inverseJoinColumns =
            @JoinColumn(name = "device_id"))
    private List<Device> deviceList;

    @OneToMany(mappedBy = "office")
    List<DeviceOffice> deviceOfficeList;

    public Office() {
    }

    public Office(String name, String address) {
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public void addEmployee(Employee employee){
        employeeList.add(employee);
        employee.getOfficeList().add(this);
    }

    public List<EmployeeOfficeJoinEntity> getEmployeeOfficeJoinEntityList() {
        return employeeOfficeJoinEntityList;
    }

    public void setEmployeeOfficeJoinEntityList(List<EmployeeOfficeJoinEntity> employeeOfficeJoinEntityList) {
        this.employeeOfficeJoinEntityList = employeeOfficeJoinEntityList;
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

    public List<DeviceOffice> getDeviceOfficeList() {
        return deviceOfficeList;
    }

    public void setDeviceOfficeList(List<DeviceOffice> deviceOfficeList) {
        this.deviceOfficeList = deviceOfficeList;
    }

    public double getOfficeFirstDifficulty(){
        double fullOfficeDifficulty = 0;
        for (DeviceOffice deviceOffice : deviceOfficeList){
           fullOfficeDifficulty+= deviceOffice.getFullFirstDifficulty();
        }
        return fullOfficeDifficulty;
    }

    public double getOfficeSecondDifficulty(){
        double secondFullOfficeDifficulty = 0;
        for (DeviceOffice deviceOffice : deviceOfficeList){
            secondFullOfficeDifficulty += deviceOffice.getFullSecondDifficulty();
        }
        return secondFullOfficeDifficulty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Office office = (Office) o;

        if (id != office.id) return false;
        if (!Objects.equals(name, office.name)) return false;
        return Objects.equals(address, office.address);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
