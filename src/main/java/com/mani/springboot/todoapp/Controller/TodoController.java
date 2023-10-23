package com.mani.springboot.todoapp.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mani.springboot.todoapp.Service.Todoservice;
import com.mani.springboot.todoapp.model.Todo;

@Controller
public class TodoController {
	
	@Autowired
	private Todoservice todoservice;
	
	@GetMapping("/")
	public String homepage(Model model)
	{
	
		String viewname=new String("ViewToDoList");
		return "Home";
	}
	
	@GetMapping({"/viewToDoList"})
	public String viewAllToDoItems(Model model, @ModelAttribute("message")String message) {
		model.addAttribute("List",todoservice.getAllToDoItems());
		model.addAttribute("msg", message);
		return "ViewToDoList";
		
	}
	
	@GetMapping("/updateToDoStatus/{id}")
	public String updateToDoStatus(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		if(todoservice.updateStatus(id)) {
			redirectAttributes.addFlashAttribute("message","Update Success");
			return "redirect:/viewToDoList";
		}
		redirectAttributes.addFlashAttribute("message","Update Failure");
		return "redirect:/viewToDoList";
	}
	
	@GetMapping("/addToDoItem")
	public String addToDoItem(Model model) {
		model.addAttribute("todo",new Todo());
		
		return "AddToDoItem";
		
	}
	
	@PostMapping("/saveToDoItem")
	public String saveToDoItem(Todo todo, RedirectAttributes redirectAttributes) {
		if(todoservice.saveOrUpdateToDoItem(todo)) {
			redirectAttributes.addFlashAttribute("message", "Save Success");
			return "redirect:/viewToDoList";
		}
		redirectAttributes.addFlashAttribute("message", "Save Failure");
		return "redirect:/addToDoItem";
		
	}
	
	@GetMapping("/editToDoItem/{id}")
	public String editToDoItem(@PathVariable Long id, Model model) {
		model.addAttribute("todo",todoservice.getToDoItemById(id));
		return "EditToDoItem";
		
	}
	
	@PostMapping("/editSaveToDoItem")
	public String editSaveToDoItem(Todo todo, RedirectAttributes redirectAttributes) {
		if(todoservice.saveOrUpdateToDoItem(todo)) {
			redirectAttributes.addFlashAttribute("message", "Edit Success");
			return "redirect:/viewToDoList";
		}
		redirectAttributes.addFlashAttribute("message", "Edit Failure");
		return "redirect:/editToDoItem"+todo.getId();
	}
	
	@GetMapping("/deleteToDoItem/{id}")
	public String deleteToDoItem(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		if(todoservice.deleteTodoItem(id)) {
			redirectAttributes.addFlashAttribute("message", "Deleted Success");
			return "redirect:/viewToDoList";
		}
		redirectAttributes.addFlashAttribute("message", "Edit Failure");
		return "redirect:/viewToDoList";
	}
	
}
