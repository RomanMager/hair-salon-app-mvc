package by.bsuir.hairdressingsalon.hairsalonapp.controller;

import by.bsuir.hairdressingsalon.hairsalonapp.entity.Customer;
import by.bsuir.hairdressingsalon.hairsalonapp.entity.Employee;
import by.bsuir.hairdressingsalon.hairsalonapp.entity.Gender;
import by.bsuir.hairdressingsalon.hairsalonapp.entity.SalonProcedure;
import by.bsuir.hairdressingsalon.hairsalonapp.service.EmployeeService;
import by.bsuir.hairdressingsalon.hairsalonapp.service.GenderService;
import by.bsuir.hairdressingsalon.hairsalonapp.service.SalonProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.naming.Binding;
import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize(value = "hasAuthority('ADMIN')")
public class AdminController {

    private final EmployeeService employeeService;
    private final SalonProcedureService procedureService;
    private final GenderService genderService;

    @Autowired
    public AdminController(EmployeeService employeeService,
                           SalonProcedureService procedureService,
                           GenderService genderService) {
        this.employeeService = employeeService;
        this.procedureService = procedureService;
        this.genderService = genderService;
    }

    @GetMapping
    public String showAdminControlPage(@AuthenticationPrincipal Customer admin, Model model) {
        model.addAttribute("admin", admin);
        return "admin/admin-control";
    }

    @GetMapping("/employees")
    public String showEmployeeManagementPage(@AuthenticationPrincipal Customer admin, Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);

        return "admin/employee-management";
    }

    @GetMapping("/employees/new")
    public String showEmployeeCreationForm(@AuthenticationPrincipal Customer admin, Model model) {
        Employee employee = new Employee();
        List<SalonProcedure> procedures = procedureService.getAllProcedures();
        List<Gender> genders = genderService.getAllGenders();
        model.addAttribute("employee", employee);
        model.addAttribute("procedures", procedures);
        model.addAttribute("genders", genders);

        return "admin/new-employee";
    }

    @PostMapping("/employees/save")
    public String createEmployee(Employee employee, BindingResult bindingResult, Model model) {
        employeeService.save(employee);

        return "redirect:/admin/employees";
    }
}
