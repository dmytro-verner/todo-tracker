package edu.springboot.web.controller;

import edu.springboot.web.model.Todo;
import edu.springboot.web.security.SecurityHelper;
import edu.springboot.web.service.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class TodoController {

    @Autowired
    TodoRepository repository;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(value = "/list-todos", method = RequestMethod.GET)
    public String showTodosList(ModelMap model){
        String username = SecurityHelper.getLoggedInUsername();

        model.put("todos", repository.findByUser(username));
        return "list-todos";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String showAddTodo(ModelMap model){
        model.addAttribute("todo", new Todo(0, SecurityHelper.getLoggedInUsername(),
                "", new Date(), false));
        return "todo";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result){
        if(result.hasErrors())
            return "todo";

        todo.setUser(SecurityHelper.getLoggedInUsername());
        repository.save(todo);
        return "redirect:/list-todos";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
    public String showUpdateTodo(@RequestParam int id, ModelMap model){
        model.put("todo", repository.findOne(id));
        return "todo";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result){
        if(result.hasErrors())
            return "todo";

        todo.setUser(SecurityHelper.getLoggedInUsername());
        repository.save(todo);
        return "redirect:/list-todos";
    }


    @RequestMapping(value = "/delete-todo", method = RequestMethod.POST)
    public String deleteTodo(@RequestParam int id){
        repository.delete(id);
        return "redirect:/list-todos";
    }
}