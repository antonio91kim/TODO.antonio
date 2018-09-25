package org.antonio.todo.service;

import org.antonio.todo.model.Todo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TodoService {
    Todo create(Todo todo);
    Todo update(long id, Todo todo);
    void delete(long id);
    Todo getById(long id);
    Page<Todo> getList(int pageNum, int itemsPerPage);
    List<Todo> getRelatedTodo(long id);
}
