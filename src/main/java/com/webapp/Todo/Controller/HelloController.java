package com.webapp.Todo.Controller;


import com.webapp.Todo.Security.Authenitication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;

@Controller
@SessionAttributes("name")
public class HelloController {
    private Logger logger= LoggerFactory.getLogger(getClass());

    @GetMapping("/")
    public String login(ModelMap map)
    {
        map.put("name",getloggedusename());
        return "Hello";
    }

    private String getloggedusename()
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }



}
