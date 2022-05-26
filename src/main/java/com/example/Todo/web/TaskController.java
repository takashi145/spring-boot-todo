package com.example.Todo.web;

import com.example.Todo.domain.Task;
import com.example.Todo.domain.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public String index(Model model) {
        Date date = new Date();
        var taskList = taskService.findAll();
        model.addAttribute("taskList", taskList);
        return "task/index";
    }
}
