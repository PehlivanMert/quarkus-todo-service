package com.example.todo.repository;

import com.example.todo.model.Todo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TodoRepository implements PanacheRepository<Todo> {
    // Panache provides all basic CRUD operations out of the box
    // We can add custom query methods here if needed
}