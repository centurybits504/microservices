package com.company.client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ClientController {

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping(value = "/add/{numone}/{numtwo}", method = RequestMethod.GET)
	public String addService(@PathVariable String numone,@PathVariable String numtwo) {
		String response = restTemplate.exchange("http://add-service/add/{numone}/{numtwo}", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
		}, numone,numtwo).getBody();
		return "Addition of  "+numone+","+numtwo +" : " + response;
	}
	
	@RequestMapping(value = "/sub/{numone}/{numtwo}", method = RequestMethod.GET)
	public String subService(@PathVariable String numone,@PathVariable String numtwo) {
		String response = restTemplate.exchange("http://sub-service/sub/{numone}/{numtwo}", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
		}, numone,numtwo).getBody();
		return "Sub of  "+numone+","+numtwo +" : " + response;
	}

 	
	@HystrixCommand(
			groupKey="fallback",
			commandKey="fallback",
			fallbackMethod="getDivisionFallback"
			)
	@RequestMapping(value = "/divisionclient/{numone}/{numtwo}", method = RequestMethod.GET)
	public String getDivision(@PathVariable int numone, @PathVariable int numtwo) {
		ResponseEntity<String> response=null;
		response=restTemplate.exchange("http://192.168.2.16:8087/api/division/divi/{numone}/{numtwo}", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
		}, numone,numtwo);

		if(response.getStatusCode()==HttpStatus.OK)
			return "Division of  "+numone+","+numtwo +" : " + response.getBody();
		else
			return "Division of  "+numone+","+numtwo +" : " +  response.getBody();
	
	}
	
	public String getDivisionFallback(int numone, int numtwo,Throwable hystrixCommand) {
		return "Can't divide, Reason -"+hystrixCommand.getMessage();
	}
	 
/*	@RequestMapping(value = "/division/{numone}/{numtwo}", method = RequestMethod.GET)
	public String divisionService(@PathVariable String numone,@PathVariable String numtwo) {
		String response = null;
		try {
		System.out.println("numone " + numone);
		System.out.println("numtwo " + numtwo);
		ResponseEntity<String> t = restTemplate.exchange("http://div-service/division/{numone}/{numtwo}", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
		}, numone,numtwo);
		System.out.println("------"+t.getBody());
		response = t.getBody();
		if(t.getStatusCode().OK==HttpStatus.OK){
			return "Division of  "+numone+","+numtwo +" : " + t.getBody();
		}else {
			return "Division of  "+numone+","+numtwo +" : " + t.getBody();
		}
		}catch(Exception e) {
			e.printStackTrace();
			return "Division of  "+numone+","+numtwo +" : " + response;
		}
			
	}
*/	
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	 
}
