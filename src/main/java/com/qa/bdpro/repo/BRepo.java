package com.qa.bdpro.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.bdpro.domain.BooksLibrary;

@Repository
public interface BRepo extends JpaRepository<BooksLibrary, Long>{

}
