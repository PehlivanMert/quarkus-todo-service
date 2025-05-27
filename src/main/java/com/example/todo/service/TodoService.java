package com.example.todo.service;

import com.example.todo.dto.TodoDTO;
import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class TodoService {

    @Inject
    TodoRepository todoRepository;

    @Transactional
    public TodoDTO createTodo(TodoDTO todoDTO) {
        Todo todo = new Todo(todoDTO.getTitle());
        todo.setDescription(todoDTO.getDescription());
        todo.setCompleted(todoDTO.isCompleted());

        todoRepository.persist(todo);
        return convertToDTO(todo);
    }

    public List<TodoDTO> getAllTodos() {
        return todoRepository.listAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<TodoDTO> getTodoById(Long id) {
        return Optional.ofNullable(todoRepository.findById(id))
                .map(this::convertToDTO);
    }

    @Transactional
    public Optional<TodoDTO> updateTodo(Long id, TodoDTO todoDTO) {
        return Optional.ofNullable(todoRepository.findById(id))
                .map(todo -> {
                    todo.setTitle(todoDTO.getTitle());
                    todo.setDescription(todoDTO.getDescription());
                    todo.setCompleted(todoDTO.isCompleted());
                    return convertToDTO(todo);
                });
    }

    @Transactional
    public Optional<TodoDTO> deleteTodo(Long id) {
        return Optional.ofNullable(todoRepository.findById(id))
                .map(todo -> {
                    todoRepository.delete(todo);
                    return convertToDTO(todo);
                });
    }

    private TodoDTO convertToDTO(Todo todo) {
        TodoDTO dto = new TodoDTO();
        dto.setId(todo.id);
        dto.setTitle(todo.getTitle());
        dto.setDescription(todo.getDescription());
        dto.setCompleted(todo.isCompleted());
        return dto;
    }
}