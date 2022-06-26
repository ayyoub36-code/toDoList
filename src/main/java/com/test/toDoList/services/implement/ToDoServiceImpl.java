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
    public List<ToDo> findAllToDos() {

        return repository.findAll();
    }

    @Override
    public void ChangeStateTodo(Long id) {

        ToDo todo = repository.findById(id).get();
        todo.setDone(true);
        repository.saveAndFlush(todo);

    }

    @Override
    public ToDo getToDo(Long id) {

        return repository.findById(id).get();
    }

    @Override
    public void SaveToDo(ToDo toDo) {
        repository.saveAndFlush(new ToDo());

    }

    @Override
    public void deleteToDo(Long id) {
        repository.deleteById(id);

    }

}
