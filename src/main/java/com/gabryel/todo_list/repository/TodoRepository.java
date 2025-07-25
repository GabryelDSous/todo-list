package com.gabryel.todo_list.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabryel.todo_list.entity.TodoList;

public interface TodoRepository extends JpaRepository<TodoList, Long>{
	List<TodoList> findByName(String name);
}
