package com.example.Todo.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
public class CustomErrorController implements ErrorController {

    @RequestMapping
    public ModelAndView error(HttpServletRequest req, ModelAndView mav) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        mav.setStatus(status);
        mav.setViewName("error");
        mav.addObject("status", status.value());
        mav.addObject("error", "Not Found");
        return mav;
    }
}
