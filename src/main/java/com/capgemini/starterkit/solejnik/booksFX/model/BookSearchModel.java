package com.capgemini.starterkit.solejnik.booksFX.model;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.starterkit.solejnik.booksFX.dataprovider.data.BookTo;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class BookSearchModel {
	private final StringProperty titlePrefix = new SimpleStringProperty();
	private final ListProperty<BookTo> result = new SimpleListProperty<BookTo>(
			FXCollections.observableList(new ArrayList<BookTo>()));

	public StringProperty getTitlePrefixProperty() {
		return titlePrefix;
	}

	public final String getTitlePrefix() {
		return titlePrefix.get();
	}

	public final void setTitlePrefix(String prefix) {
		titlePrefix.set(prefix);
	}

	public ListProperty<BookTo> getResultProperty() {
		return result;
	}

	public final List<BookTo> getResult() {
		return result.get();
	}

	public final void setResult(List<BookTo> books) {
		result.setAll(books);
	}

}
