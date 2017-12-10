package edu.springboot.web.service;

import edu.springboot.web.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();
    private static int todoCount = 3;

    static{
        todos.add(new Todo(1, "Dima", "Learn Hibernate", new Date(), false));
        todos.add(new Todo(2, "Dima", "Learn JDBC", new Date(), true));
        todos.add(new Todo(3, "Dima", "Learn Spring Boot", new Date(), false));
    }

    public List<Todo> retrieveTodos(String user){
        return todos.stream().filter(t -> t.getUser().equals(user)).collect(Collectors.toList());
    }

    public void addTodo(String user, String description, Date date, boolean isDone){
        todos.add(new Todo(++todoCount, user, description, date, isDone));
    }

    public void deleteTodo(int id){
        todos.stream().filter(t -> t.getId() != id).collect(Collectors.toList());
    }
}