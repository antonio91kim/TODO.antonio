package org.antonio.todo.service;

import org.antonio.todo.model.Todo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TodoServiceImplTest.class, loader = AnnotationConfigContextLoader.class)
@ComponentScan("org.antonio.todo")
public class TodoServiceImplTest {
    @Autowired
    private TodoService todoService;

    @Before
    public void setUp() {

    }

    @Test
    public void testInsert() {
        Todo todo = new Todo();
        todo.setTitle("This is test title");
        Todo savedTodo = todoService.create(todo);

        savedTodo = todoService.getById(savedTodo.getId());
        Assert.assertNotNull(savedTodo);
        Assert.assertEquals("This is test title", savedTodo.getTitle());
        Assert.assertNotNull(savedTodo.getCreatedTime());
    }

    @Test
    public void testUpdate() {
        Todo todo = new Todo();
        todo.setTitle("This is test title");
        Todo savedTodo = todoService.create(todo);
        savedTodo.setTitle("This is updated title");
        Todo updatedTodo = todoService.update(savedTodo.getId(), savedTodo);

        updatedTodo = todoService.getById(updatedTodo.getId());
        Assert.assertNotNull(updatedTodo);
        Assert.assertEquals("This is updated title", updatedTodo.getTitle());
        Assert.assertNotNull(updatedTodo.getUpdatedTime());
    }

    @Test
    public void testGetById() {
        Todo todo  = todoService.getById(1);

        Assert.assertNotNull(todo);
        Assert.assertEquals(1, todo.getId());
    }

    @Test
    public void testDelete() {
        Todo savedTodo = todoService.getById(1);
        todoService.delete(1);

        Todo deletedTodo = todoService.getById(1);
        Assert.assertNull(deletedTodo);
    }

    @Test
    public void testGetList() {
        for (int i = 0; i < 10; i++) {
            Todo todo = new Todo();
            todo.setTitle("This is test title");
            todoService.create(todo);
        }

        Page<Todo> todoPage = todoService.getList(1, 10);
        Assert.assertNotNull(todoPage);
        Assert.assertEquals(12, todoPage.getTotalElements());
        Assert.assertEquals(10, todoPage.getContent().size());
    }

    @Test
    public void testGetRelatedTodo() {
        Todo todo1 = new Todo();
        todo1.setTitle("This is first todo");
        todo1 = todoService.create(todo1);

        Todo todo2 = new Todo();
        todo2.setTitle("This is related todo @1");
        todoService.create(todo2);

        List<Todo> relatedList = todoService.getRelatedTodo(todo1.getId());
        Assert.assertEquals(1, relatedList.size());
    }

}
