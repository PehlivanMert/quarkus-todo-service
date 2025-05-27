package com.example.todo.resource;

import com.example.todo.dto.TodoDTO;
import com.example.todo.service.TodoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/api/todos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Tag(name = "Todo Resource", description = "Todo REST endpoints")
public class TodoResource {

    private static final Logger LOG = Logger.getLogger(TodoResource.class);

    @Inject
    TodoService todoService;

    @GET
    @Operation(summary = "Get all todos", description = "Returns a list of all todos")
    @APIResponse(responseCode = "200", description = "List of todos retrieved successfully")
    public List<TodoDTO> getAllTodos() {
        LOG.info("Getting all todos");
        return todoService.getAllTodos();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get todo by ID", description = "Returns a todo by its ID")
    @APIResponse(responseCode = "200", description = "Todo found")
    @APIResponse(responseCode = "404", description = "Todo not found")
    public Response getTodo(@PathParam("id") Long id) {
        LOG.info("Getting todo with id: " + id);
        return todoService.getTodoById(id)
                .map(todo -> Response.ok(todo).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Transactional
    @Operation(summary = "Create new todo", description = "Creates a new todo")
    @APIResponse(responseCode = "201", description = "Todo created successfully")
    public Response createTodo(TodoDTO todoDTO) {
        LOG.info("Creating new todo: " + todoDTO);
        TodoDTO created = todoService.createTodo(todoDTO);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @Operation(summary = "Update todo", description = "Updates an existing todo")
    @APIResponse(responseCode = "200", description = "Todo updated successfully")
    @APIResponse(responseCode = "404", description = "Todo not found")
    public Response updateTodo(@PathParam("id") Long id, TodoDTO todoDTO) {
        LOG.info("Updating todo with id: " + id + ", new data: " + todoDTO);
        return todoService.updateTodo(id, todoDTO)
                .map(todo -> Response.ok(todo).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @PATCH
    @Path("/{id}/complete")
    @Transactional
    @Operation(summary = "Mark todo as completed", description = "Marks a todo as completed")
    @APIResponse(responseCode = "200", description = "Todo marked as completed")
    @APIResponse(responseCode = "404", description = "Todo not found")
    public Response completeTodo(@PathParam("id") Long id, CompleteRequest req) {
        LOG.info("Marking todo as completed with id: " + id + ", completed: " + req.completed);
        return todoService.getTodoById(id)
                .map(todo -> {
                    todo.setCompleted(req.completed);
                    return todoService.updateTodo(id, todo)
                            .map(updated -> Response.ok(updated).build())
                            .orElse(Response.status(Response.Status.NOT_FOUND).build());
                })
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Operation(summary = "Delete todo", description = "Deletes a todo")
    @APIResponse(responseCode = "200", description = "Todo deleted successfully")
    @APIResponse(responseCode = "404", description = "Todo not found")
    public Response deleteTodo(@PathParam("id") Long id) {
        LOG.info("Deleting todo with id: " + id);
        return todoService.deleteTodo(id)
                .map(todo -> Response.ok(todo).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    public static class CompleteRequest {
        public boolean completed;
    }
}