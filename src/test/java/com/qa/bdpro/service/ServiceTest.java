package com.qa.bdpro.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
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
		//When
		Mockito.when(this.repo.save(newBook)).thenReturn(savedBook);
		//Then
		assertThat(this.service.create(newBook)).isEqualTo(savedBook);
		//Verify
		Mockito.verify(this.repo, Mockito.times(1)).save(newBook);		
	}
	@Test
	void testReadAll() {
		
		
	}
	
	
	
	@Test
	void testUpdate() {	
	// GIVEN - id, object
			Long id = 1L;
	BooksLibrary toUpdate = new BooksLibrary("Bravo", "Bro", "No", "Yes");
	Optional<BooksLibrary> optBook = Optional.of(new BooksLibrary(id, null, null, null, null));
	BooksLibrary updated = new BooksLibrary (id, toUpdate.getTitle(), toUpdate.getAuthor(), 
			toUpdate.getAvailable(), toUpdate.getReserved());
	//When 
	Mockito.when(this.repo.findById(id)).thenReturn(optBook);
	Mockito.when(this.repo.save(updated)).thenReturn(updated);
	// Then
	assertThat(this.service.update(id, toUpdate)).isEqualTo(updated);
	// VERIFY
	Mockito.verify(this.repo, Mockito.times(1)).save(updated);
	Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		}

	@Test
	void testDelete() {
		// GIVEN
		Long id = 1L;
		Optional<BooksLibrary> optBook = Optional.of(new BooksLibrary());
		BooksLibrary deleted = optBook.get();
		Mockito.when(this.repo.findById(id)).thenReturn(optBook);
		assertThat(this.service.delete(id)).isEqualTo(deleted);
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(id);
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}	

}
