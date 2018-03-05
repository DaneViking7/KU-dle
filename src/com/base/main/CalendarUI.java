package com.base.main;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.TextArea;



import com.base.data.DairyFarmerClient;
import com.base.data.models.Event;
import com.base.data.models.User;
import com.base.main.CreateEventUI;
import com.base.main.CreateUserUI;

import com.base.util.Time;
import com.base.util.Utilities;


public class CalendarUI extends Application
{
	@FXML SplitPane paneMain;

	@FXML private Label lblCurrentMonth; // Shows what month is currently showing
	@FXML private Label lblSelectedDate; // Shows what day is currently selected
	@FXML private Label lblSelectedDate2;
	private Image arrow = new Image("file:res/assets/arrow.png");
	private Image cowGif = new Image("file:res/assets/cow.gif");
	@FXML private ImageView btnMonthLeft;
	@FXML private ImageView btnMonthRight;

	@FXML private CheckBox chckMultiDay;
	@FXML private CheckBox chckTwentyfour;
	@FXML private Label lblAdminDisable;
	@FXML private Button btnCreateEvent;
	@FXML private Button btnCreateUser;

	@FXML private TextArea lblEventName;
	@FXML private TextArea lblEventDesc;

	// Calendar boxes
	private Label[][] calendarDateLabels = new Label[6][7];
	@FXML private Label lblDay56;
	@FXML private Label lblDay55;
	@FXML private Label lblDay54;
	@FXML private Label lblDay53;
	@FXML private Label lblDay52;
	@FXML private Label lblDay51;
	@FXML private Label lblDay50;
	@FXML private Label lblDay46;
	@FXML private Label lblDay45;
	@FXML private Label lblDay44;
	@FXML private Label lblDay43;
	@FXML private Label lblDay42;
	@FXML private Label lblDay41;
	@FXML private Label lblDay40;
	@FXML private Label lblDay36;
	@FXML private Label lblDay35;
	@FXML private Label lblDay34;
	@FXML private Label lblDay33;
	@FXML private Label lblDay32;
	@FXML private Label lblDay31;
	@FXML private Label lblDay30;
	@FXML private Label lblDay26;
	@FXML private Label lblDay25;
	@FXML private Label lblDay24;
	@FXML private Label lblDay23;
	@FXML private Label lblDay22;
	@FXML private Label lblDay21;
	@FXML private Label lblDay20;
	@FXML private Label lblDay16;
	@FXML private Label lblDay15;
	@FXML private Label lblDay14;
	@FXML private Label lblDay13;
	@FXML private Label lblDay12;
	@FXML private Label lblDay11;
	@FXML private Label lblDay10;
	@FXML private Label lblDay06;
	@FXML private Label lblDay05;
	@FXML private Label lblDay04;
	@FXML private Label lblDay03;
	@FXML private Label lblDay02;
	@FXML private Label lblDay01;
	@FXML private Label lblDay00;

	private Rectangle[][] calendarDateBoxes = new Rectangle[6][7];
	@FXML private Rectangle boxDay56;
	@FXML private Rectangle boxDay55;
	@FXML private Rectangle boxDay54;
	@FXML private Rectangle boxDay53;
	@FXML private Rectangle boxDay52;
	@FXML private Rectangle boxDay51;
	@FXML private Rectangle boxDay50;
	@FXML private Rectangle boxDay46;
	@FXML private Rectangle boxDay45;
	@FXML private Rectangle boxDay44;
	@FXML private Rectangle boxDay43;
	@FXML private Rectangle boxDay42;
	@FXML private Rectangle boxDay41;
	@FXML private Rectangle boxDay40;
	@FXML private Rectangle boxDay36;
	@FXML private Rectangle boxDay35;
	@FXML private Rectangle boxDay34;
	@FXML private Rectangle boxDay33;
	@FXML private Rectangle boxDay32;
	@FXML private Rectangle boxDay31;
	@FXML private Rectangle boxDay30;
	@FXML private Rectangle boxDay26;
	@FXML private Rectangle boxDay25;
	@FXML private Rectangle boxDay24;
	@FXML private Rectangle boxDay23;
	@FXML private Rectangle boxDay22;
	@FXML private Rectangle boxDay21;
	@FXML private Rectangle boxDay20;
	@FXML private Rectangle boxDay16;
	@FXML private Rectangle boxDay15;
	@FXML private Rectangle boxDay14;
	@FXML private Rectangle boxDay13;
	@FXML private Rectangle boxDay12;
	@FXML private Rectangle boxDay11;
	@FXML private Rectangle boxDay10;
	@FXML private Rectangle boxDay06;
	@FXML private Rectangle boxDay05;
	@FXML private Rectangle boxDay04;
	@FXML private Rectangle boxDay03;
	@FXML private Rectangle boxDay02;
	@FXML private Rectangle boxDay01;
	@FXML private Rectangle boxDay00;

	// Color palettes
	@FXML private Color clrDeactivated = Color.web("#5a5a5a");
	@FXML private Color clrActivated = Color.web("#d9d9d9");
	@FXML private Color clrCurrDate = Color.web("#ffafaf");
	@FXML private Color clrSelDate = Color.web("#a5e6fb");
	@FXML private Color clrEvents = Color.web("#7efb91");

	@FXML private ListView<Event> lstViewAvailable; //Initialization of the ListView thats waiting for approval
	@FXML private ListView<Event> lstViewAccepted; //Initialization of the ListView that is already accepted
	@FXML private ListView<String> lstViewTimes; //Initialization of the ListView that is already accepted

	// For displays
	private String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	private String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

	LocalDate currentDateLD = LocalDate.of(0, 1, 1); // LocalDate version of current date (To be used in interfacing with the client)
	static LocalDate selectedDateLD = LocalDate.of(0, 1, 1); // LocalDate version of selected date
	private int[] currentDateArr = {0, 0, 0}; // (Month/Day/Year)
	private int[] selectedDateArr = {0, 0, 0};
	private int selDateRow = 0; // Row index of selected date
	private int selDateDay = 0; // Day index of selected date
	static ArrayList<Pair<Integer, Integer>> multiDayArr = new ArrayList<Pair<Integer, Integer>>();
	

	static DairyFarmerClient client = new DairyFarmerClient();
	private User user;

	private String inputUsername;
	private String inputPassword;
	private boolean loginDetails = false;
	public static boolean multiDayMode;

	/**
	 * Where the application launches from
	 * @param args What is passed in (don't worry about this)
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Sets the stage and initializes the program
	 * @param stage The stage of the application
	 */
	public void start(Stage stage) throws Exception
	{
		FXMLLoader load = new FXMLLoader(getClass().getResource("/CalendarUI.fxml")); // You may have to change the path in order to access CalendarUI.fxml
		load.setController(this); // Makes it so that you can control the UI using this class
		client.initEvents(); // Loads events from a file
		client.initUsers(); // Loads users from a file

		while(!loginDetails)  //only reach the calendar UI if the username exists and the password is correct
		{
			showLoginPage();

			if(Utilities.userExists(client.getUsers(), inputUsername)) //if the username exists
			{
				if(inputPassword.equals(client.getUser(inputUsername).getPassword()) ) //if the password is correct
				{
					loginDetails = true;
				}
				else
				{
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("Incorrect Password");
					alert.setContentText("Please try again");

					alert.showAndWait();
				}
			}
			else
			{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Username does not exist");
				alert.setContentText("Please try again");

				alert.showAndWait();
			}
		}

		user = client.getUser(inputUsername);
		if(user.getAdmin())
		{
			Platform.runLater(() ->
			{
				lblAdminDisable.setVisible(false);
				btnCreateEvent.setDisable(false);
				btnCreateUser.setDisable(false);
			});
		}

		Parent root = (Parent) load.load();
		Scene scene = new Scene(root);

		//Arrow Pictures
		btnMonthLeft.setImage(arrow);
		btnMonthRight.setImage(arrow);

		// Start the application
		stage.setTitle("Calendar");
		stage.setScene(scene);
		stage.setResizable(true);
		stage.show();

		setMultiDayMode(false);
		Time.setTwentyFourMode(false);
		lblEventName.setWrapText(true);
		lblEventDesc.setWrapText(true);
		setupCalendarBoxes(); // Populates arrays
		setCurrDate(); // Sets the current date
		showChangedMonth(); // Updates the UI
		initializeListeners(); // Initializes listeners...
		updateLists(); // Initializes the event lists
		listViewApprovePushed();
		listViewAcceptedPushed(lstViewAvailable);
		listViewAcceptedPushed(lstViewAccepted);

		// Happens when user tries to exit
		stage.setOnCloseRequest(e ->
		{
			try {
				client.saveEvents(); // Save to file
				client.saveUsers(); // Save to file
			}
			catch (IOException e1)
			{
				e1.printStackTrace();
			}
		});
	}

	/**
	 * Show the login page before user open calendar
	 */
	private void showLoginPage()
	{
		//Obtained from : http://code.makery.ch/blog/javafx-dialogs-official/
		// Create the custom dialog.
		Dialog<Pair<String, String>> dialog = new Dialog<>(); //Creates the dialog for the login portal
		dialog.setTitle("Login Portal"); //Naming Scheme
		dialog.setHeaderText("Calendar Login"); //Naming Scheme

		// Set the icon (must be included in the project).
		ImageView ivCow = new ImageView();
		ivCow.setImage(cowGif);
		dialog.setGraphic(ivCow); //THE LORD AND SAVIOR OUR COW!

		// Set the button types.
		ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE); //Options for Buttons
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL); // Cancel Button

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		// Username Field for the Fields
		TextField username = new TextField();
		username.setPromptText("Username");
		PasswordField password = new PasswordField();
		password.setPromptText("Password");

		grid.add(new Label("Username:"), 0, 0);
		grid.add(username, 1, 0);
		grid.add(new Label("Password:"), 0, 1);
		grid.add(password, 1, 1);

		// Enable/Disable login button depending on whether a username was entered.
		Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(true);


		// Do some validation (using the Java 8 lambda syntax).
		username.textProperty().addListener((observable, oldValue, newValue) ->
		{
			loginButton.setDisable(newValue.trim().isEmpty());
		});

		dialog.getDialogPane().setContent(grid);

		// Request focus on the username field by default.
		Platform.runLater(() -> username.requestFocus());

		// Convert the result to a username-password-pair when the login button is clicked.
		dialog.setResultConverter(dialogButton ->
		{
		    if (dialogButton == loginButtonType)
		    {
		        return new Pair<>(username.getText(), password.getText());
		    }
		    if(dialogButton == ButtonType.CANCEL)
		    {
		    	//Platform.exit();
		    	System.exit(0);
		    }
		    return null;
		});

		dialog.showAndWait();

		inputUsername = username.getText();
		inputPassword = password.getText();
	}

	/**
	 * Initializes and sets the current date
	 */
	private void setCurrDate()
	{
		String temp = LocalDateTime.now().toString(); // Gets the current date
		currentDateArr[2] = Integer.parseInt(temp.substring(0, 4)); // Year
		currentDateArr[1] = Integer.parseInt(temp.substring(8, 10)); // Day
		currentDateArr[0] = Integer.parseInt(temp.substring(5, 7)); // Month
		for(int i = 0; i < 3; i++)
		{
			selectedDateArr[i] = currentDateArr[i]; // Makes the selected date the current date at the initialization of the program

			selectedDateArr[i] = currentDateArr[i]; // Makes the selected date the current date at the initialization of the program
		}
		currentDateLD = LocalDate.now();
		selectedDateLD = LocalDate.now();
	}

	/**
			selectedDateArr[i] = currentDateArr[i]; // Makes the selected date the current date at the initialization of the program

			selectedDateArr[i] = currentDateArr[i]; // Makes the selected date the current date at the initialization of the program

	 * Populates the calendar box arrays with the FXML labels and boxes
	 */
	private void setupCalendarBoxes()
	{
		calendarDateLabels[0][0] = lblDay00;
		calendarDateLabels[0][1] = lblDay01;
		calendarDateLabels[0][2] = lblDay02;
		calendarDateLabels[0][3] = lblDay03;
		calendarDateLabels[0][4] = lblDay04;
		calendarDateLabels[0][5] = lblDay05;
		calendarDateLabels[0][6] = lblDay06;
		calendarDateLabels[1][0] = lblDay10;
		calendarDateLabels[1][1] = lblDay11;
		calendarDateLabels[1][2] = lblDay12;
		calendarDateLabels[1][3] = lblDay13;
		calendarDateLabels[1][4] = lblDay14;
		calendarDateLabels[1][5] = lblDay15;
		calendarDateLabels[1][6] = lblDay16;
		calendarDateLabels[2][0] = lblDay20;
		calendarDateLabels[2][1] = lblDay21;
		calendarDateLabels[2][2] = lblDay22;
		calendarDateLabels[2][3] = lblDay23;
		calendarDateLabels[2][4] = lblDay24;
		calendarDateLabels[2][5] = lblDay25;
		calendarDateLabels[2][6] = lblDay26;
		calendarDateLabels[3][0] = lblDay30;
		calendarDateLabels[3][1] = lblDay31;
		calendarDateLabels[3][2] = lblDay32;
		calendarDateLabels[3][3] = lblDay33;
		calendarDateLabels[3][4] = lblDay34;
		calendarDateLabels[3][5] = lblDay35;
		calendarDateLabels[3][6] = lblDay36;
		calendarDateLabels[4][0] = lblDay40;
		calendarDateLabels[4][1] = lblDay41;
		calendarDateLabels[4][2] = lblDay42;
		calendarDateLabels[4][3] = lblDay43;
		calendarDateLabels[4][4] = lblDay44;
		calendarDateLabels[4][5] = lblDay45;
		calendarDateLabels[4][6] = lblDay46;
		calendarDateLabels[5][0] = lblDay50;
		calendarDateLabels[5][1] = lblDay51;
		calendarDateLabels[5][2] = lblDay52;
		calendarDateLabels[5][3] = lblDay53;
		calendarDateLabels[5][4] = lblDay54;
		calendarDateLabels[5][5] = lblDay55;
		calendarDateLabels[5][6] = lblDay56;

		calendarDateBoxes[0][0] = boxDay00;
		calendarDateBoxes[0][1] = boxDay01;
		calendarDateBoxes[0][2] = boxDay02;
		calendarDateBoxes[0][3] = boxDay03;
		calendarDateBoxes[0][4] = boxDay04;
		calendarDateBoxes[0][5] = boxDay05;
		calendarDateBoxes[0][6] = boxDay06;
		calendarDateBoxes[1][0] = boxDay10;
		calendarDateBoxes[1][1] = boxDay11;
		calendarDateBoxes[1][2] = boxDay12;
		calendarDateBoxes[1][3] = boxDay13;
		calendarDateBoxes[1][4] = boxDay14;
		calendarDateBoxes[1][5] = boxDay15;
		calendarDateBoxes[1][6] = boxDay16;
		calendarDateBoxes[2][0] = boxDay20;
		calendarDateBoxes[2][1] = boxDay21;
		calendarDateBoxes[2][2] = boxDay22;
		calendarDateBoxes[2][3] = boxDay23;
		calendarDateBoxes[2][4] = boxDay24;
		calendarDateBoxes[2][5] = boxDay25;
		calendarDateBoxes[2][6] = boxDay26;
		calendarDateBoxes[3][0] = boxDay30;
		calendarDateBoxes[3][1] = boxDay31;
		calendarDateBoxes[3][2] = boxDay32;
		calendarDateBoxes[3][3] = boxDay33;
		calendarDateBoxes[3][4] = boxDay34;
		calendarDateBoxes[3][5] = boxDay35;
		calendarDateBoxes[3][6] = boxDay36;
		calendarDateBoxes[4][0] = boxDay40;
		calendarDateBoxes[4][1] = boxDay41;
		calendarDateBoxes[4][2] = boxDay42;
		calendarDateBoxes[4][3] = boxDay43;
		calendarDateBoxes[4][4] = boxDay44;
		calendarDateBoxes[4][5] = boxDay45;
		calendarDateBoxes[4][6] = boxDay46;
		calendarDateBoxes[5][0] = boxDay50;
		calendarDateBoxes[5][1] = boxDay51;
		calendarDateBoxes[5][2] = boxDay52;
		calendarDateBoxes[5][3] = boxDay53;
		calendarDateBoxes[5][4] = boxDay54;
		calendarDateBoxes[5][5] = boxDay55;
		calendarDateBoxes[5][6] = boxDay56;
	}

	/**
	 * Populates the ListViews
	 */
	private void updateLists()
	{
		Platform.runLater(() ->
		{
			// Start by reseting the lists
			lstViewAccepted.getItems().removeAll(lstViewAccepted.getItems());
			lstViewAvailable.getItems().removeAll(lstViewAvailable.getItems());
			lstViewTimes.getItems().removeAll(lstViewTimes.getItems());
			lblEventName.setText("");
			lblEventDesc.setText("");

			// Fill the lists
			List<Event> events = client.getEvents(selectedDateLD);
			if(events != null)
			{
				for(Event e : events)
				{
					if(e.attendeeExists(user)) // If the user has already accepted
						lstViewAccepted.getItems().add(e);
					else // If the user has yet to accept
						lstViewAvailable.getItems().add(e);
				}
			}
		});
	}

	/**
	 * Opens the window for the user to accept or reject the event
	 */
	private void listViewApprovePushed()
	{
		Platform.runLater(() -> {
			Event event = lstViewAvailable.getSelectionModel().getSelectedItem();
			if(event != null)
			{
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation Event");
				alert.setHeaderText("Event Actions");
				alert.setContentText("Choose your option.");

				ButtonType buttonAccept = new ButtonType("Accept Event");
				ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

				alert.getButtonTypes().setAll(buttonAccept, buttonTypeCancel);

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == buttonAccept)
				{
					try {
						openApproveEvent(event);
					}
					catch (Exception e1)
					{
						e1.printStackTrace();
					}
				}
			}
		});
	}


	/**
	 * Displays the event information at the bottom
	 */
	private void listViewAcceptedPushed(ListView<Event> lstView)
	{
		Platform.runLater(() -> {
			lstView.setOnMouseClicked(e ->
			{
				Event currentEvent = lstView.getSelectionModel().getSelectedItem();
				if(currentEvent != null)
				{
					lblEventName.setText(currentEvent.getEventName());
					lblEventDesc.setText(currentEvent.getDescription());
					lstViewTimes.getItems().removeAll(lstViewTimes.getItems());;
					List<Time> listofTimes = currentEvent.getTimes();

					if(currentEvent!=null)
					{
						String temp;
						for (Time time : listofTimes)
						{
							temp = "";
							temp += time.toString() + " :";
							List<User> users = time.getAttendees();
							/*for(User m : users)
							{
								System.out.print(m.getName());
								System.out.print('\n');
							}*/
							for(User u : users)
							{
								temp += " " + u.getName();
							}
							lstViewTimes.getItems().add(temp);
						}
					}
					if(lstView.equals(lstViewAvailable))
						listViewApprovePushed();
				}
			});
		});
	}


	/**
	 * Initializes the buttons and tells them what to do when they are clicked on
	 */
	private void initializeListeners()
	{
		btnCreateEvent.setOnAction(e ->
		{
			try {
				if(canCreateEvent())
					openCreateEvent(); // Opens the create event window
				else
					showDialogBox("Cannot Create Event", "Cannot create event on this day", "Creating events on this day is disallowed", AlertType.ERROR);
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
		});

		// Opens the create a user function
		btnCreateUser.setOnAction(e ->
		{
			try {
				openCreateUser(); // Opens the create user window
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
		});

		// Goes back a month
		btnMonthLeft.setOnMouseClicked(e ->
		{
			changeMonth(true, 1);
		});

		// Goes forward a month
		btnMonthRight.setOnMouseClicked(e ->
		{
			changeMonth(false, 1);
		});
		

		// Sets all of the boxes to change the selected date when they are clicked on
		for(int rowCal = 0; rowCal < 6; rowCal++)
		{
			for(int dayCal = 0; dayCal < 7; dayCal++)
			{
				// These must be final because in order for the functions to run correctly they must use final variables
				final int row = rowCal;
				final int day = dayCal;
				calendarDateBoxes[rowCal][dayCal].setOnMouseClicked(e ->
				{
					if(!multiDayMode)
					{
						changeDate(row, day);
					}
					else
					{	
						Pair<Integer, Integer> tempPair = new Pair<>(row, day);
						multiDayArr.add(tempPair);
						
						calendarDateBoxes[row][day].setFill(clrSelDate);
					}
				});
				calendarDateLabels[rowCal][dayCal].setOnMouseClicked(e ->
				{
					if(!multiDayMode)
					{
						changeDate(row, day);
					}
				});
			}
		}

		// Turns on/off 24-hour mode
		chckTwentyfour.setOnAction(e ->
		{
			Time.setTwentyFourMode(chckTwentyfour.isSelected());
			updateLists();
		});
		
		// Turns on/off multi-day events
		chckMultiDay.setOnAction(e ->
		{
			setMultiDayMode(chckMultiDay.isSelected());
		});
	}
	
	/**
	 *  sets multi-day mode to true or false: true if multi-day is enabled, false otherwise
	 * @param multiDay, boolean
	 */
	private void setMultiDayMode(boolean multiDay)
	{
		multiDayMode = multiDay;
	}

	/**
	 * Checks to see if user can create an event
	 * @return true if you can create an event
	 */
	private boolean canCreateEvent()
	{
		if(selectedDateArr[0] == 12 && selectedDateArr[1] == 25) // Christmas
			return false;
		else if(selectedDateArr[0] == 1 && selectedDateArr[1] == 1) // New Year's Day
			return false;
		else if(selectedDateArr[0] == 7 && selectedDateArr[1] == 4) // Independence Day
			return false;
		else if(currentDateLD.compareTo(selectedDateLD) > 0)
			return false;
		else
			return true;
	}

	/**
	 * Opens the create event window
	 * @throws IOException In case CreateEventUI screws up
	 * @throws InterruptedException For the thread
	 */
	private void openCreateEvent() throws IOException, InterruptedException
	{
		paneMain.setDisable(true); // Disables the Calendar
		CreateEventUI event = new CreateEventUI(client, user, selectedDateLD);
		// Obtained (in part) from https://stackoverflow.com/questions/24104313/how-to-delay-in-java by Boris the Spider
		final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	    executorService.scheduleAtFixedRate(new Runnable() {
	        @Override
	        public void run()
	        {
	            if(event.getEventFinished())
	            {
	            	updateLists();
	            	paneMain.setDisable(false);
	            	Thread.currentThread().stop();
	            }
	        }
	    }, 0, 1, TimeUnit.SECONDS);
	}

	/**
	 * Opens the create user window
	 * @throws IOException In case CreateUserUI screws up
	 * @throws InterruptedException For the thread
	 */
	private void openCreateUser() throws IOException, InterruptedException
	{
		paneMain.setDisable(true); // Disables the Calendar
		CreateUserUI event = new CreateUserUI(client);
		// Obtained (in part) from https://stackoverflow.com/questions/24104313/how-to-delay-in-java by Boris the Spider
		final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	    executorService.scheduleAtFixedRate(new Runnable() {
	        @Override
	        public void run()
	        {
	            if(event.getWindowFinished())
	            {
	            	updateLists();
	            	paneMain.setDisable(false);
	            	Thread.currentThread().stop();
	            }
	        }
	    }, 0, 1, TimeUnit.SECONDS);
	}

	/**
	 * Opens the approve event window
	 * @throws IOException In case CreateEventUI screws up
	 * @throws InterruptedException For the thread
	 */
	private void openApproveEvent(Event event) throws IOException, InterruptedException
	{
		paneMain.setDisable(true); // Disables the Calendar
		AcceptEventUI accept = new AcceptEventUI(user, event);
		// Obtained (in part) from https://stackoverflow.com/questions/24104313/how-to-delay-in-java by Boris the Spider
		final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	    executorService.scheduleAtFixedRate(new Runnable() {
	        @Override
	        public void run()
	        {
	            if(accept.getWindowFinished())
	            {
	            	updateLists();
	            	paneMain.setDisable(false);
	            	Thread.currentThread().stop();
	            }
	        }   
	    }, 0, 1, TimeUnit.SECONDS);
	}

	/**
	 * Goes forward or backward a month on the calendar pane
	 * @param left If true, we go back a month, if false, we go forward a month
	 * @param day The day that is to be selected
	 */
	private void changeMonth(boolean left, int day)
	{
		// Go back a month
		if(left)
		{
			selectedDateArr[0]--;
			if(selectedDateArr[0] == 0) // Wrap around
			{
				selectedDateArr[0] = 12; // Go to December
				selectedDateArr[2]--; // Go back a year
			}
			selectedDateArr[1] = day;
			selectedDateLD = LocalDate.of(selectedDateArr[2], selectedDateArr[0], selectedDateArr[1]); // Update selectedDateLD
			showChangedMonth();
		}
		else // Go forward a month
		{
			selectedDateArr[0]++;
			if(selectedDateArr[0] == 13) // Wrap around
			{
				selectedDateArr[0] = 1; // Go to January
				selectedDateArr[2]++; // Go forward a year
			}
			selectedDateArr[1] = day;
			selectedDateLD = LocalDate.of(selectedDateArr[2], selectedDateArr[0], selectedDateArr[1]); // Update selectedDateLD
			showChangedMonth();
		}
		updateLists();
	}

	/**
	 * Changes the selected date to the actual selected date
	 * @param row The row of the new date
	 * @param day The day of the week of the new date
	 */
	private void changeDate(int row, int day)
	{
		selectedDateArr[1] = Integer.parseInt(calendarDateLabels[row][day].getText()); // Get selected day
		if(calendarDateBoxes[row][day].getFill().equals(clrDeactivated)) // If the day is not in the current month...
		{
			// ... Change the month
			if(selectedDateArr[1] >= 21) // If the user clicks on the previous month
				changeMonth(true, selectedDateArr[1]);
			else // If the user clicks on the next month
				changeMonth(false, selectedDateArr[1]);
		}
		else // If the day is in the current month
		{
			// If the old selected day was the current day
			if(Integer.parseInt(calendarDateLabels[selDateRow][selDateDay].getText()) == currentDateArr[1] && currentDateArr[0] == selectedDateArr[0] && currentDateArr[2] == selectedDateArr[2])
				calendarDateBoxes[selDateRow][selDateDay].setFill(clrCurrDate);
			else
			{
				if(client.getEvents(LocalDate.of(selectedDateArr[2], selectedDateArr[0], Integer.parseInt(calendarDateLabels[selDateRow][selDateDay].getText()))) == null)
					calendarDateBoxes[selDateRow][selDateDay].setFill(clrActivated); // Light Gray - Activated
				else
					calendarDateBoxes[selDateRow][selDateDay].setFill(clrEvents); // Green - Events on this day

				if(calendarDateLabels[selDateRow][selDateDay].getText().equals("25") && selectedDateArr[0] == 12)
					calendarDateBoxes[selDateRow][selDateDay].setFill(Color.RED); // Red - No events allowed
				if(calendarDateLabels[selDateRow][selDateDay].getText().equals("1") && selectedDateArr[0] == 1)
					calendarDateBoxes[selDateRow][selDateDay].setFill(Color.RED);
				if(calendarDateLabels[selDateRow][selDateDay].getText().equals("4") && selectedDateArr[0] == 7)
					calendarDateBoxes[selDateRow][selDateDay].setFill(Color.RED);
			}
			calendarDateBoxes[row][day].setFill(clrSelDate);
			selectedDateLD = LocalDate.of(selectedDateArr[2], selectedDateArr[0], selectedDateArr[1]); // Update selectedDateLD
			selDateRow = row;
			selDateDay = day;
			updateSelLabel();
		}
		updateLists();
	}

	/**
	 * Tell the application to change the month
	 */
	private void showChangedMonth()
	{
		int dayOfTheWeek = getDayOfTheWeek(1, selectedDateArr[0], selectedDateArr[2]); // Gets the day of the week of the first day of the month

		int[][] calendarDates = new int[6][7]; // The dates to be written to the program
		int prevMonthDays; // Get the amount of days in the previous month
		if(selectedDateArr[0] == 1) // Wrap
			prevMonthDays = getAmtOfDays(12, 0); // Second parameter doesn't matter because month is not February
		else
			prevMonthDays = getAmtOfDays(selectedDateArr[0] - 1, selectedDateArr[2]);
		if(dayOfTheWeek == 0) // This is so if the first day of the month is on a Sunday, start the month on the second line
			dayOfTheWeek += 7;
		for(int dayCal = 0; dayCal < 7; dayCal++) // Populate first row with the last of last month's dates
		{
			calendarDates[0][dayCal] = (prevMonthDays - dayOfTheWeek + dayCal) % prevMonthDays + 1;
		}
		int dayIndex = (prevMonthDays - dayOfTheWeek + 7) % prevMonthDays; // The index of the current date
		int currMonthDays = getAmtOfDays(selectedDateArr[0], selectedDateArr[2]); // Get the amount of days in the current month
		// Go through all of the calendar cells
		for(int rowCal = 1; rowCal < 6; rowCal++)
		{
			for(int dayCal = 0; dayCal < 7; dayCal++)
			{
				calendarDates[rowCal][dayCal] = dayIndex % currMonthDays + 1; // Set the date label
				dayIndex++;
			}
		}
		populateCalendar(calendarDates); // Update the calendar UI
	}

	/**
	 * Returns the selected date's day of the week
	 * @param day The day of the desired date
	 * @param monthParam The month of the desired date
	 * @param yearParam The year of the desire date
	 * @return dayOfTheWeek The index of the day of the week (0 = Sunday,..., 6 = Saturday)
	 */
	private int getDayOfTheWeek(int day, int monthParam, int yearParam)
	{
		int month = monthParam - 2;
		if(month <= 0)
			month += 12;
		int year = yearParam % 100;
		if(month >= 11)
			year--;
		int century = yearParam / 100;
		// Uses Gauss's Algorithm to determine the day of the week - https://en.wikipedia.org/wiki/Determination_of_the_day_of_the_week
		int dayOfTheWeek = (day + (int) Math.floor((2.6 * month) - 0.2) + year + (year / 4) + (century / 4) - (2 * century)) % 7;
		if(dayOfTheWeek < 0)
			dayOfTheWeek += 7;

		return dayOfTheWeek;
	}

	/**
	 * Returns the amount of days in the specified month
	 * @param m Month of the desired amount of days
	 * @param y The year... Only useful if the month is February (for leap years)
	 * @return The amount of days
	 */
	private int getAmtOfDays(int m, int y)
	{
		if(m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) // January, March,..., December
			return 31;
		else if(m == 2) // February
		{
			boolean isNine = false; // isNine means is 29 days
			if(y % 4 == 0)
			{
				if(y % 100 == 0)
				{
					if(y % 400 == 0)
						isNine = true;
				}
				else
					isNine = true;
			}
			if(isNine)
				return 29;
			else
				return 28;
		}
		else
			return 30;
	}

	/**
	 * Populates the calendar with the specified dates
	 * @param calendarDates The array of date labels
	 */
	private void populateCalendar(int[][] calendarDates)
	{
		Platform.runLater(() -> { // In order to change the labels, you must use Platform.runLater
			// Show the current month shown (at the top of the pane)
			String currMonthLabel = months[selectedDateArr[0]-1];
			if(selectedDateArr[2] != currentDateArr[2]) // If the selected year is not the current year
				currMonthLabel += " " + selectedDateArr[2];
			lblCurrentMonth.setText(currMonthLabel);

			// Fill the calendar with labels
			boolean hitFirst = false; // If the index has hit the first day of the month
			for(int rowCal = 0; rowCal < 6; rowCal++)
			{
				for(int dayCal = 0; dayCal < 7; dayCal++)
				{
					if(calendarDates[rowCal][dayCal] == 1)
						hitFirst = !hitFirst;
					if(!hitFirst) // If the month is not selected
						calendarDateBoxes[rowCal][dayCal].setFill(clrDeactivated); // Dark Gray - Deactivated
					else if(calendarDates[rowCal][dayCal] == currentDateArr[1] && currentDateArr[0] == selectedDateArr[0] && currentDateArr[2] == selectedDateArr[2]) // If the date is the current date
						calendarDateBoxes[rowCal][dayCal].setFill(clrCurrDate); // Red - Current date
					else // If the date is in the current month
					{
						if(client.getEvents(LocalDate.of(selectedDateArr[2], selectedDateArr[0], calendarDates[rowCal][dayCal])) == null)
							calendarDateBoxes[rowCal][dayCal].setFill(clrActivated); // Light Gray - Activated
						else
							calendarDateBoxes[rowCal][dayCal].setFill(clrEvents); // Green - Events on this day
					}
					if(calendarDates[rowCal][dayCal] == 25 && selectedDateArr[0] == 12 && hitFirst)
						calendarDateBoxes[rowCal][dayCal].setFill(Color.RED); // Red - No events allowed
					if(calendarDates[rowCal][dayCal] == 1 && selectedDateArr[0] == 1 && hitFirst)
						calendarDateBoxes[rowCal][dayCal].setFill(Color.RED);
					if(calendarDates[rowCal][dayCal] == 4 && selectedDateArr[0] == 7 && hitFirst)
						calendarDateBoxes[rowCal][dayCal].setFill(Color.RED);
					if(calendarDates[rowCal][dayCal] == selectedDateArr[1] && hitFirst) // If the cell is the selected date
					{
						calendarDateBoxes[rowCal][dayCal].setFill(clrSelDate); // Blue - Selected date
						selDateRow = rowCal;
						selDateDay = dayCal;
					}
					calendarDateLabels[rowCal][dayCal].setText("" + calendarDates[rowCal][dayCal]); // Set the labels
				}
			}

			updateSelLabel(); // Updates the selected day label
		});
	}

	/**
	 * Updates the selected day label
	 */
	private void updateSelLabel()
	{
		Platform.runLater(() ->
		{
			String selDateLabel = days[selDateDay] + ", " + months[selectedDateArr[0]-1] + " " + selectedDateArr[1] + ", " + selectedDateArr[2];
			lblSelectedDate.setText(selDateLabel);
			lblSelectedDate2.setText("Selected Date\n" +  selDateLabel);

		});
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
}
