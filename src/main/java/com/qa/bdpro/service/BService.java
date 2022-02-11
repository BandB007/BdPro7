package com.qa.bdpro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.bdpro.domain.BooksLibrary;
import com.qa.bdpro.repo.BRepo;

@Service
public class BService implements BServiceInterface<BooksLibrary> {
	
	private BRepo repo;
	
	public BService (BRepo repo) {
		this.repo = repo;
	}

//create
	@Override
	public BooksLibrary create(BooksLibrary book) {
		return this.repo.save(book);
	}	
	
//Read All
	@Override
	public List<BooksLibrary> getAll() {
		return this.repo.findAll();
	}

//getone
	@Override
	public BooksLibrary getByID(Long id) {
		return this.repo.findById(id).get();
	}
//update
	@Override
	public BooksLibrary update(Long id, BooksLibrary book) {
		BooksLibrary existing = this.repo.findById(id).get();
		existing.setAuthor(book.getAuthor());
		existing.setAvailable(book.getAvailable());
		existing.setReserved(book.getReserved());
		existing.setTitle(book.getTitle());
		return this.repo.save(existing);
	}
	
	//delete
	@Override
		public BooksLibrary delete(Long id) {
			Optional<BooksLibrary> toDelete = this.repo.findById(id);
			this.repo.deleteById(id);	
			return toDelete.orElse(null);
		}
}

