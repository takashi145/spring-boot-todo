package com.example.Todo.domain.task;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> findAll(long id) {
        return taskRepository.findAll(id);
    }

    @Transactional
    public void create(String title, long user_id, String description, Date deadline) {
        taskRepository.insert(title, user_id, description, deadline);
    }

    public Task findById(long id) {
        return taskRepository.findById(id);
    }

    public void update(long id, String title, String description, Date deadline) {
        taskRepository.update(id, title, description, deadline);
    }

    public void delete(long id) {
        taskRepository.delete(id);
    }

}
