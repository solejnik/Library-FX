package com.capgemini.starterkit.solejnik.booksFX.dataprovider.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.capgemini.starterkit.solejnik.booksFX.dataprovider.DataProvider;
import com.capgemini.starterkit.solejnik.booksFX.dataprovider.data.BookTo;
import com.capgemini.starterkit.solejnik.booksFX.dataprovider.data.http.MyHttpClient;
import com.capgemini.starterkit.solejnik.booksFX.dataprovider.data.mapper.JsonMapper;

public class DataProviderImpl implements DataProvider {
	private final static Logger LOG = Logger.getLogger(DataProviderImpl.class);
	private JsonMapper mapper = new JsonMapper();
	private MyHttpClient httpClient = new MyHttpClient();

	public Collection<BookTo> findBooks(String titlePrefix) {
		LOG.debug("Searching books");
		titlePrefix = titlePrefix.equals(" ") ? "" : titlePrefix;
		String jsonInput = "";
		Collection<BookTo> booksList = new ArrayList<BookTo>();
		jsonInput = httpClient.get(titlePrefix);
		booksList = mapper.map(jsonInput);
		return booksList;
	}

	public void deleteBook(Long id) {
		LOG.debug("Deleting a book");
		httpClient.delete(id);
	}

	public void saveBook(BookTo book) {
		LOG.debug("Saving a book");
		String bookJson = mapper.map(book);
		httpClient.post(bookJson);
	}

}
