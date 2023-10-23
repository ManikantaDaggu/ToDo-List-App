package com.mani.springboot.todoapp.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mani.springboot.todoapp.Repo.IToDoRepo;
import com.mani.springboot.todoapp.model.Todo;

@Service
public class Todoservice {
	
	@Autowired
	IToDoRepo repo;
	
	public List<Todo> getAllToDoItems(){
		ArrayList<Todo>  todolist=new ArrayList<>();
		repo.findAll().forEach(todo -> todolist.add(todo));
		return todolist;
	}
	
	public Todo getToDoItemById(Long id){
		return repo.findById(id).get();
	}
	
	public boolean updateStatus(Long id) {
		Todo todo=getToDoItemById(id);
		todo.setStatus("Completed");
		
		return saveOrUpdateToDoItem(todo);
		
	}
	
	public boolean saveOrUpdateToDoItem(Todo todo) {
		Todo updatedobj=repo.save(todo);
		
		if(getToDoItemById(updatedobj.getId())!=null) {
			return true;
		}
		
		return false;
		
	}
	
	public boolean deleteTodoItem(Long id) {
		repo.deleteById(id);
		if(repo.findById(id).isEmpty()) {
			return true;
		}
		return false;
	}

}
