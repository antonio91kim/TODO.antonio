package org.antonio.todo.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Todo")
@Getter
@Setter
public class Todo {

    @Id
    @GeneratedValue(generator="seq_todo", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="seq_todo",sequenceName="SEQ_TODO", allocationSize=100)
    @Column(name="id")
    long id;

    @Column(name="title")
    String title;

    @Column(name="createdTime")
    Date createdTime;

    @Column(name="updatedTime")
    Date updatedTime;

    @Column(name="isCompleted")
    boolean isCompleted;


}
