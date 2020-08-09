package com.example.consumer.service;

import com.example.consumer.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TaskService {

    Task findById(Long id);
    List<Task> findAll();
    void delete(Long id);
    Task update(Long id, Task task);
    Task create(Task task);
}
