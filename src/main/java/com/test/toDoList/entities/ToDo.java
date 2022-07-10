package com.test.toDoList.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "todos")
public class ToDo implements Serializable {

    private static final long serialVersionUID = 1L;

    // attributs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Version
    private int version;

    @Column(nullable = false)
    @Length(max = 100)
    private String title;

    @Column(nullable = true)
    private String description;

    private boolean isDone = false;

    // constructors
    public ToDo() {

    }

    public ToDo(String title, String description, boolean isDone) {
        this.title = title;
        this.description = description;
        this.isDone = isDone;
    }

    // getters & setters
    public long getId() {
        return id;
    }
    
    

    public ToDo(long id, String title, String description, boolean isDone) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.isDone = isDone;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ToDo [id=" + id + ", title=" + title + ", description=" + description + ", isDone=" + isDone + "]";
    }

}
