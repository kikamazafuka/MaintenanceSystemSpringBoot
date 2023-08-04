package de.artsem.springcourse.Project2Boot.models;

import jakarta.persistence.*;

@Entity
@Table(name = "device_office")
public class DeviceOfficeJoinEntity {

    @EmbeddedId
    private DeviceOfficeKey id;

    @ManyToOne
    @MapsId("deviceId")
    @JoinColumn(name = "device_id")
    private Device device;

    @ManyToOne
    @MapsId("officeId")
    @JoinColumn(name = "office_id")
    private Office office;

    @Column(name = "quantity")
    private int quantity;

    public DeviceOfficeJoinEntity() {
    }

    public DeviceOfficeJoinEntity(DeviceOfficeKey id, Device device, Office office, int quantity) {
        this.id = id;
        this.device = device;
        this.office = office;
        this.quantity = quantity;
    }

    public DeviceOfficeKey getId() {
        return id;
    }

    public void setId(DeviceOfficeKey id) {
        this.id = id;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getFullFirstDifficulty(){
        return device.getFirstDifficulty()*quantity;
    }

    public double getFullSecondDifficulty(){
        return device.getSecondDifficulty()*quantity;
    }

}
