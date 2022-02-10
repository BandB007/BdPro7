package com.qa.bdpro.service;

import org.springframework.stereotype.Service;

@Service
public class BService {
	
	private BRepo repo;
	
	public BService (BRepo repo) {
		this.repo = repo;
	}


}
