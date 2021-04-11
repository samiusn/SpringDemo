package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Nlp;
import com.example.service.NLPService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/nlp")
public class NLPController {
	@Autowired
	private NLPService nlpService;

	@CrossOrigin
	@PostMapping(value = "/getSentiment", produces = "application/json")
	public String getSentiment(@RequestBody String text) {

		Nlp nlpObj = new Nlp(nlpService.getSentiment(text)
								.stream()
								.mapToInt(Integer::intValue)
								.toArray());
		String json = new Gson().toJson(nlpObj);

		return "2";
	}

}
