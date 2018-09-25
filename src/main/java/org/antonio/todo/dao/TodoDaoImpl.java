package org.antonio.todo.dao;

import lombok.extern.slf4j.Slf4j;
import org.antonio.todo.common.ErrorCode;
import org.antonio.todo.model.Todo;
import org.antonio.todo.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Slf4j
@Component
@Repository
public class TodoDaoImpl implements TodoDao {
    @Autowired
    private TodoRepository todoRepository;

    public TodoDaoImpl() {
        super();
    }

    public Todo create(Todo todo) {
        if (todo.getCreatedTime() == null)
            todo.setCreatedTime(new Date());
        todoRepository.saveAndFlush(todo);
        return todo;
    }

    @Override
    public Todo update(Todo todo) {
        if (todoRepository.existsById(todo.getId())) {
            todo.setUpdatedTime(new Date());
            todoRepository.save(todo);
        }
        return todo;
    }

    @Override
    public void delete(long id) {
        todoRepository.deleteById(id);
    }

    @Override
    public Todo getById(long id) {
        return todoRepository.findById(id);
    }

    @Override
    public Page<Todo> getList(int pageNum, int itemsPerPage) {
        PageRequest request = PageRequest.of(pageNum - 1, itemsPerPage);
        return todoRepository.findAll(request);
    }

    @Override
    public List<Todo> getRelatedTodo(long id) {
        return todoRepository.findByTitleLikeAndIsCompleted("%@" + id + "%", false);
    }

}
