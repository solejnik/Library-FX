package com.capgemini.starterkit.solejnik.booksFX.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AddBookModel {
	private final StringProperty bookTitle = new SimpleStringProperty();
	private final StringProperty authorFirstName = new SimpleStringProperty();
	private final StringProperty authorLastName = new SimpleStringProperty();

	public StringProperty bookTitle() {
		return bookTitle;
	}

	public final String getBookTitle() {
		return bookTitle.get();
	}

	public final void setBookTitle(String newTitle) {
		bookTitle.set(newTitle);
	}

	public StringProperty authorFirstName() {
		return authorFirstName;
	}

	public final String getAuthorFirstName() {
		return authorFirstName.get();
	}

	public final void setAuthorFirstName(String newFirstName) {
		authorFirstName.set(newFirstName);
	}

	public StringProperty authorLastName() {
		return authorLastName;
	}

	public final String getAuthorLastName() {
		return authorLastName.get();
	}

	public final void setAuthorLastName(String newLastName) {
		authorLastName.set(newLastName);
	}

}
