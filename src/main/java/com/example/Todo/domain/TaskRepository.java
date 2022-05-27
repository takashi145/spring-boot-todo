package com.example.Todo.domain;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface TaskRepository {

    @Select("select * from tasks")
    List<Task> findAll();

    @Insert("insert into tasks(title, description, deadline) value(#{title}, #{description}, #{deadline})")
    void insert(String title, String description, Date deadline);
}
