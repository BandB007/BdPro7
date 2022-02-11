package com.qa.bdpro.controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.bdpro.domain.BooksLibrary;


@SpringBootTest
@AutoConfigureMockMvc //making the test requests
@Sql(scripts = { "classpath:bdpro-schema.sql",
		"classpath:bdpro-data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
																									
@ActiveProfiles("bdtest")
public class ControlTest {

	@Autowired
		private MockMvc mock;// Mocking the requests
	
	@Autowired
		private ObjectMapper map;// Interpreting JSON
	
	//create
	@Test
		void createTest() throws Exception {
			// create a book in Library
			BooksLibrary newB = new BooksLibrary ("Aim", "Amy", "Yes", "No");
			String newBJSON = this.map.writeValueAsString(newB);
			RequestBuilder mockRequest = post("/create").contentType(MediaType.APPLICATION_JSON)
					.content(newBJSON);
	
			/// save a new book in Library 
			BooksLibrary savedB = new BooksLibrary (2L, "Big", "Biggy", "No","Yes");																							
			String savedBJSON = this.map.writeValueAsString(savedB);
			
			// response (status + body)
			ResultMatcher matchStatus = status().isCreated();
			ResultMatcher matchBody = content().json(savedBJSON);
			
			// perform the test
			this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
		}
		
		//read
		@Test
			void readTest() throws Exception {	
			BooksLibrary readB = new BooksLibrary(1L, "Bepos", "Bpo", "No", "Yes");
			List<BooksLibrary> allBook = List.of(readB);
			String readBookJSON = this.map.writeValueAsString(allBook);
			RequestBuilder readReq = get("/getAll");
			ResultMatcher status = status().isOk();
			ResultMatcher body = content().json(readBookJSON);
			this.mock.perform(readReq).andExpect(status).andExpect(body);

		}
		
		//read by id
		@Test
		void readByIdTest() throws Exception {	
		BooksLibrary readById = new BooksLibrary(1L, "Bepos", "Bpo", "No", "Yes");
		List<BooksLibrary> allBook = List.of(readById);
		String readBookJSON = this.map.writeValueAsString(allBook);
		RequestBuilder readReq = get("/getById");
		ResultMatcher status = status().isOk();
		ResultMatcher body = content().json(readBookJSON);
		this.mock.perform(readReq).andExpect(status).andExpect(body);
		}
		
		//update
		@Test
			void updateTest() throws Exception {
			BooksLibrary updateBook = new BooksLibrary("Always", "Al", "No", "Yes");
			String updateBookJSON = this.map.writeValueAsString(updateBook);
			Long IDupdate = 1L;
			RequestBuilder updateReq = put("/replace/" + IDupdate).contentType(MediaType.APPLICATION_JSON)
					.content(updateBookJSON);
			BooksLibrary retUpdatedBook = new BooksLibrary (1L, "Bepos", "Bpo", "No", "Yes");
			String retUpdatedBookJSON = this.map.writeValueAsString(retUpdatedBook);
			
			ResultMatcher retStatus = status().isOk();
			ResultMatcher retBody = content().json(retUpdatedBookJSON);

			this.mock.perform(updateReq).andExpect(retStatus).andExpect(retBody);
		}
		
		//delete
		@Test
			void deleteTest() throws Exception {
			BooksLibrary deleteBook = new BooksLibrary(1L, "Bepos", "Bpo", "No", "Yes");
			String deleteBookJSON = this.map.writeValueAsString(deleteBook);
			Long remId = 1L;
			RequestBuilder delRequest = delete("/delete/" + remId);
			ResultMatcher Status = status().isOk();
			ResultMatcher Body = content().json(deleteBookJSON);
			this.mock.perform(delRequest).andExpect(Status).andExpect(Body);
		}
}
