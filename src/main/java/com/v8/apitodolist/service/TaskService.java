package com.v8.apitodolist.service;

import com.v8.apitodolist.model.Task;
import com.v8.apitodolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task saveTask(Task task) {
        if(taskRepository.findById(task.getId()).isPresent()) return null;

        return taskRepository.save(task);
    }

    public List<Task> findAllTasks() {
        return (List<Task>) taskRepository.findAll();
    }

    public Task findTaskById(String id) {
        if(taskRepository.findById(Integer.valueOf(id)).isEmpty()) return null;

        return taskRepository.findById(Integer.valueOf(id)).get();
    }

    public String deleteTaskById(String id){
        if(taskRepository.findById(Integer.valueOf(id)).isEmpty()) return "Task is not present to be deleted";
        taskRepository.deleteById(Integer.valueOf(id));

        return "Task has been deleted";
    }

    public String updateTaskById(String id, Task task){
        if(taskRepository.findById(Integer.valueOf(id)).isEmpty()) return "Task is not present to be updated";

        Task currentTask = taskRepository.findById(task.getId()).get();

        currentTask.setTitle(task.getTitle());
        currentTask.setDescription(task.getDescription());
        currentTask.setStatus(task.getStatus());

        taskRepository.save(currentTask);

        return "Task has been updated";
    }

}
