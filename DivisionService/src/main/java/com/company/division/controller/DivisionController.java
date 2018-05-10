package com.company.division.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class DivisionController {

	/*@HystrixCommand(
			groupKey="fallback",
			commandKey="fallback",
			fallbackMethod="getDivisionFallback"
			)
	@RequestMapping(value = "/division/{numone}/{numtwo}", method = RequestMethod.GET)
	public ResponseEntity<String> getDivision(@PathVariable int numone, @PathVariable int numtwo) {
		int result = (numone / numtwo);
		return new ResponseEntity<String>(result+"", HttpStatus.OK);
	}
	
	public ResponseEntity<String> getDivisionFallback(int numone, int numtwo) {
		return new ResponseEntity<String>("Cann't divide with 0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	*/
			
			@RequestMapping(value = "/divi/{numone}/{numtwo}", method = RequestMethod.GET)
			public ResponseEntity<String> getDivision(@PathVariable int numone, @PathVariable int numtwo) {
				String result = null;
				try {
					if(numtwo==0) {
						System.out.println("here...");
						return new ResponseEntity<String>("Cann't divide with "+numtwo, HttpStatus.OK);
					}else {
						result = (numone / numtwo)+"";
						return new ResponseEntity<String>(result, HttpStatus.OK);						
					}
				}catch (Exception e) {
					e.printStackTrace();
					return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}		
			
}
