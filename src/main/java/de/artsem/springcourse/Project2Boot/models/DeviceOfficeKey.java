//package de.artsem.springcourse.Project2Boot.models;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Embeddable;
//
//import java.io.Serializable;
//
//@Embeddable
//public class DeviceOfficeKey implements Serializable {
//
//    @Column(name = "device_id")
//    private int deviceId;
//
//    @Column(name = "office_id")
//    private int officeId;
//
//    public DeviceOfficeKey() {
//    }
//
//    public DeviceOfficeKey(int deviceId, int officeId) {
//        this.deviceId = deviceId;
//        this.officeId = officeId;
//    }
//
//    public int getDeviceId() {
//        return deviceId;
//    }
//
//    public void setDeviceId(int deviceId) {
//        this.deviceId = deviceId;
//    }
//
//    public int getOfficeId() {
//        return officeId;
//    }
//
//    public void setOfficeId(int officeId) {
//        this.officeId = officeId;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        DeviceOfficeKey that = (DeviceOfficeKey) o;
//
//        if (deviceId != that.deviceId) return false;
//        return officeId == that.officeId;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = deviceId;
//        result = 31 * result + officeId;
//        return result;
//    }
//}
