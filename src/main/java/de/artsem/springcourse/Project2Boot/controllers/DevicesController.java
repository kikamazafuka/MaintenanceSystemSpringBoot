package de.artsem.springcourse.Project2Boot.controllers;

import de.artsem.springcourse.Project2Boot.models.Book;
import de.artsem.springcourse.Project2Boot.models.Device;
import de.artsem.springcourse.Project2Boot.models.Employee;
import de.artsem.springcourse.Project2Boot.models.Office;
import de.artsem.springcourse.Project2Boot.services.DevicesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/devices")
public class DevicesController {
    private final DevicesService deviceService;

    @Autowired
    public DevicesController(DevicesService deviceService) {
        this.deviceService = deviceService;
    }

    @RequestMapping()
    private String index(Model model){
        model.addAttribute("devices", deviceService.findAll());
        return "devices/index";
    }

    @GetMapping("/new")
    public String newDevice(@ModelAttribute("device") Device device){
        return "devices/new";
    }

    @PostMapping
    public String create(@ModelAttribute ("device") @Valid Device device,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "devices/new";
        }
        deviceService.save(device);
        return "redirect:/devices";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute ("device") Device device){
        model.addAttribute("device", deviceService.findById(id));

//        Office office = officesService.findById(id);
//        List<Employee> employees = office.getEmployeeList();
//        List<Device> devices = office.getDeviceList();
//        model.addAttribute("assignedEmployees", employees);
//        model.addAttribute("assignedDevices", devices);
        return "devices/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("device", deviceService.findById(id));
        return "devices/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("device") @Valid Device device, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "devices/edit";

        deviceService.update(id, device);
        return "redirect:/devices";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        deviceService.delete(id);
        return "redirect:/devices";
    }

    @PatchMapping("/{id}/assign")
    public String assignToOffice(@PathVariable("id") int id, @ModelAttribute("device") Device device,
                                 @RequestParam(required = false, name = "quantity") int quantity){
        deviceService.assign(id, device.getId(), quantity);
        //TODO validate for existing office in current employee entity
        return "redirect:/offices/"+id;
    }

    @PatchMapping("/{id}/delete_device")
    public String deleteOffice(@PathVariable("id") int id, @ModelAttribute("device") Device device){
        deviceService.delete(device.getId(), id);
        return "redirect:/offices/"+id;
    }
}
