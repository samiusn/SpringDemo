package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	@RequestMapping("/hello")
	public String Hello() {
		return "Hello from Spring Bro";
	}
	
	@Autowired
	private ICityService cityService;
	
	@RequestMapping("/cities")
	public String getCities() {
		var cities = (List<City>) cityService.findAll();
		
		cities.forEach(s -> s.getName());
		
		return "Cities";
	}

}
