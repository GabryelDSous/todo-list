package com.gabryel.todo_list.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping
	public ResponseEntity<?> findAll(@RequestBody @Valid TodoList todo){
		return todoService.listAll(todo);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findAll(@PathVariable Long id){
		return todoService.findById(id);
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody @Valid TodoList todo){
		return todoService.update(todo);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		return todoService.delete(id);
	}
}