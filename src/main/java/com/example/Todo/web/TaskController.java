package com.example.Todo.web;

import com.example.Todo.domain.Task;
import com.example.Todo.domain.TaskService;
import com.example.Todo.web.forms.TaskForm;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        Task task = taskService.findById(id);
        model.addAttribute("task", task);
        return "task/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") long id, @ModelAttribute TaskForm form, Model model) {
        Task task = taskService.findById(id);
        model.addAttribute("task", task);
        return "task/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable("id") long id, @Validated TaskForm form, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return edit(id, form, model);
        }
        taskService.update(id, form.getTitle(), form.getDescription(), form.getDeadline());
        return "redirect:/task/" + id;
    }
}
