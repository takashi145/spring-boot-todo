package com.example.Todo.domain.task;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;


@Data
@AllArgsConstructor
public class Task {
    private long id;
    private String title;
    private String description;
    private Date deadline;
}
