package by.samsonnik.shopSpringApp.demo.controllers;

import by.samsonnik.shopSpringApp.demo.dao.ClientDao;
import by.samsonnik.shopSpringApp.demo.models.Client;
import by.samsonnik.shopSpringApp.demo.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.beans.Transient;

@Controller
public class LoginController {

    private final RegistrationService registrationService;

    public LoginController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/auth/loginPage")
    public String loginPage() {
        return "loginPage";
    }

    @GetMapping("/auth/registration")
    public String registrationPage(@ModelAttribute("client") @Valid Client client) {
        return "registrationPage";
    }

    @Transient
    @PostMapping("/auth/registration")
    public String performRegistration(@ModelAttribute("client")@Valid Client client, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registrationPage";
        }
        registrationService.register(client);
        return "loginPage";
    }
}
