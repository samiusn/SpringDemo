package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.City;
import com.example.model.Item;
import com.example.service.ItemRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RestController
@RequestMapping("/api")
public class ItemController {
	@RequestMapping("/helloWorld")
	public String helloWorld() {
		return "Hello from Item Controller.";
	}
	
	@Autowired
	private ItemRepository itemRepository;
	
	@CrossOrigin
	@GetMapping(value = "/item/{id}")
	public ResponseEntity<Item> getItemById(@PathVariable("id") long id){
		
		Optional<Item> item = itemRepository.findById(id);
		
		if(item.isPresent()) {
			return new ResponseEntity<> (item.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@CrossOrigin
	@RequestMapping("/items")
	public String getItems() {
		List<Item> item = itemRepository.findAll();
		Gson gson = new Gson();
		
		String itemsJSON = gson.toJson(item,
				new TypeToken<ArrayList<City>>() {}.getType());
		
		System.out.println(item);
		
		return itemsJSON;
	}
	
	@CrossOrigin
	@PostMapping(value = "/item/create", produces ="application/json")
	public String createItem(@RequestBody Item item) {
		Item returnItem = itemRepository.save(item);
		
		ObjectMapper mapper = new ObjectMapper();
		String returnJSON = "";
		try {
			returnJSON = mapper.writeValueAsString(returnItem);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(returnJSON);
		
		return returnJSON;
		
	}

}



















