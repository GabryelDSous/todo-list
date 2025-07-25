package com.gabryel.todo_list.service;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gabryel.todo_list.entity.TodoList;
import com.gabryel.todo_list.errormessage.CustomErrorMessage;
import com.gabryel.todo_list.repository.TodoRepository;


@Service
public class TodoService {
	
	private final TodoRepository todoRepository;

	public TodoService(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}
	
	public ResponseEntity<?> create(TodoList todo){
		if(todoRepository.findByName(todo.getName()).isEmpty())
			return new ResponseEntity<>(todoRepository.save(todo), HttpStatus.CREATED);
		return userExist();
	}
	
	public ResponseEntity<?> listAll(){
		Sort mainPriority = Sort.by("priority").descending().and(Sort.by("name").ascending());
		if(!todoRepository.findAll().isEmpty())
			return new ResponseEntity<>(todoRepository.findAll(mainPriority), HttpStatus.OK);
		return new ResponseEntity<>(new CustomErrorMessage("users doesn't exist"), HttpStatus.OK);
	}
	
	public ResponseEntity<?> findById(Long id){
		if(!todoRepository.findById(id).isEmpty())
			return new ResponseEntity<>(todoRepository.findById(id), HttpStatus.OK);
		return userNotExist();
	}
	
	public ResponseEntity<?> update(TodoList todo){
		if(!todoRepository.findById(todo.getId()).isEmpty())
			return new ResponseEntity<>(todoRepository.save(todo), HttpStatus.OK);
		return userNotExist();
	}
	
	public ResponseEntity<?> delete(Long id){
		if(!todoRepository.findById(id).isEmpty()) {
			todoRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return userNotExist();
	}

	private ResponseEntity<?> userNotExist(){
		return new ResponseEntity<>(new CustomErrorMessage("User doesn't exist"), HttpStatus.NOT_FOUND);
	}
	private ResponseEntity<?> userExist(){
		return new ResponseEntity<>(new CustomErrorMessage("User just exist"), HttpStatus.NOT_ACCEPTABLE);
	}
}
