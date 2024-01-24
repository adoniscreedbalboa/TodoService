package com.webapp.Todo.Controller;
import com.webapp.Todo.Business.TodoService;
import com.webapp.Todo.Service.Todo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

//@Controller
@SessionAttributes({"name"})

public class TodoController {

    @Autowired
    TodoService todoService;

    int id=0;


    @GetMapping("/list-todos")
    public String gettodos(ModelMap map){
        List todos=todoService.findtodo();
        map.put("todo",todos);

        return "todos";
    }

    @GetMapping("/add-todo")
    public String getalltodos(ModelMap map){
    //if(map.isEmpty()) {
    //map.put("todo", todoService.addtodo(0, (String) map.get("name"), "Learn", LocalDate.of(2024, 01, 21), false));
    //}
        Todo todo= new Todo(1,(String) map.get("name"),"",LocalDate.now(),false);
        map.put("todo",todo);
        return "addtodo";
    }
    @PostMapping("/add-todo")
    public String addtodos(ModelMap map, @Valid Todo todo, BindingResult result){
       if (result.hasErrors()){
           return"addtodo";
       }
        todoService.addtodo(id++,(String) map.get("name"),todo.getDescription(), todo.getTargetDate(),false);
        return "redirect:list-todos";
    }

    @GetMapping("/delete-todo")
    public String deletetodos(@RequestParam int id,ModelMap map){

        todoService.deletetodo(id,map);
        return "redirect:list-todos";
    }

    @GetMapping("/update-todo")
    public String updatetodos(ModelMap map,@RequestParam int id){
       TodoService todo=todoService.findbyid(id);
        map.put("todo",todo);

        return "addtodo";
    }

    @PostMapping("/update-todo")
    public String updating(Todo todo){
        todoService.updatetodo(todo.getId(), todo.getDescription(),todo.getTargetDate());
        return "redirect:list-todos";
    }
    private String getloggedusename(ModelMap map)
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

}
