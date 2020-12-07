package by.bsuir.hairdressingsalon.hairsalonapp.controller;

import by.bsuir.hairdressingsalon.hairsalonapp.entity.*;
import by.bsuir.hairdressingsalon.hairsalonapp.service.AppointmentService;
import by.bsuir.hairdressingsalon.hairsalonapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final CustomerService customerService;
    private final AppointmentService appointmentService;

    @Autowired
    public ProfileController(CustomerService customerService, AppointmentService appointmentService) {
        this.customerService = customerService;
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public String displayCustomerProfilePage(@AuthenticationPrincipal Customer customer, Model model) {
        List<ProcedureAppointment> appointmentsForCustomer = appointmentService.getAppointmentsForCustomer(customer);

        model.addAttribute("customer", customer);
        model.addAttribute("appointmentsForCustomer", appointmentsForCustomer);

        return "customer/profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@PathVariable Long id, @AuthenticationPrincipal Customer customer, Model model) {
        customer.setId(id);
        customerService.save(customer);

        return "redirect:/profile";
    }

    @GetMapping("/employees/edit/{id}")
    public String showEmployeeEditForm(@PathVariable Long id, Model model) {

        return "customer/profile-edit";
    }
}
