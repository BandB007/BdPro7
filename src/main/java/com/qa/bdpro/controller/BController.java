package com.qa.bdpro.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class BController {
	private BService service;
	
	//Constructor injection
		public BController (BService service) {
			this.service = service;
		}

}
