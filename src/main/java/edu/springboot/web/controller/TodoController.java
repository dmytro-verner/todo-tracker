package edu.springboot.web.controller;

import edu.springboot.web.model.Todo;
import edu.springboot.web.service.TodoService;
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
@SessionAttributes("name")
public class TodoController {

    @Autowired
    TodoService todoService;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(value = "/list-todos", method = RequestMethod.GET)
    public String showTodosList(ModelMap model){
        String name = getLoggedInUsername(model);
        model.put("todos", todoService.retrieveTodos(name));
        return "list-todos";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String showAddTodo(ModelMap model){
        model.addAttribute("todo", new Todo(0, getLoggedInUsername(model),
                "", new Date(), false));
        return "todo";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result){
        if(result.hasErrors())
            return "todo";

        todoService.addTodo(getLoggedInUsername(model), todo.getDescription(), todo.getTargetDate(), false);
        return "redirect:/list-todos";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
    public String showUpdateTodo(@RequestParam int id, ModelMap model){
        model.put("todo", todoService.retrieveTodo(id));
        return "todo";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result){
        if(result.hasErrors())
            return "todo";

        todo.setUser(getLoggedInUsername(model));
        todoService.updateTodo(todo);
        return "redirect:/list-todos";
    }


    @RequestMapping(value = "/delete-todo", method = RequestMethod.POST)
    public String deleteTodo(@RequestParam int id){
        todoService.deleteTodo(id);
        return "redirect:/list-todos";
    }

    private String getLoggedInUsername(ModelMap model) {
        return (String) model.get("name");
    }
}
