package com.example.Todo.domain.user;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface UserRepository {

    @Select("select * from users where username = #{username}")
    Optional<User> findByUsername(String username);

    @Insert("insert into users(username, password) values(#{username}, #{password})")
    void insert(String username, String password);
}
