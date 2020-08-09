package com.example.consumer.service;

import com.example.consumer.model.Task;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class TaskServiceImpl implements  TaskService{

    @Value("${resource.tasks}")
    private String resource;

    @Value("${resource.tasks}/{id}")
    private String idResource;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    TaskService taskService;

    @Override
    public Task findById(Long id) {
        return restTemplate.getForObject(idResource, Task.class, id);
    }

    @Override
    public List<Task> findAll() {
       return  Arrays.stream(Objects.requireNonNull(restTemplate.getForObject(resource, Task[].class))).collect(Collectors.toList());

    }

    @Override
    public void delete(Long id) {
        restTemplate.delete(idResource, id);
    }

    @Override
    public Task update(Long id, Task task) {
        return restTemplate
                .exchange(idResource, HttpMethod.PUT, new HttpEntity<>(task), Task.class, id)
                .getBody();
    }

    @Override
    public Task create(Task task) {
        return restTemplate.postForObject(resource, task, Task.class);
    }
}
