package de.artsem.springcourse.Project2Boot.models;

import jakarta.persistence.*;

@Entity
@Table(name = "device_office")
public class DeviceOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @ManyToOne
    @JoinColumn(name = "office_id")
    private Office office;

    @ManyToOne
    @JoinColumn(name = "system_type_id")
    private SystemType systemType;

    @Column(name = "quantity")
    private int quantity;

    public DeviceOffice() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
