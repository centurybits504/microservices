package com.company.sub.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//@RestController("/subservice")
@RestController
public class SubstractionController {

	@RequestMapping(value = "/sub/{numone}/{numtwo}", method = RequestMethod.GET)
	private ResponseEntity<Integer> getSubstraction(@PathVariable String numone, @PathVariable String numtwo) {
		Integer result = Integer.parseInt(numone) - Integer.parseInt(numtwo);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
}
