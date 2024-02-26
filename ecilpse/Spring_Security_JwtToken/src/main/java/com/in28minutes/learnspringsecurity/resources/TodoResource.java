package com.in28minutes.learnspringsecurity.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.security.RolesAllowed;

@RestController
public class TodoResource {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final List<Todo> TODOS_LIST = List.of(
			new Todo("in28minutes","Learn AWS"),
			new Todo("in28minutes","Get AWS Certified")
			);

	@GetMapping("/todos")
	public List<Todo> retrieveAllTodos() {
		return TODOS_LIST;
	}
	
	//security에서 @EnableMethodSecurity, false면 403반
	@GetMapping("/users/{username}/todos")
	@PreAuthorize("hasRole('USER') and #username == authentication.name")	//username와 authentication.name이 같아야한다 
	@PostAuthorize("returnObject.username == 'in28minutes'")	//return객체의 usernam이 'in28minutes'와 같아야한
	@RolesAllowed({"ADMIN","USER"})	//jsr250
	public Todo retrieveTodosGorSpecificUser(@PathVariable("username") String username) {
		return TODOS_LIST.get(0);
	}
	
	@PostMapping("/users/{username}/todos")
	public void createTodosGorSpecificUser(@PathVariable("username") String username,@RequestBody Todo todo) {
		logger.info("Create {} for {}",todo,username);
	}
}

record Todo(String username,String description) {}


