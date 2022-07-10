package com.test.toDoList.services.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.toDoList.entities.ToDo;
import com.test.toDoList.repositories.ToDoRepository;
import com.test.toDoList.services.ToDoService;

@Service
public class ToDoServiceImpl implements ToDoService {

    @Autowired
    private ToDoRepository repository;

    public void setRepository(ToDoRepository repository) {
        this.repository = repository;
    }

    // implemented methods
    @Override
    public List<ToDo> findAllTodos() {

        return repository.findAll();
    }

    @Override
    public ToDo changeStateTodo(long id) {

        ToDo todo = repository.findById(id).get();
        todo.setDone(true);
        return repository.save(todo);

    }

    @Override
    public ToDo getToDo(long id) {

        return repository.findById(id).get();
    }

    @Override
    public ToDo SaveToDo(ToDo todo) {
       return  repository.save(todo);

    }

    @Override
    public void deleteToDo(long id) {
        repository.deleteById(id);

    }

}
