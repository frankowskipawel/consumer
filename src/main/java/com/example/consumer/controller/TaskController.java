package com.example.consumer.controller;

import com.example.consumer.model.Task;
import com.example.consumer.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping(value = "{id}")
    public String findById(@PathVariable Long id) {
        System.out.println(taskService.findById(id).getDescription());
        return "redirect:/";
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        model.addAttribute("newTask", new Task());
        return "tasks";
    }

    @PostMapping(value = "delete")
    public String delete(@RequestParam Long id) {
        taskService.delete(id);

        return "redirect:/";
    }

    @PostMapping(value = "put")
    public String update (Task task){
        Task tempTask = taskService.findById(task.getId());
        task.setUser(tempTask.getUser());
        taskService.update(task.getId(), task);
        return "redirect:/";
    }

    @PostMapping
    public String create(@ModelAttribute("newTask") Task task) {
        taskService.create(task);
        return "redirect:/";
    }




}
