package com.gabryel.todo_list.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabryel.todo_list.entity.TodoList;
import com.gabryel.todo_list.service.TodoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("Todolist")
public class TodoController{
	
	private final TodoService todoService;
	
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody @Valid TodoList todo){
		return todoService.create(todo);
	}
	
}