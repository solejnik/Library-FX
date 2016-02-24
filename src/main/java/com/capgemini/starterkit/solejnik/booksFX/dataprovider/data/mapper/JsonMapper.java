package com.capgemini.starterkit.solejnik.booksFX.dataprovider.data.mapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.capgemini.starterkit.solejnik.booksFX.dataprovider.data.BookTo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapper {
	private ObjectMapper mapper = new ObjectMapper();

	public Collection<BookTo> map(String jSonString) {
		Collection<BookTo> booksList = new ArrayList<BookTo>();
		try {
			booksList = Arrays.asList(mapper.readValue(jSonString, BookTo[].class));
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return booksList;
	}

	public String map(BookTo book) {
		try {
			return mapper.writeValueAsString(book);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
