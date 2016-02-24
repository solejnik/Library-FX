package com.capgemini.starterkit.solejnik.booksFX.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.capgemini.starterkit.solejnik.booksFX.dataprovider.DataProvider;
import com.capgemini.starterkit.solejnik.booksFX.dataprovider.data.BookTo;
import com.capgemini.starterkit.solejnik.booksFX.model.BookSearchModel;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BookSearchController {
	private static final Logger LOG = Logger.getLogger(BookSearchController.class);
	@FXML
	ResourceBundle resources;
	@FXML
	URL location;
	@FXML
	TextField titlePrefixField;
	@FXML
	Button searchButton;
	@FXML
	Button addButton;
	@FXML
	TableView<BookTo> resultTable;
	@FXML
	TableColumn<BookTo, Long> idColumn;
	@FXML
	TableColumn<BookTo, String> titleColumn;

	private final DataProvider dataProvider = DataProvider.INSTANCE;
	private final BookSearchModel model = new BookSearchModel();

	public BookSearchController() {
		LOG.debug("Constructor: titleField: " + titlePrefixField);
	}

	@FXML
	private void initialize() {
		LOG.debug("Initialize: titleField: " + titlePrefixField);
		initializeTableResult();
		titlePrefixField.textProperty().bindBidirectional(model.getTitlePrefixProperty());
		resultTable.itemsProperty().bind(model.getResultProperty());
		searchButton.disableProperty().bind(model.getTitlePrefixProperty().isEmpty());
	}

	@FXML
	public void searchButtonAction(ActionEvent event) {
		LOG.debug("search() button clicked");
		searchAction();
	}

	@FXML
	public void deletePopupMenuAction(ActionEvent event) {
		LOG.debug("Delete from context menu clicked()");
		BookTo selectedItem = resultTable.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			Runnable backgroundTask = new Runnable() {
				@Override
				public void run() {
					dataProvider.deleteBook(selectedItem.getId());
					LOG.debug("Book was deleted");
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							searchAction();
						}
					});
				}
			};
			new Thread(backgroundTask).start();
		}
	}

	@FXML
	public void addButtonAction(ActionEvent event) {
		LOG.debug("Add book button was clicked()");
		openAddBookDialog();
	}

	private void openAddBookDialog() {
		LOG.debug("Add book modal is opening");
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("/com/capgemini/starterkit/solejnik/booksFX/view/Add-book.fxml"));
		Parent root = null;
		Stage stage = new Stage();
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		stage.setScene(new Scene(root));
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(addButton.getScene().getWindow());
		stage.setTitle("New Book");
		stage.showAndWait();
		searchAction();
		LOG.debug("Add book modal is closed");
	}

	private void initializeTableResult() {
		idColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Long>(cellData.getValue().getId()));
		titleColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTitle()));
	}

	private void searchAction() {
		Runnable backgroundTask = new Runnable() {
			@Override
			public void run() {
				LOG.debug("Run new thread for search books");
				String titlePrefix = model.getTitlePrefix() != null ? model.getTitlePrefix() : " ";
				Collection<BookTo> result = dataProvider.findBooks(titlePrefix);
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						model.setResult(new ArrayList<BookTo>(result));
						resultTable.getSortOrder().clear();
					}
				});
			}
		};
		new Thread(backgroundTask).start();
	}
}
