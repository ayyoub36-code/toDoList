package com.test.toDoList.services;

import java.util.List;

import com.test.toDoList.entities.ToDo;

public interface ToDoService {

    // lister tous les todos
    public List<ToDo> findAllTodos();

    // changer l etat d'un ToDo
    public ToDo changeStateTodo(long id);

    // single todo details
    public ToDo getToDo(long id);

    // add a new todo
    public ToDo SaveToDo(ToDo toDo);

    // delete todo
    public void deleteToDo(long id);

}
