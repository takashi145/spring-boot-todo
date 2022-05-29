package com.example.Todo.domain.task;

import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface TaskRepository {

    @Select("select * from tasks")
    List<Task> findAll();

    @Insert("insert into tasks(title, description, deadline) value(#{title}, #{description}, #{deadline})")
    void insert(String title, String description, Date deadline);

    @Select("select * from tasks where id = #{id}")
    Task findById(long id);

    @Update("update tasks set title = #{title}, description = #{description}, deadline = #{deadline} where id = #{id}")
    void update(long id, String title, String description, Date deadline);

    @Delete("delete from tasks where id = #{id}")
    void delete(long id);
}
