package com.example.Todo.domain.task;

import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface TaskRepository {

    @Select("select * from tasks where user_id = #{user_id} and deadline >= now()")
    List<Task> findAll(long usr_id);

    @Insert("insert into tasks(title, user_id, description, deadline) value(#{title}, #{user_id}, #{description}, #{deadline})")
    void insert(String title, long user_id, String description, Date deadline);

    @Select("select * from tasks where id = #{id}")
    Task findById(long id);

    @Update("update tasks set title = #{title}, description = #{description}, deadline = #{deadline} where id = #{id}")
    void update(long id, String title, String description, Date deadline);

    @Delete("delete from tasks where id = #{id}")
    void delete(long id);
}
