package com.test.toDoList.services.implement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.test.toDoList.entities.ToDo;
import com.test.toDoList.repositories.ToDoRepository;

@ExtendWith(MockitoExtension.class) // simuler l objet repo pour tester la couche service
class ToDoServiceImplUnitTests {

    // ToDo repo
    @Mock
    ToDoRepository mockRepo;

    // la classe ou j inject le mockRepo
    @InjectMocks
    ToDoServiceImpl toDoService;

    @Test
    void findAllTodos_test() {
        // arrange
        List<ToDo> todos = new ArrayList<>();
        todos.add(new ToDo("rdv doc", "rdv à 17h", false));
        todos.add(new ToDo("pain", "boulangerie du coin !", false));

        // simuler le comportement du mockRepo
        when(mockRepo.findAll()).thenReturn(todos);

        // act
        List<ToDo> lst = toDoService.findAllTodos();

        // assert
        assertEquals(2, lst.size());
        assertEquals(todos.get(0).getDescription(), lst.get(0).getDescription());
    }

    @Test
    void getToDo_test() {
        ToDo todo = new ToDo(1, "rdv doc", "rdv à 17h", false);
        when(mockRepo.findById((long) 1)).thenReturn(Optional.of(todo));

        ToDo t = toDoService.getToDo(1);

        assertEquals(t.getDescription(), "rdv à 17h");
    }

    @Test
    void SaveToDo_test() {
        ToDo todo = new ToDo(1, "rdv doc", "rdv à 17h", false);
        when(mockRepo.save(todo)).thenReturn(todo);

        ToDo t = toDoService.SaveToDo(todo);

        assertEquals(t.getDescription(), todo.getDescription());
        assertEquals(t.getTitle(), "rdv doc");
    }

    @Test
    void deleteToDo_test() {
        ToDo todo = new ToDo(1, "rdv doc", "rdv à 17h", false);
        toDoService.deleteToDo(todo.getId());
        verify(mockRepo, times(1)).deleteById((long) 1);
    }
    
    @Test
    void changeStateTodo_test() {
        ToDo todo = new ToDo(1, "rdv doc", "rdv à 17h", false);
        when(mockRepo.save(todo)).thenReturn(todo);

        ToDo t = toDoService.SaveToDo(todo);

        assertEquals(t.getDescription(), todo.getDescription());
        assertEquals(t.getTitle(), "rdv doc");
    }

}
