package de.artsem.springcourse.Project2Boot.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "system_type")
public class SystemType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String systemTypeName;

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
        this.systemTypeName = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSystemTypeName() {
        return systemTypeName;
    }

    public void setSystemTypeName(String systemTypeName) {
        this.systemTypeName = systemTypeName;
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
