package com.webapp.Todo.Service;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Todo {

    @Id
    @GeneratedValue
    private int id;
    private String username;
    @Size(min=5,message = "Enter atleast 10 characters")
    private String description;

    private LocalDate targetDate;

    private boolean done;
    public Todo(){}


    public Todo(int id,String username,String description,LocalDate targetDate,Boolean done){
        this.id=id;
        this.username=username;
        this.description=description;
        this.targetDate=targetDate;
        this.done=done;

    }
    public int getId() {
        return id;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public String getDescription() {
        return description;
    }

    public String getUsername() {
        return username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "TodoService{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", description='" + description + '\'' +
                ", targetDate=" + targetDate +
                '}';
    }
}
