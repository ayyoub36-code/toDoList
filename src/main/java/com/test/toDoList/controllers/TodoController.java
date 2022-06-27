package com.test.toDoList.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.test.toDoList.entities.ToDo;
import com.test.toDoList.form.FormTodo;
import com.test.toDoList.services.ToDoService;

@Controller
public class TodoController {

    @Autowired
    private ToDoService service;

    public void setService(ToDoService service) {
        this.service = service;
    }

    // lister tous les todos

    @GetMapping("/todos")
    public ModelAndView listeTodo() {

        ModelAndView modelView = new ModelAndView();
        List<ToDo> todos = service.findAllTodos();
        modelView.setViewName("todos");
        modelView.addObject("todos", todos);

        return modelView;
    }

    @GetMapping("/todos/delete/{id}")
    public String deleteTodo(Model model, @PathVariable long id) {
        ToDo toDo = service.getToDo(id);
        if (toDo != null) {
            service.deleteToDo(id);
        }
        return "redirect:/todos";

    }

    // afficher le formulaire d ajout de la todo
    @GetMapping("/add")
    public String showForm(Model model) {
        FormTodo formTodo = new FormTodo();
        model.addAttribute("formTodo", formTodo);
        return "addtodo";
    }

    // poster le formulaire pour ajouter le todo
    @PostMapping("/add")
    public ModelAndView addTodo(@ModelAttribute("formTodo") FormTodo formTodo) {

        ModelAndView modelView = new ModelAndView();

        ToDo todo = new ToDo(formTodo.getTitle(), formTodo.getDescription(), false);
        service.SaveToDo(todo);
        modelView.setViewName("redirect:/todos");

        return modelView;
    }

    @PostMapping("/todos/state/{id}")
    public String changeStateTodo(@PathVariable long id) {
        service.ChangeStateTodo(id);
        return "redirect:/todos";
    }

    // todo details
    @GetMapping("/todos/{id}")
    public ModelAndView todoDetails(@PathVariable long id) {

        ModelAndView modelView = new ModelAndView();
        ToDo todo = service.getToDo(id);
        modelView.setViewName("todo");
        modelView.addObject("todo", todo);

        return modelView;
    }
}
