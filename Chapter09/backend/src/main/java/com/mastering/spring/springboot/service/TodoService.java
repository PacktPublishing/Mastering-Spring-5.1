package com.mastering.spring.springboot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mastering.spring.springboot.bean.Todo;

@Service
public class TodoService {
	private static List<Todo> todos = new ArrayList<Todo>();
	private static int todoCount = 4;
	static {
		todos.add(new Todo(1, "Jack", "Learn Spring MVC", new Date(), false));
		todos.add(new Todo(2, "Jack", "Learn Struts", new Date(), false));
		todos.add(new Todo(3, "Jill", "Learn Hibernate", new Date(), false));
		todos.add(new Todo(4, "Jill", "Learn Spring MVC", new Date(), false));
	}

	public List<Todo> retrieveTodos(String user) {
		List<Todo> filteredTodos = new ArrayList<Todo>();
		for (Todo todo : todos) {
			if (todo.getUser().equals(user))
				filteredTodos.add(todo);
		}
		return filteredTodos;
	}

	public Todo addTodo(String name, String desc, Date targetDate, boolean isDone) {
		Todo todo = new Todo(++todoCount, name, desc, targetDate, isDone);
		todos.add(todo);
		return todo;
	}

	public Todo retrieveTodo(int id) {
		for (Todo todo : todos) {
			if (todo.getId() == id)
				return todo;
		}
		return null;
	}

	public Todo update(Todo todo) {
		Todo deletedTodo = deleteById(todo.getId());
		if (deletedTodo == null) {
			throw new RuntimeException("Todo not found");
		}
		todos.add(todo);
		return todo;
	}

	public Todo deleteById(int id) {
		Todo todo = retrieveTodo(id);

		if (todo == null)
			throw new RuntimeException("Todo not found");

		if (todos.remove(todo)) {
			return todo;
		}

		throw new RuntimeException("Delete Unsuccessful");
	}
}