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
		return new ResponseEntity<>(todoRepository.save(todo), HttpStatus.CREATED);
	}
	
	public ResponseEntity<?> listAll(){
		Sort mainPriority = Sort.by("priority").descending().and(Sort.by("name").ascending());
		return new ResponseEntity<>(todoRepository.findAll(mainPriority), HttpStatus.OK);
	}
	
	public ResponseEntity<?> findById(Long id){
		if(!todoRepository.findById(id).isEmpty())
			return new ResponseEntity<>(todoRepository.findById(id), HttpStatus.OK);
		return userNotExist(id);
	}
	
	public ResponseEntity<?> update(TodoList todo){
		if(!todoRepository.findById(todo.getId()).isEmpty())
			return new ResponseEntity<>(todoRepository.save(todo), HttpStatus.OK);
		return userNotExist(todo.getId());
	}
	
	public ResponseEntity<?> delete(Long id){
		todoRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	private ResponseEntity<?> userNotExist(Long id){
		return new ResponseEntity<>(new CustomErrorMessage("This user no exist"), HttpStatus.NOT_FOUND);
	}
}
