package com.company.add.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdditionController {

	@RequestMapping(value = "/add/{numone}/{numtwo}", method = RequestMethod.GET)
	public ResponseEntity<Integer> getAddition(@PathVariable Integer numone, @PathVariable Integer numtwo) {
		Integer result = null;
		try {
			System.out.println("in add-service");
			result = numone + numtwo;
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<Integer>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Integer> postAddition() {
		Integer result = null;
		try {
			result = numone + numtwo;
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<Integer>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/

}


