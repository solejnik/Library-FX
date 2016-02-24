package com.capgemini.starterkit.solejnik.booksFX.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.capgemini.starterkit.solejnik.booksFX.dataprovider.DataProvider;
import com.capgemini.starterkit.solejnik.booksFX.dataprovider.data.AuthorTo;
import com.capgemini.starterkit.solejnik.booksFX.dataprovider.data.BookTo;
import com.capgemini.starterkit.solejnik.booksFX.model.AddBookModel;

import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddBookController {
	private final static Logger LOG = Logger.getLogger(AddBookController.class);
	@FXML
	ResourceBundle resources;
	@FXML
	URL location;
	@FXML
	TextField bookTitle;
	@FXML
	TextField authorFirstName;
	@FXML
	TextField authorLastName;
	@FXML
	Button okButton;
	@FXML
	Button cancelButton;

	private BookTo book;
	private final AddBookModel model = new AddBookModel();
	private DataProvider provider = DataProvider.INSTANCE;
	private Stage stage;

	@FXML
	public void okButonAction(ActionEvent event) {
		LOG.debug("Ok button was clicked");
		Runnable backgroundTask = new Runnable() {
			@Override
			public void run() {
				LOG.debug("New thread for save new book is run");
				book.setTitle(model.getBookTitle());
				book.getAuthors().add(new AuthorTo(model.getAuthorFirstName(), model.getAuthorLastName()));
				provider.saveBook(book);
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						closeStage();
					}
				});
			}
		};
		new Thread(backgroundTask).start();
	}

	@FXML
	public void cancelButtonAction(ActionEvent event) {
		LOG.debug("Cancel button was clicked");
		closeStage();
	}

	@FXML
	private void initialize() {
		LOG.debug("Initialize()");
		bookTitle.textProperty().bindBidirectional(model.bookTitle());
		authorFirstName.textProperty().bindBidirectional(model.authorFirstName());
		authorLastName.textProperty().bindBidirectional(model.authorLastName());
		BooleanBinding booleanBind = model.authorLastName().isEmpty().or((model.authorFirstName().isEmpty()))
				.or(model.bookTitle().isEmpty());
		okButton.disableProperty().bind(booleanBind);
		book = new BookTo("", new ArrayList<AuthorTo>());
	}

	private void closeStage() {
		LOG.debug("Stage is closing");
		stage = (Stage) okButton.getScene().getWindow();
		stage.close();
	}

}
