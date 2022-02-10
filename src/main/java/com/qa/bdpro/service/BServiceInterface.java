package com.qa.bdpro.service;

import java.util.List;

public interface BServiceInterface <T> {

	T create(T book);

	List<T> readAll();
	
	T readone(Long id);

	T update(Long id, T book);

	T delete(Long id);

	T readOne(Long id);
}



