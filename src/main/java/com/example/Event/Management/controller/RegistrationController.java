package com.example.Event.Management.controller;

import com.example.Event.Management.model.Registration;
import com.example.Event.Management.repository.RegistrationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    private final RegistrationRepository registrationRepository;

    public RegistrationController(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @PostMapping
    public Registration registerStudent(@RequestBody Registration registration) {
        return registrationRepository.save(registration);
    }

    @GetMapping
    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }
}