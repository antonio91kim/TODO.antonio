package org.antonio.todo.service;

import lombok.extern.slf4j.Slf4j;
import org.antonio.todo.common.ErrorCode;
import org.antonio.todo.dao.TodoDao;
import org.antonio.todo.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoDao todoDao;

    @Override
    public Todo create(Todo todo) {
        return todoDao.create(todo);
    }

    @Override
    public Todo update(long id, Todo todo) {
        Todo updateTodo = todoDao.getById(id);
        updateTodo.setTitle(todo.getTitle());
        return todoDao.update(updateTodo);
    }

    @Override
    public void delete(long id) {
        todoDao.delete(id);
    }

    @Override
    public Todo getById(long id) {
        return todoDao.getById(id);
    }

    @Override
    public Page<Todo> getList(int pageNum, int itemsPerPage) {
        return todoDao.getList(pageNum, itemsPerPage);
    }

    @Override
    public List<Todo> getRelatedTodo(long id) {
        return todoDao.getRelatedTodo(id);
    }

}
