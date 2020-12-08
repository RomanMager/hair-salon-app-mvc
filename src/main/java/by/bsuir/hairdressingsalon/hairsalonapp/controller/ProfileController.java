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

        Customer customerData = customerService.findByLoginOrEmail(customer.getLogin(), null).orElseThrow();
        model.addAttribute("customer", customerData);
        model.addAttribute("appointmentsForCustomer", appointmentsForCustomer);

        return "customer/profile";
    }

    @GetMapping("/edit")
    public String showEmployeeEditForm(@AuthenticationPrincipal Customer customer, Model model) {
        model.addAttribute("customer", customer);
        return "customer/profile-edit";
    }

    @PostMapping
    public String updateProfile(@AuthenticationPrincipal Customer customer, Customer updated, Model model) {
        // updated.setId(customer.getId());
        // customerService.save(updated);

        customerService.updateProfile(customer, updated);

        model.addAttribute("customer", updated);
        return "redirect:/profile";
    }
}
