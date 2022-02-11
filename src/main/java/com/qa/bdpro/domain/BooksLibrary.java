package com.qa.bdpro.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Books")
public class BooksLibrary {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column (nullable = false)
	private String title;
	
	@Column (nullable = false)
	private String author;
	
	@Column 
	private String available;

	@Column
	private String reserved;
	
//Empty Constructor
	public BooksLibrary() {}

//Constructor without id	
	public BooksLibrary(String title, String author, String available, String reserved) {
	this.title = title;
	this.author = author;
	this.available = available;
	this.reserved = reserved;
}
	
//Constructor with all variables
public BooksLibrary(Long id, String title, String author, String available, String reserved) {
	this.id = id;
	this.title = title;
	this.author = author;
	this.available = available;
	this.reserved = reserved;
}

//getters and setters
public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getAuthor() {
	return author;
}

public void setAuthor(String author) {
	this.author = author;
}

public String getAvailable() {
	return available;
}

public void setAvailable(String available) {
	this.available = available;
}

public String getReserved() {
	return reserved;
}

public void setReserved(String reserved) {
	this.reserved = reserved;
}

//hashcode, equals and tostring



//@Override
//public String toString() {
//	return "BooksLibrary [id=" + id + ", title=" + title + ", author=" + author + ", available=" + available
//			+ ", reserved=" + reserved + "]";
//}

@Override
public int hashCode() {
	return Objects.hash(author, available, reserved, title);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	BooksLibrary other = (BooksLibrary) obj;
	return Objects.equals(author, other.author) && Objects.equals(available, other.available)
			&& Objects.equals(reserved, other.reserved) && Objects.equals(title, other.title);
}

}
