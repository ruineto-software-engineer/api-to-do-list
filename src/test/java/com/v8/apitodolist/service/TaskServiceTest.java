package com.v8.apitodolist.service;

import com.v8.apitodolist.domain.TaskEnum;
import com.v8.apitodolist.model.Task;
import com.v8.apitodolist.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @InjectMocks
    TaskService taskService;

    @Mock
    TaskRepository taskRepository;

    Task task;

    @BeforeEach
    public void setUp(){
        task = new Task();

        task.setId(1);
        task.setTitle("Atividade 1");
        task.setDescription("Descrição da Atividade 1");
        task.setStatus(TaskEnum.valueOf("PENDENTE"));
    }

    @Test
    void shoudBeSuccessfulSearchAnTask(){
        when(taskRepository.findById(task.getId())).thenReturn(Optional.ofNullable(task));

        Task resultTask = taskService.findTaskById(String.valueOf(task.getId()));

        assertEquals(Optional.ofNullable(task).get(), resultTask);
        verify(taskRepository).findById(task.getId());
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    void shoudBeSuccessfulSearchAnListTask(){
        when(taskRepository.findAll()).thenReturn(Collections.singleton(task).stream().toList());

        List<Task> resultTasks = taskService.findAllTasks();

        assertEquals(Collections.singleton(task).stream().toList(), resultTasks);
        verify(taskRepository).findAll();
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    void shoudBeSuccessfulCreationAnTask(){
        when(taskRepository.save(task)).thenReturn(task);

        Task resultTask = taskService.saveTask(task);

        assertEquals(task, resultTask);
        verify(taskRepository).save(task);
    }

    @Test
    void shoudBeSuccessfulDeletionAnTask(){
        when(taskRepository.findById(task.getId())).thenReturn(Optional.ofNullable(task));
        doNothing().when(taskRepository).deleteById(task.getId());

        String returnExpected = "Task has been deleted";
        String result = taskService.deleteTaskById(String.valueOf(task.getId()));

        assertEquals(returnExpected, result);
        verify(taskRepository).deleteById(task.getId());
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    void shoudBeSuccessfulUpdateAnTask(){
        Task updatedTask = new Task();

        updatedTask.setId(1);
        updatedTask.setTitle(task.getTitle());
        updatedTask.setDescription(task.getDescription());
        updatedTask.setStatus(TaskEnum.valueOf("CONCLUIDA"));

        when(taskRepository.findById(task.getId())).thenReturn(Optional.ofNullable(task));

        String returnExpected = "Task has been updated";
        String result = taskService.updateTaskById(String.valueOf(task.getId()), updatedTask);

        assertEquals(returnExpected, result);
        verify(taskRepository).save(updatedTask);
    }

}
