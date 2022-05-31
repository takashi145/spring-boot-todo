package com.example.Todo.web;

import com.example.Todo.domain.auth.CustomUserDetails;
import com.example.Todo.domain.task.Task;
import com.example.Todo.domain.task.TaskService;
import com.example.Todo.domain.user.User;
import com.example.Todo.web.forms.TaskForm;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public String index(@AuthenticationPrincipal CustomUserDetails user, Model model) {
        List<Task> taskList = taskService.findAll(user.getId());
        model.addAttribute("taskList", taskList);
        return "task/index";
    }

    @GetMapping("/create")
    public String create(@ModelAttribute TaskForm form) {
        return "task/create";
    }

    @PostMapping("/create")
    public String store(@AuthenticationPrincipal CustomUserDetails user, @Validated TaskForm form, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return create(form);
        }
        taskService.create(form.getTitle(), user.getId(), form.getDescription(), form.getDeadline());
        return "redirect:/task";
    }

    @GetMapping("/{id}")
    public String show(@AuthenticationPrincipal CustomUserDetails user, @PathVariable("id") long id, Model model) {
        Task task = taskService.findById(id);
        //作成者かどうか
        if(task.getUser_id() != user.getId()) {
            return "forward:/error";
        }
        model.addAttribute("task", task);
        return "task/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@AuthenticationPrincipal CustomUserDetails user, @PathVariable("id") long id, @ModelAttribute TaskForm form, Model model) {
        Task task = taskService.findById(id);
        if(task.getUser_id() != user.getId()) {
            return "forward:/error";
        }
        model.addAttribute("task", task);
        return "task/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@AuthenticationPrincipal CustomUserDetails user, @PathVariable("id") long id, @Validated TaskForm form, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return edit(user, id, form, model);
        }
        taskService.update(id, form.getTitle(), form.getDescription(), form.getDeadline());
        return "redirect:/task/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") long id, Model model) {
        taskService.delete(id);
        return "redirect:/task";
    }

}
