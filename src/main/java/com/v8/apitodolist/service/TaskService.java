package com.v8.apitodolist.service;

import com.v8.apitodolist.model.Task;
import com.v8.apitodolist.repository.TaskRepository;
import com.v8.apitodolist.service.exception.EntityBadRequestException;
import com.v8.apitodolist.service.exception.EntityConflictException;
import com.v8.apitodolist.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task saveTask(Task task) {
        if(taskRepository.findById(task.getId()).isPresent()){
            throw new EntityConflictException(
                    "Task id: " + task.getId() + " is founded"
            );
        };

        return taskRepository.save(task);
    }

    public List<Task> findAllTasks() {
        return (List<Task>) taskRepository.findAll();
    }

    public Task findTaskById(String id) {
        return taskRepository.findById(Integer.valueOf(id)).orElseThrow(
                () -> new EntityNotFoundException("Task id: " + id + " not found to be delivered")
        );
    }

    public String deleteTaskById(String id){
        taskRepository.findById(Integer.valueOf(id)).orElseThrow(
                () -> new EntityNotFoundException("Task id: " + id + " not found to be deleted")
        );

        taskRepository.deleteById(Integer.valueOf(id));

        return "Task has been deleted";
    }

    public String updateTaskById(String id, Task task){
        if(!Integer.valueOf(id).equals(task.getId())){
            throw new EntityBadRequestException(
                    "Resource with PathVariable id different from payload id"
            );
        }

        Task currentTask = taskRepository.findById(Integer.valueOf(id)).orElseThrow(
                () -> new EntityNotFoundException("Task id: " + id + " not found to be updated")
        );

        currentTask.setTitle(task.getTitle());
        currentTask.setDescription(task.getDescription());
        currentTask.setStatus(task.getStatus());

        taskRepository.save(currentTask);

        return "Task has been updated";
    }

}
