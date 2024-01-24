package com.webapp.Todo.Business;

import com.webapp.Todo.Service.Todo;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class TodoService {


    List todo= new ArrayList();
    public TodoService(){}



    public void addtodo(int id,String username,String description,LocalDate localDate,Boolean value){

        todo.add(new Todo(id,username,description,localDate,value));

    }

    public List findtodo(){
        return todo;
    }



    public void deletetodo(int id, ModelMap map){

        todo.remove(id);

    }

    public void updatetodo(int id,String description,LocalDate targetDate){

        Todo updated=(Todo) todo.get(id);
        updated.setDescription(description);
        updated.setTargetDate(targetDate);
        todo.set(id,updated);

    }

    public TodoService findbyid(int id) {
        return (TodoService) todo.get(id);
    }
}
