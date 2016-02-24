package com.capgemini.starterkit.solejnik.booksFX.dataprovider;

import java.util.Collection;

import com.capgemini.starterkit.solejnik.booksFX.dataprovider.data.BookTo;
import com.capgemini.starterkit.solejnik.booksFX.dataprovider.impl.DataProviderImpl;

public interface DataProvider {
	DataProvider INSTANCE = new DataProviderImpl();

	Collection<BookTo> findBooks(String titlePrefix);

	void deleteBook(Long id);

	void saveBook(BookTo book);
}
