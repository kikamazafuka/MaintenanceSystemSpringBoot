package de.artsem.springcourse.Project2Boot.controllers;

import de.artsem.springcourse.Project2Boot.models.Employee;
import de.artsem.springcourse.Project2Boot.models.Office;
import de.artsem.springcourse.Project2Boot.services.EmployeeOfficeJoinEntityService;
import de.artsem.springcourse.Project2Boot.services.EmployeesService;
import de.artsem.springcourse.Project2Boot.services.OfficesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeesController {

    private final EmployeesService employeesService;
    private final OfficesService officesService;
    private final EmployeeOfficeJoinEntityService employeeOfficeJoinEntityService;

    @Autowired
    public EmployeesController(EmployeesService employeesService, OfficesService officesService,
                               EmployeeOfficeJoinEntityService employeeOfficeJoinEntityService) {
        this.employeesService = employeesService;
        this.officesService = officesService;
        this.employeeOfficeJoinEntityService = employeeOfficeJoinEntityService;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("employees", employeesService.findAll());
        return "employees/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable ("id") int id, Model model, @ModelAttribute ("office") Office office){
        Employee employee = employeesService.findById(id);
        List<Office> officesToSubtract = officesService.findAll();
        officesToSubtract.removeAll(employee.getOfficeList());

        model.addAttribute("employee", employee);
        model.addAttribute("assignedOffices", employee.getOfficeList());
        model.addAttribute("officesToAssign", officesToSubtract);
        return "employees/show";
    }

    @GetMapping("/new")
    public String newEmployee(@ModelAttribute("employee")Employee employee){
        return "employees/new";
    }

    @PostMapping
    public String create(@ModelAttribute("employee") @Valid Employee employee,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "employees/new";
        }
        employeesService.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("employee", employeesService.findById(id));
        return "employees/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Employee employee,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (bindingResult.hasErrors()){
            return "employees/edit";
        }
        employeesService.update(id, employee);
        return "redirect:/employees";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        employeesService.delete(id);
        return "redirect:/people";
    }

    @PatchMapping("/{id}/assign")
    public String assignToOffice(@PathVariable("id") int id, @ModelAttribute("office") Office office){
        employeesService.assign(id, office.getId());
        //TODO validate for existing office in current employee entity
        return "redirect:/employees/"+id;
    }

    @PatchMapping("/{id}/delete_office")
    public String deleteOffice(@PathVariable("id") int id, @ModelAttribute("office") Office office){
        employeesService.delete(id, office.getId());
        return "redirect:/employees/"+id;
    }
}
