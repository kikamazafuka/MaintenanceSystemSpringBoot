package de.artsem.springcourse.Project2Boot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
@Table(name = "system_type")
public class SystemType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "System name should not be empty")
    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "device_office",
            joinColumns =
            @JoinColumn(name = "system_type_id"),
            inverseJoinColumns =
            @JoinColumn(name = "device_id"))
    private List<Device> devices;

    @OneToMany(mappedBy = "systemType")
    List<DeviceOffice> deviceOfficeList;

    public SystemType() {
    }
    public SystemType(int id, String name) {
        this.id = id;
        this.name = name;
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

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public List<DeviceOffice> getDeviceOfficeList() {
        return deviceOfficeList;
    }

    public void setDeviceOfficeList(List<DeviceOffice> deviceOfficeList) {
        this.deviceOfficeList = deviceOfficeList;
    }
}
