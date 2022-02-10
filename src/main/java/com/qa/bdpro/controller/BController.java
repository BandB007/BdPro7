package com.qa.bdpro.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.bdpro.domain.BooksLibrary;
import com.qa.bdpro.service.BService;

@RestController
public class BController {
	private BService service;
	
	//Constructor injection
		public BController (BService service) {
			this.service = service;
		}
		
	//Create
		@PostMapping("/create")
		public ResponseEntity<BooksLibrary>create(@RequestBody BooksLibrary book) {
			return new ResponseEntity<BooksLibrary>(this.service.create(book),HttpStatus.CREATED);
		}
		
	//read
		@GetMapping("/getall")
		public ResponseEntity<List<BooksLibrary>> getAll() {
			return new ResponseEntity<List<BooksLibrary>>(this.service.getAll(), HttpStatus.OK);
		}
		
	//finding by id
		@GetMapping("/getOne/{id}")
		public ResponseEntity<BooksLibrary>getOne(@PathVariable Long id) {
			return new ResponseEntity<BooksLibrary>(this.service.getById(id), HttpStatus.ACCEPTED);
		}
	
	//update
		@PutMapping("/replace/{id}")
		public ResponseEntity<BooksLibrary>replace(@PathVariable Long id, BooksLibrary book) {
			return new ResponseEntity<BooksLibrary>(this.service.replace(id, book), HttpStatus.ACCEPTED);
		}

	//delete
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<BooksLibrary>delete(@PathVariable Long id) {
			return new ResponseEntity<BooksLibrary>(this.service.delete(id), HttpStatus.OK);

		}
}
