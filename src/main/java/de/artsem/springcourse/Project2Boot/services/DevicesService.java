package de.artsem.springcourse.Project2Boot.services;

import de.artsem.springcourse.Project2Boot.models.Device;
import de.artsem.springcourse.Project2Boot.models.DeviceOffice;
import de.artsem.springcourse.Project2Boot.models.Office;
import de.artsem.springcourse.Project2Boot.repositories.DeviceOfficeRepository;
import de.artsem.springcourse.Project2Boot.repositories.DevicesRepository;
import de.artsem.springcourse.Project2Boot.repositories.OfficesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DevicesService {

    private final DevicesRepository devicesRepository;
    private final OfficesRepository officesRepository;
    private final DeviceOfficeRepository deviceOfficeRepository;
//    private final DeviceOfficeJoinEntityRepository deviceOfficeJoinEntityRepository;

    public DevicesService(DevicesRepository devicesRepository, OfficesRepository officesRepository,
//                          DeviceOfficeJoinEntityRepository deviceOfficeJoinEntityRepository
                          DeviceOfficeRepository deviceOfficeRepository) {
        this.devicesRepository = devicesRepository;
        this.officesRepository = officesRepository;
//        this.deviceOfficeJoinEntityRepository = deviceOfficeJoinEntityRepository;
        this.deviceOfficeRepository = deviceOfficeRepository;
    }

    public List<Device> findAll() {
        return devicesRepository.findAll();
    }

    @Transactional
    public void save(Device device) {
        devicesRepository.save(device);
    }

    public Device findById(int id) {
        return devicesRepository.findById(id).orElse(null);
    }

    @Transactional
    public void delete(int id) {
        devicesRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, Device updatedDevice) {
        updatedDevice.setId(id);
        devicesRepository.save(updatedDevice);
    }

//    @Transactional
//    public void assign(int officeId, int deviceId ){
//        Device device  = devicesRepository.findById(deviceId).get();
//        Office office = officesRepository.findById(officeId).get();
//        office.getDeviceList().add(device);
//        DeviceOfficeJoinEntity deviceOfficeJoinEntity = new DeviceOfficeJoinEntity();
//        deviceOfficeJoinEntity.setDevice(device);
//        deviceOfficeJoinEntity.setOffice(office);
//        deviceOfficeJoinEntity.setQuantity(1);
//        office.getDeviceOfficeJoinEntityList().add(deviceOfficeJoinEntity);
////        deviceOfficeJoinEntityRepository.save(deviceOfficeJoinEntity);
////        List<DeviceOfficeJoinEntity> deviceOfficeJoinEntityList = office.getDeviceOfficeJoinEntityList();
////        for (DeviceOfficeJoinEntity deviceOfficeJoinEntity : deviceOfficeJoinEntityList){
////            if (deviceOfficeJoinEntity.getDevice().getId()==deviceId){
////                deviceOfficeJoinEntity.setQuantity(1);
////            }
////        }
//        try {
//            officesRepository.save(office);
//        } catch (Exception e){
//            //TODO catch if device already exists in office object
//            System.out.println("already exist");
//        }
//    }

    @Transactional
    public void assign(int officeId, int deviceId, int quantity){
        if (quantity<=0){
            quantity=1;
        }
        DeviceOffice deviceOffice = new DeviceOffice();
        Optional<Device> optionalDevice = devicesRepository.findById(deviceId);
        Optional<Office> optionalOffice = officesRepository.findById(officeId);
        if (optionalDevice.isPresent() && optionalOffice.isPresent()){
            deviceOffice.setDevice(optionalDevice.get());
            deviceOffice.setOffice(optionalOffice.get());
            deviceOffice.setQuantity(quantity);
            deviceOfficeRepository.save(deviceOffice);
        }
    }

    @Transactional
    public void delete (int deviceId , int officeId ){
        Optional<Office> optionalOffice = officesRepository.findById(officeId);
        Optional<Device> optionalDevice = devicesRepository.findById(deviceId);
        if (optionalDevice.isPresent() && optionalOffice.isPresent()){
            DeviceOffice deviceOffice = deviceOfficeRepository
                    .findByOfficeAndDevice(optionalOffice.get(), optionalDevice.get());
            deviceOfficeRepository.delete(deviceOffice);
        }
    }
}
