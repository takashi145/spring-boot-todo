package com.example.Todo.web;

import com.example.Todo.domain.Task;
import com.example.Todo.domain.TaskService;
import com.example.Todo.web.forms.TaskForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
        List<Task> taskList = taskService.findAll();
        model.addAttribute("taskList", taskList);
        return "task/index";
    }

    @GetMapping("/create")
    public String create(@ModelAttribute TaskForm form) {
        return "task/create";
    }

    @PostMapping("/create")
    public String store(@Validated TaskForm form, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return create(form);
        }
        taskService.create(form.getTitle(), form.getDescription(), form.getDeadline());
        return "redirect:/task";
    }
}
