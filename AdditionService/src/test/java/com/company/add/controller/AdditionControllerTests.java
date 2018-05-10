package com.company.add.controller;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdditionControllerTests {

	@Autowired
	AdditionController additionController;

	@Before
	public void setBefore() {
  	}

	@Test
	public void getAdditionTest() {
		ResponseEntity<Integer> result = additionController.getAddition(2, 2);
		assertTrue(result.getStatusCode() == HttpStatus.OK);
	}
	
	@Test
	public void getAdditionStringTest() {
		ResponseEntity<Integer> result = additionController.getAddition(2, null);
		assertTrue(result.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@After
	public void setAfter() {
	}
	
}
