package com.capgemini.starterkit.solejnik.booksFX.dataprovider.data;

import java.util.List;

public class BookTo {
	private Long id;
	private String title;
	private List<AuthorTo> authors;

	public BookTo() {
	}

	public BookTo(Long id, String title, List<AuthorTo> authors) {
		this.id = id;
		this.title = title;
		this.authors = authors;
	}

	public BookTo(String title, List<AuthorTo> authors) {
		this.title = title;
		this.authors = authors;
	}

	public BookTo(Long id, String title) {
		this.id = id;
		this.title = title;
	}

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

	public List<AuthorTo> getAuthors() {
		return authors;
	}

	public void setAuthors(List<AuthorTo> authors) {
		this.authors = authors;
	}

	@Override
	public String toString() {
		return "BookTo [id=" + id + ", title=" + title + ", authors=" + authors + "]";
	}
}
