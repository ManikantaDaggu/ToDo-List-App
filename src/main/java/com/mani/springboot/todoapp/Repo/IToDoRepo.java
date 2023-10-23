package com.mani.springboot.todoapp.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mani.springboot.todoapp.model.Todo;

@Repository
public interface IToDoRepo extends JpaRepository<Todo, Long> {
	

}
