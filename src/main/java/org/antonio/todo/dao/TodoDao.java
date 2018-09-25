package org.antonio.todo.dao;

import org.antonio.todo.model.Todo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TodoDao {

    Todo create(Todo todo);

    Todo update(Todo todo);

    void delete(long id);

    Todo getById(long id);

    Page<Todo> getList(int pageNum, int itemsPerPage);

    List<Todo> getRelatedTodo(long id);
}
