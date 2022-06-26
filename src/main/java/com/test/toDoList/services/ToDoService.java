package com.test.toDoList.services;

import java.util.List;

import com.test.toDoList.entities.ToDo;

public interface ToDoService {

    // lister tous les todos
    public List<ToDo> findAllToDos();

    // changer l etat d'un ToDo
    public void ChangeStateTodo(Long id);

    // single todo details
    public ToDo getToDo(Long id);

    // add a new todo
    public void SaveToDo(ToDo toDo);

    // delete todo
    public void deleteToDo(Long id);

}
