package com.gabryel.todo_list.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gabryel.todo_list.entity.TodoList;
import com.gabryel.todo_list.repository.TodoRepository;


@Service
public class TodoService {
	
	private final TodoRepository todoRepository;

	public TodoService(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}
	
	public ResponseEntity<?> create(TodoList todo){
		return new ResponseEntity<>(todoRepository.save(todo), HttpStatus.CREATED);
	}
	
	public ResponseEntity<?> listAll(TodoList todo){
		return new ResponseEntity<>(todoRepository.findAll(), HttpStatus.OK);
	}
	
	public ResponseEntity<?> findById(Long id){
		return new ResponseEntity<>(todoRepository.findById(id), HttpStatus.OK);
	}
	
	public ResponseEntity<?> update(TodoList todo){
		return new ResponseEntity<>(todoRepository.save(todo), HttpStatus.OK);
	}
	
	public ResponseEntity<?> delete(Long id){
		todoRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	
}
