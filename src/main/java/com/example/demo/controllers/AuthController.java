package com.example.demo.controllers;

import com.example.demo.models.Person;
import com.example.demo.service.PersonService;
import com.example.demo.service.RegService;
import com.example.demo.util.PersonValidator;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final PersonValidator personValidator;
    private final RegService regService;
    private final PersonService personService;

    private final PasswordEncoder passwordEncoder;
    @Autowired
    public AuthController(PersonValidator personValidator, RegService regService, PersonService personService, PasswordEncoder passwordEncoder) {
        this.personValidator = personValidator;
        this.regService = regService;
        this.personService = personService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/reg")
    public String regPage(@ModelAttribute("person") Person person){
        return "auth/def/reg";
    }
    @PostMapping("/reg")
    public String performReg(@ModelAttribute("person") @Valid  Person person , BindingResult bindingResult){
            personValidator.validate(person,bindingResult);
            if(bindingResult.hasErrors()){
                return "redirect:/auth/reg";
            }
            person.setConfines(-1);
            person.setUser_role("ROLE_DEF");
            regService.reg(person);
            return "redirect:/auth/login";
    }
    @GetMapping("/login")
    public String log(@ModelAttribute("person") Person person){
        return "auth/login";
    }

}
