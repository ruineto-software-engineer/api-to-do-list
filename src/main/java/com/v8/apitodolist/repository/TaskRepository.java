package com.v8.apitodolist.repository;

import com.v8.apitodolist.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Integer> {
}
