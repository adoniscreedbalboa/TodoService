package com.webapp.Todo.Repository;

import com.webapp.Todo.Service.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Todorepository extends JpaRepository<Todo,Integer> {

    public List<Todo> findByUsername(String username);


}
