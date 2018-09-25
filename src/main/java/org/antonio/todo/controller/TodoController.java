package org.antonio.todo.controller;

import lombok.extern.slf4j.Slf4j;
import org.antonio.todo.common.ErrorCode;
import org.antonio.todo.common.TodoException;
import org.antonio.todo.model.Todo;
import org.antonio.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @PostMapping(value = "/todo")
    @ResponseBody
    public void create(@RequestBody Todo todo) {
        if (todo.getTitle() == null || todo.getTitle().length() == 0)
            throw new TodoException(ErrorCode.WRONG_PARAMETER, "No title");
        if (todo.getCreatedTime() == null)
            todo.setCreatedTime(new Date());
        todoService.create(todo);
    }

    @PutMapping(value = "/todo/{id}")
    public void update(@PathVariable long id, @RequestBody Todo todo) {
        todoService.update(id, todo);
    }

    @DeleteMapping(value = "todo/{id}")
    public void delete(@PathVariable long id) {
        todoService.delete(id);
    }

    @PutMapping(value = "/todo/{id}/complete")
    public void complete(@PathVariable long id) {
        Todo todo = todoService.getById(id);
        if (todo != null) {
            // check related item
            List<Todo> relatedList = todoService.getRelatedTodo(id);
            Iterator<Todo> iter = relatedList.iterator();
            while (iter.hasNext()) {
                Todo related = iter.next();
                String title = related.getTitle() + " ";
                // check exactly same match
                if (title.indexOf("@" + id + " ") < 0)
                    iter.remove();
            }
            if (relatedList.size() == 0) {
                todo.setCompleted(true);
                todoService.update(id, todo);
            } else {
                throw new TodoException(ErrorCode.RELATED_EXIST, "Cannot complete due to related item(s)");
            }
        } else {
            throw new TodoException(ErrorCode.NOT_EXIST, "Todo not exist [id: " + id + "]");
        }
    }

    @GetMapping("/todo/{id}")
    public Todo getById(@PathVariable long id) {
        return todoService.getById(id);
    }

    @GetMapping("/todo")
    public Page<Todo> getList(@RequestParam int pageNum, @RequestParam int itemsPerPage) {
        return todoService.getList(pageNum, itemsPerPage);
    }

    @ExceptionHandler(value = TodoException.class)
    public ResponseEntity<Object> handleException(TodoException e) {
        if (e.getErrorCode() == ErrorCode.NOT_EXIST || e.getErrorCode() == ErrorCode.WRONG_PARAMETER)
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        else if (e.getErrorCode() == ErrorCode.RELATED_EXIST)
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        else
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
