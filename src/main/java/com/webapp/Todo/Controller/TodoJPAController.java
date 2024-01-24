package com.webapp.Todo.Controller;

import com.webapp.Todo.Business.TodoService;
import com.webapp.Todo.Service.Todo;
import com.webapp.Todo.Repository.Todorepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes({"name"})

public class TodoJPAController {


    Todorepository todorepository;

    TodoJPAController(Todorepository todorepository){
        this.todorepository=todorepository;
    }

    int id=0;


    @GetMapping("/list-todos")
    public String gettodos(ModelMap map){
        String username=getloggedusename(map);
        List todos=todorepository.findByUsername(username);
        map.put("todo",todos);

        return "todos";
    }

    @GetMapping("/add-todo")
    public String getalltodos(ModelMap map){
        String username=getloggedusename(map);
        Todo todo= new Todo(0,username,"",LocalDate.now(),false);
        map.put("todo",todo);
        return "addtodo";
    }
    @PostMapping("/add-todo")
    public String addtodos(ModelMap map, @Valid Todo todo, BindingResult result){
       if (result.hasErrors()){
           return"addtodo";
       }
       String user=getloggedusename(map);
       todo.setUsername(user);
       todorepository.save(todo);
        return "redirect:list-todos";
    }

    @GetMapping("/delete-todo")
    public String deletetodos(@RequestParam int id,ModelMap map){

        todorepository.deleteById(id);
        return "redirect:list-todos";
    }

    @GetMapping("/update-todo")
    public String updatetodos(ModelMap map,@RequestParam int id){
       Todo todo=todorepository.findById(id).get();
        map.put("todo",todo);

        return "addtodo";
    }

    @PostMapping("/update-todo")
    public String updating(Todo todo, ModelMap map){
        String username=getloggedusename(map);
        todo.setUsername(username);
        todorepository.save(todo);
        return "redirect:list-todos";
    }
    private String getloggedusename(ModelMap map)
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }


}
