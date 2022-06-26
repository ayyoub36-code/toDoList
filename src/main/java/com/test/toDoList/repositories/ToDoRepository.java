package com.test.toDoList.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.toDoList.entities.ToDo;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {


}
