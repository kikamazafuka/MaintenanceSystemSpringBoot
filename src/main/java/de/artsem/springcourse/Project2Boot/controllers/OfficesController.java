package de.artsem.springcourse.Project2Boot.controllers;

import de.artsem.springcourse.Project2Boot.models.Device;
import de.artsem.springcourse.Project2Boot.models.DeviceOfficeJoinEntity;
import de.artsem.springcourse.Project2Boot.models.Employee;
import de.artsem.springcourse.Project2Boot.models.Office;
import de.artsem.springcourse.Project2Boot.services.DevicesService;
import de.artsem.springcourse.Project2Boot.services.EmployeesService;
import de.artsem.springcourse.Project2Boot.services.OfficesService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/offices")
public class OfficesController {

    private final OfficesService officesService;
    private final EmployeesService employeesService;
    private final DevicesService devicesService;

    public OfficesController(OfficesService officesService, EmployeesService employeesService, DevicesService devicesService) {
        this.officesService = officesService;
        this.employeesService = employeesService;
        this.devicesService = devicesService;
    }

    @GetMapping
    public String index (Model model){
        model.addAttribute("offices", officesService.findAll());
        return  "offices/index";
    }

    @GetMapping("/new")
    public String newOffice(@ModelAttribute ("office") Office office){
        return "offices/new";
    }

    @PostMapping
    public String create(@ModelAttribute ("office") @Valid Office office,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "offices/new";
        }
        officesService.save(office);
        return "redirect:/offices";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model,
                       @ModelAttribute ("employee") Employee employee,
                       @ModelAttribute ("device") Device device){
        model.addAttribute("office", officesService.findById(id));
        Office office = officesService.findById(id);
        List<Employee> employees = office.getEmployeeList();
        List<Device> devices = office.getDeviceList();

        //get list of devices that current office doesn't contain
        List<Device> devicesToSubtract = devicesService.findAll();
        devicesToSubtract.removeAll(office.getDeviceList());
        //TODO set quantity of devices on object
        List<DeviceOfficeJoinEntity> deviceOfficeJoinEntity = office.getDeviceOfficeJoinEntityList();

        model.addAttribute("deviceOffice", deviceOfficeJoinEntity);
        model.addAttribute("assignedEmployees", employees);
        model.addAttribute("assignedDevices", devices);
        model.addAttribute("devicesToAssign", devicesToSubtract);
        return "offices/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("office", officesService.findById(id));
        return "offices/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("office") @Valid Office office, BindingResult bindingResult, @PathVariable("id") int id){
        if (bindingResult.hasErrors()){
            return "offices/edit";
        }
        officesService.update(id, office);
        return "redirect:/offices";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable ("id") int id){
        officesService.delete(id);
        return "redirect:/offices";
    }

}
