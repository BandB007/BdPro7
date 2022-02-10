package com.qa.bdpro.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.bdpro.domain.BooksLibrary;
import com.qa.bdpro.repo.BRepo;

@SpringBootTest
public class ServiceTest {

	private BooksLibrary newBook;
	private BooksLibrary savedBook;
	
	@Autowired
	private BService service;
	
	@MockBean
	private BRepo repo;
	
	//executed before each @Test method
	@BeforeEach
	void setup() {
		newBook = new BooksLibrary("Maxim", "Max", "No", "Yes");
		savedBook = new BooksLibrary("Maxim", "Max", "No", "Yes");
	}
	
	@Test
	//Create
	void testcreate() {
		Mockito.when(this.repo.save(newBook)).thenReturn(savedBook);
		assertThat(this.service.create(newBook)).isEqualTo(savedBook);
		Mockito.verify(this.repo, Mockito.times(1)).save(newBook);
		
		
		
	}
	
}
