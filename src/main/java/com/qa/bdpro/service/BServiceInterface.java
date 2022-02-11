package com.qa.bdpro.service;

import java.util.List;

public interface BServiceInterface <T> {

	T create(T book);

	List<T> getAll();
	
	T update(Long id, T book);

	T delete(Long id);

	T getByID(Long id);
}



