package com.test.toDoList.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.test.toDoList.entities.ToDo;
import com.test.toDoList.services.implement.ToDoServiceImpl;

/*
 * Pour tester des controller mvc (@Controller - @RestController) on a besoin de simuler des requêtes HTTP.
 * Une classe est fournie par Spring Boot Test (MockMvc) permet de simuler l'exécution de requêtes HTTP.
 * Pour utiliser cette classe, ajouter ces 2 annotation (@SpringBootTest - @AutoConfigureMockMvc) sur la classe de test
 * 
 */

@SpringBootTest
@AutoConfigureMockMvc
class ToDoControllerUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    TodoController todoController;

    @MockBean // couche service a injecter dans le controlleur
    ToDoServiceImpl toDoService;

    // liste toDo
    private List<ToDo> todos = new ArrayList<>();

    @BeforeEach
    public void setup() {
        todos.add(new ToDo(1, "rdv doc", "rdv à 17h", false));
        todos.add(new ToDo(2, "pain", "boulangerie du coin !", false));
    }

    // Vérifier que le controller est bien chargé
    @Test
    void contextInit() {
        assertNotNull(todoController);
    }

    @Test
    void listeTodo_test() throws Exception {
        when(toDoService.findAllTodos()).thenReturn(todos);

        mockMvc.perform(get("/todos")).andExpect(status().isOk()).andExpect(view().name("todos"))
                .andExpect(model().attributeExists("todos"))
                .andExpect(content().contentType("text/html;charset=UTF-8"));
        verify(toDoService, times(1)).findAllTodos();
        verifyNoMoreInteractions(toDoService);
    }

    @Test
    void todoDetails_test() throws Exception {
        ToDo todo = new ToDo(1, "pain", "ne l oublie pas !", false);
        when(toDoService.getToDo(1)).thenReturn(todo);

        mockMvc.perform(get("/todos/{id}", 1L)).andExpect(status().isOk()).andExpect(view().name("todo"))
                .andExpect(model().attributeExists("todo"));

        verify(toDoService, times(1)).getToDo(1L);
        verifyNoMoreInteractions(toDoService);

    }

    @Test
    void addTodo_test() throws Exception {
        ToDo todo = new ToDo(1, "pain", "ne l oublie pas !", false);
        when(toDoService.SaveToDo(todo)).thenReturn(todo);

        mockMvc.perform(post("/todos/add").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("description", todo.getDescription()).param("title", todo.getTitle())
                .sessionAttr("todo", new ToDo())).andExpect(view().name("redirect:/todos"));

        verify(toDoService, times(1)).SaveToDo(todo);
        verifyNoMoreInteractions(toDoService);

    }

}
