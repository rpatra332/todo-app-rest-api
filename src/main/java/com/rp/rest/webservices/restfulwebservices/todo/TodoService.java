package com.rp.rest.webservices.restfulwebservices.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

//@Service
public class TodoService {

	private static List<Todo> todos = new ArrayList<>();

	private static int todosCount = 0;

	static {
		todos.add(new Todo(++todosCount, "rp", "Get AWS Certified", LocalDate.now().plusYears(2), false));
		todos.add(new Todo(++todosCount, "trevor", "Learn DevOps", LocalDate.now().plusYears(1), false));
		todos.add(new Todo(++todosCount, "rp", "Learn Full Stack", LocalDate.now().plusYears(2), false));
		todos.add(new Todo(++todosCount, "rp", "Laundry", LocalDate.now().plusMonths(1), false));
		todos.add(new Todo(++todosCount, "rp", "Learn Salsa", LocalDate.now().plusYears(2), false));
		todos.add(new Todo(++todosCount, "trevor", "Learn Psychology", LocalDate.now().plusYears(3), false));
		todos.add(new Todo(++todosCount, "trevor", "Go for the DJ gig", LocalDate.now().plusDays(3), false));
	}

	public List<Todo> findByUsername(String username) {
		Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
		return todos.stream().filter(predicate).toList();
	}

	public Todo addTodo(String username, String description, LocalDate targetDate, boolean done) {
		Todo todo = new Todo(++todosCount, username, description, targetDate, done);
		todos.add(todo);
		return todo;
	}

	public void deleteById(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		todos.removeIf(predicate);
	}

	public Todo findById(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		Todo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}

	public void updateTodo(Todo todo) {
		deleteById(todo.getId());
		todos.add(todo);
	}
}