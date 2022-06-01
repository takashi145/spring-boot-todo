package com.example.Todo.web;

import com.example.Todo.domain.user.User;
import com.example.Todo.domain.user.UserService;
import com.example.Todo.web.forms.TaskForm;
import com.example.Todo.web.forms.UserForm;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.LoggerRegistry;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(@AuthenticationPrincipal UserDetails user, Model model){
        if(user != null) {
            return "redirect:/task";
        }

        model.addAttribute("show", false);
        return "auth/login";
    }

    @GetMapping("/register")
    public String register(@AuthenticationPrincipal UserDetails user, @ModelAttribute UserForm form, Model model) {
        if(user != null) {
            return "redirect:/task";
        }
        model.addAttribute("show", false);
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Validated UserForm form, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "auth/register";
        }
        userService.create(form.getUsername(), form.getPassword());
        return "redirect:/task";
    }
}
