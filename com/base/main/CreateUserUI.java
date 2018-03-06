package com.base.main;

import java.io.IOException;
import java.util.List;

import com.base.data.DairyFarmerClient;
import com.base.data.models.Event;
import com.base.data.models.User;
import com.base.util.Time;
import com.base.util.Utilities;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateUserUI
{
	@FXML private Button btnCreateUser;
	@FXML private Button btnCancel;
	@FXML private TextField txtUsername;
	@FXML private PasswordField txtPassword;
	@FXML private CheckBox chckIsAdmin;
	private Image farmer = new Image("file:res/assets/farmer.png");
	@FXML private ImageView imgFarmer;

	private boolean done = false;

	private DairyFarmerClient client = new DairyFarmerClient();

	/**
	 * Where the application launches from
	 * @param clientParam DairyFarmer client to keep track of current data
	 * @throws IOException If an input or output exception occurred
	 */
	public CreateUserUI(DairyFarmerClient clientParam) throws IOException
	{
		FXMLLoader load = new FXMLLoader(getClass().getResource("/CreateUserUI.fxml")); // You may have to change the path in order to access CalendarUI.fxml
		load.setController(this); // Makes it so that you can control the UI using this class

		Parent root = (Parent) load.load();
		Scene scene = new Scene(root);

		imgFarmer.setImage(farmer);

		// Start the application
		client = clientParam;
		Stage stage = new Stage();
		stage.setTitle("Create User");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		initializeListeners(stage);
		stage.setOnCloseRequest(e -> // In case the user exits without pressing cancel
		{
			done = true;
		});
	}

	/**
	 * Sets the functions that run when the user clicks on certain objects
	 */
	private void initializeListeners(Stage stage)
	{
		// Creates the user
		btnCreateUser.setOnAction(e ->
		{
			String username = txtUsername.getText();
			String password = txtPassword.getText();
			if(checkInputs(username, password))
			{
				client.createUser(username, password, chckIsAdmin.isSelected());
				showDialogBox("User Created", "User " + username + " Successfully Created!", "Creation was a success", AlertType.INFORMATION);
				done = true;
				stage.close();
			}
		});

		// Cancels the user creation
		btnCancel.setOnAction(e ->
		{
			done = true;
			stage.close();
		});
	}

	/**
	 * Validates user input
	 * @param username String representing username
	 * @param password String representing user password
	 * @return true if valid user input
	 */
	private boolean checkInputs(String username, String password)
	{
		if(username.isEmpty())
		{
			showDialogBox("Username Empty", "Username Field is Empty", "Please enter a username", AlertType.ERROR);
			txtUsername.requestFocus();
			return false;
		}
		if(password.isEmpty())
		{
			showDialogBox("Password Empty", "Password Field is Empty", "Please enter a password", AlertType.ERROR);
			txtPassword.requestFocus();
			return false;
		}
		if(Utilities.userExists(client.getUsers(), username))
		{
			showDialogBox("Username Taken", "This Username is Already Taken", "Please try again", AlertType.ERROR);
			txtUsername.requestFocus();
			return false;
		}
		return true;
	}

	/**
	 *
	 * http://code.makery.ch/blog/javafx-dialogs-official/
	 * @param title String representing title of dialog box
	 * @param header String representing head of dialog box
	 * @param content Content of dialog box
	 * @param type Type of dialog box
	 */
	public void showDialogBox(String title, String header, String content, AlertType type)
	{
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);

		alert.showAndWait();
	}

	/**
	 * Returns if the window is finished
	 * @return if the window is finished
	 */
	public boolean getWindowFinished()
	{
		return done;
	}
}
