package com.gabryel.todo_list.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.gabryel.todo_list.entity.TodoList;
import com.gabryel.todo_list.repository.TodoRepository;

import jakarta.validation.Valid;

@Service
public class TodoService {
	
	private final TodoRepository todoRepository;

	public TodoService(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}
	
	public ResponseEntity<?> create(@RequestBody @Valid TodoList todo){
		return new ResponseEntity<>(todoRepository.save(todo), HttpStatus.CREATED);
	}
	
	public ResponseEntity<?> listAll(@RequestBody @Valid TodoList todo){
		return new ResponseEntity<>(todoRepository.findAll(), HttpStatus.OK);
	}
	
	public ResponseEntity<?> findById(Long id){
		return new ResponseEntity<>(todoRepository.findById(id), HttpStatus.OK);
	}
	
	public ResponseEntity<?> update(@RequestBody @Valid TodoList todo){
		return new ResponseEntity<>(todoRepository.save(todo), HttpStatus.UPGRADE_REQUIRED);
	}
	
	public ResponseEntity<?> delete(Long id){
		todoRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	
}
