package com.v8.apitodolist.controller;

import com.v8.apitodolist.model.Task;
import com.v8.apitodolist.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Task>> findAllTasks() {
        return ResponseEntity.ok().body(taskService.findAllTasks());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Task> createTask(@RequestBody @Valid Task task) {
        return ResponseEntity.ok().body(taskService.saveTask(task));
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> findTaskById(@PathVariable @Valid String id) {
        return ResponseEntity.ok().body(taskService.findTaskById(id));
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateTaskById(
            @PathVariable @Valid String id, @RequestBody @Valid Task task
    ) {
        return taskService.updateTaskById(id, task);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteById(@PathVariable @Valid String id) {
        return taskService.deleteTaskById(id);
    }

}
