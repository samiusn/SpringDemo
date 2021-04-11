package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RestController
//@RequestMapping("/api")
public class Controller {
	
//	@GetMapping("/hello")
	@RequestMapping("/helloWorld")
	public String Hello() {
		return "Hello from Spring Bro";
	}
	
	@Autowired
	private CityRepository cityRepository;
	@CrossOrigin
	@RequestMapping(value = "/city/{id}", produces ="application/json")
//	@GetMapping(value = "/city/{id}")
	public ResponseEntity<City> getCityById(@PathVariable("id") long id){
		
		Optional<City> city = cityRepository.findById(id);
		
//		HttpHeaders headers = new HttpHeaders();
//	    headers.add("Content-Type", "application/json");
	    
//		return cityRepository.findById(id);
		if(city.isPresent()) {
			return new ResponseEntity<> (city.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@CrossOrigin
	@RequestMapping("/cities")
	public String getCities() {
		List<City> city = cityRepository.findAll();
//		ObjectMapper mapper = new ObjectMapper();
		Gson gson = new Gson();
		
//		String jsonString = mapper.writeValueAsString(std);
		String citiesJSON = gson.toJson(city,
				new TypeToken<ArrayList<City>>() {}.getType());
		
		System.out.println(city);
		
		return citiesJSON;
	}

}
