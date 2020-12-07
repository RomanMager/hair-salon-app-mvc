package by.bsuir.hairdressingsalon.hairsalonapp.controller;

import by.bsuir.hairdressingsalon.hairsalonapp.entity.Customer;
import by.bsuir.hairdressingsalon.hairsalonapp.entity.Employee;
import by.bsuir.hairdressingsalon.hairsalonapp.entity.ProcedureAppointment;
import by.bsuir.hairdressingsalon.hairsalonapp.entity.SalonProcedure;
import by.bsuir.hairdressingsalon.hairsalonapp.service.AppointmentService;
import by.bsuir.hairdressingsalon.hairsalonapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
}
