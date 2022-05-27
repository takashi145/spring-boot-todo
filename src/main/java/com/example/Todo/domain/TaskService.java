package com.example.Todo.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Transactional
    public void create(String title, String description, Date deadline) {
        taskRepository.insert(title, description, deadline);
    }

    public Task findById(long id) {
        return taskRepository.findById(id);
    }
}
