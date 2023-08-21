package de.artsem.springcourse.Project2Boot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


import java.util.List;

@Entity
@Table(name = "Device")
public class Device {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 30 characters")
    private String name;

    @Column(name = "first_difficulty")
    private double firstDifficulty;

    @Column(name = "second_difficulty")
    private double secondDifficulty;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "deviceList")
    private List<Office> officeList;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "devices")
    private List<SystemType> systemTypeList;

    public Device() {
    }

    public Device(int id, String name, double firstDifficulty, double secondDifficulty) {
        this.id = id;
        this.name = name;
        this.firstDifficulty = firstDifficulty;
        this.secondDifficulty = secondDifficulty;
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

    public double getFirstDifficulty() {
        return firstDifficulty;
    }

    public void setFirstDifficulty(double firstDifficulty) {
        this.firstDifficulty = firstDifficulty;
    }

    public double getSecondDifficulty() {
        return secondDifficulty;
    }

    public void setSecondDifficulty(double secondDifficulty) {
        this.secondDifficulty = secondDifficulty;
    }

    public List<Office> getOfficeList() {
        return officeList;
    }

    public void setOfficeList(List<Office> officeList) {
        this.officeList = officeList;
    }

    public List<SystemType> getSystemTypeList() {
        return systemTypeList;
    }

    public void setSystemTypeList(List<SystemType> systemTypeList) {
        this.systemTypeList = systemTypeList;
    }

}
