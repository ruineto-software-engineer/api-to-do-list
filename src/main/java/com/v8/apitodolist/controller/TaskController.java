package com.v8.apitodolist.controller;

import com.v8.apitodolist.model.Task;
import com.v8.apitodolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping(value = "/healf")
    @ResponseStatus(HttpStatus.OK)
    public String taskHealf() {
        return "I'm live with tasks!";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Task> findAllTasks() {
        return taskService.findAllTasks();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task findTaskById(@PathVariable String id) {
        return taskService.findTaskById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateTaskById(@PathVariable String id, @RequestBody Task task) {
        return taskService.updateTaskById(id, task);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteById(@PathVariable String id) {
        return taskService.deleteTaskById(id);
    }

}
