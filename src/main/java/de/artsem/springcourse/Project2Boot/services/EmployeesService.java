package de.artsem.springcourse.Project2Boot.services;

import de.artsem.springcourse.Project2Boot.models.Employee;
import de.artsem.springcourse.Project2Boot.models.EmployeeType;
import de.artsem.springcourse.Project2Boot.models.Office;
import de.artsem.springcourse.Project2Boot.repositories.EmployeesRepository;
import de.artsem.springcourse.Project2Boot.repositories.OfficesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class EmployeesService {
    private final EmployeesRepository employeesRepository;
    private final OfficesRepository officesRepository;

    @Autowired
    public EmployeesService(EmployeesRepository employeesRepository, OfficesRepository officesRepository) {
        this.employeesRepository = employeesRepository;
        this.officesRepository = officesRepository;
    }

    public List<Employee> findAll(){
        return employeesRepository.findAll();
    }

    public Employee findById(int id) {
        Optional<Employee> foundEmployee = employeesRepository.findById(id);
        return foundEmployee.orElse(null);
    }

    @Transactional
    public void save(Employee employee){
        employee.setEmployeeType(EmployeeType.JUNIOR);
        employeesRepository.save(employee);
    }

    @Transactional
    public void update(int id, Employee updatedEmployee) {
        updatedEmployee.setId(id);
        updatedEmployee.setEmployeeType(EmployeeType.JUNIOR);
        employeesRepository.save(updatedEmployee);
    }

    @Transactional
    public void delete(int id) {
        employeesRepository.deleteById(id);
    }

    public void assignEmployeeToOffice(int id, Office office) {


    }

    @Transactional
    public void assign(int employeeId, int officeId){
        Employee employee = employeesRepository.findById(employeeId).get();
        Office office = officesRepository.findById(officeId).get();
        employee.getOfficeList().add(office);
        try {
            employeesRepository.save(employee);
        } catch (Exception e){
            //TODO catch if office already exists in employee object
            System.out.println("already exist");
        }
    }

    @Transactional
    public void delete (int employeeId, int officeId){
        Employee employee = employeesRepository.findById(employeeId).get();
        Office office = officesRepository.findById(officeId).get();
        employee.getOfficeList().remove(office);
    }
}
