package org.antonio.todo.repositories;

import org.antonio.todo.model.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    public Todo findById(long id);
    public Page<Todo> findAll(Pageable pageable);
    public List<Todo> findByTitleLikeAndIsCompleted(String ref, boolean isCompleted);
}
