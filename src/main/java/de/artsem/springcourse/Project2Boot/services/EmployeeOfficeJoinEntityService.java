package de.artsem.springcourse.Project2Boot.services;

import de.artsem.springcourse.Project2Boot.models.Employee;
import de.artsem.springcourse.Project2Boot.models.EmployeeOfficeJoinEntity;
import de.artsem.springcourse.Project2Boot.models.Office;
import de.artsem.springcourse.Project2Boot.repositories.EmployeeOfficeJoinEntityRepository;
import de.artsem.springcourse.Project2Boot.repositories.EmployeesRepository;
import de.artsem.springcourse.Project2Boot.repositories.OfficesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class EmployeeOfficeJoinEntityService {

    private final EmployeeOfficeJoinEntityRepository employeeOfficeJoinEntityRepository;
    private final EmployeesRepository employeesRepository;
    private final OfficesRepository officesRepository;

    public EmployeeOfficeJoinEntityService(EmployeeOfficeJoinEntityRepository employeeOfficeJoinEntityRepository, EmployeesRepository employeesRepository, OfficesRepository officesRepository) {
        this.employeeOfficeJoinEntityRepository = employeeOfficeJoinEntityRepository;
        this.employeesRepository = employeesRepository;
        this.officesRepository = officesRepository;
    }

//    @Transactional
//    public void assign(int employeeId, int officeId){
//        Employee employee = employeesRepository.findById(employeeId).get();
//        Office office = officesRepository.findById(officeId).get();
//        employee.getOfficeList().add(office);
//        try {
//            employeesRepository.save(employee);
//        } catch (Exception e){
//            //TODO catch if office already exists in employee object
//            System.out.println("already exist");
//        }
//    }
//
//    @Transactional
//    public void delete (int employeeId, int officeId){
//        Employee employee = employeesRepository.findById(employeeId).get();
//        Office office = officesRepository.findById(officeId).get();
//        employee.getOfficeList().remove(office);
//    }
}
