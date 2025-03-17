package com.rungroup.SpringMVCPt1.controller;

import com.rungroup.SpringMVCPt1.dto.RegistrationDto;
import com.rungroup.SpringMVCPt1.models.UserEntity;
import com.rungroup.SpringMVCPt1.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    //GET mapping to display the registration page
    @GetMapping("/register")
    public String registrationForm(Model model) {
        RegistrationDto registrationDto = new RegistrationDto();
        model.addAttribute("user", registrationDto);
        return "register";
    }

    //POST endpoint to register a user
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") RegistrationDto user, BindingResult result, Model model) {
        //Check if there is already an existing user
        UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
        if (existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()) {
            result.rejectValue("email", "user.exists");
        }

        UserEntity existingUsername = userService.findByUsername(user.getUsername());
        if (existingUsername != null && existingUsername.getUsername() != null && !existingUsername.getUsername().isEmpty()) {
            result.rejectValue("username", "user.exists");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }

        userService.saveUser(user);

        return "redirect:/clubs?success";
    }
}
