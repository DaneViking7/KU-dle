package com.base.data;

import com.base.data.interfaces.Events;
import com.base.data.interfaces.Users;
import com.base.data.models.Event;
import com.base.data.models.User;
import com.base.util.Time;
import com.base.util.Task;
import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.*;

import java.io.*;
import java.time.*;
import java.util.*;

/**
 * Interface for UI to do data manipulation and file IO
 */
public class DairyFarmerClient {
    private Gson gson;
    private FileWriter writer;
    private BufferedReader reader;

    private HashMap<LocalDate, List<Event>> events;
    private HashMap<String, User> users;

    /**
     * Initializes gson object
     */
    public DairyFarmerClient() {
        /*
        Gson used from gson library by Google
        Converters.registerAll used from gson-javatime-serialisers by gkopff
         */
        gson = Converters.registerAll(new GsonBuilder()).create();

        events = new HashMap<>();
        users = new HashMap<>();
    }

    /**
     * Creates a hashmap of users
     *
     * @throws IOException If an input or output exception occurred
     */
    public void initUsers() throws IOException {
        File userFile = new File("res/users.txt");
        if (!userFile.exists()) {
            users = new HashMap<>();
            return;
        }

        reader = new BufferedReader(new FileReader("res/users.txt"));
        String json = reader.readLine();

        //Used gson.fromJson from gson library by Google
        Users tempUsers = gson.fromJson(json, Users.class);
        users = tempUsers.getUsers();
    }

    /**
     * Creates a hashmap of events
     *
     * @throws IOException If an input or output exception occurred
     */
    public void initEvents() throws IOException {
        File eventsFile = new File("res/events.txt");
        if (!eventsFile.exists()) {
            events = new HashMap<>();
            return;
        }

        reader = new BufferedReader(new FileReader("res/events.txt"));
        String json = reader.readLine();

        //Used gson.fromJson from gson library by Google
        Events tempEvents = gson.fromJson(json, Events.class);
        events = tempEvents.getEvents();
    }

    /**
     * Converts events to json and saves to file
     *
     * @throws IOException If an input or output exception occurred
     */
    public void saveEvents() throws IOException {
        Events tempEvents = new Events(events);

        //Used gson.toJson from gson library by Google
        String eventJson = gson.toJson(tempEvents);
        createFile(eventJson, "res/events.txt");
    }

    /**
     * Converts user data to json and save to file
     *
     * @throws IOException If an input or output exception occurred
     */
    public void saveUsers() throws IOException {
        Users tempUsers = new Users(users);

        //Used gson.toJson from gson library by Google
        String userJson = gson.toJson(tempUsers);
        createFile(userJson, "res/users.txt");
    }

    /**
     * Writes json data to file
     *
     * @param json String representing json data
     * @param fileName String representing file name
     * @throws IOException If an input or output exception occurred
     */
    public void createFile(String json, String fileName) throws IOException {
        writer = new FileWriter(fileName);
        writer.write(json);
        writer.close();
    }

    /**
     * Returns list of events for specific date
     *
     * @param date LocalDate to access events from specific day
     * @return list of events for the provided date
     * @see LocalDate
     */
    public List<Event> getEvents(LocalDate date) {
        return events.get(date);
    }

    /**
     * Creates an event with specified information
     *
     * @param eventName String representing event name
     * @param description String representing event description
     * @param creatorName String representing creator name
     * @param date LocalDate representing day of event
     * @param times List of times for event
     * @param attendees List of attendees for event
     * @param tasks List of tasks for event
     * @return true if event was created
     * @see LocalDate
     * @see Time
     * @see List
     */
    public boolean createEvent(String eventName, String description, String creatorName, LocalDate date, List<Time> times, List<User> attendees, List<Task> tasks) {
        Event event = new Event(eventName, description, creatorName, date, times, attendees, tasks);

        if (events.containsKey(date)) {
            events.get(date).add(event);
        } else {
            List<Event> tempEvents = new ArrayList<>();
            tempEvents.add(event);

            events.put(date, tempEvents);
        }

        return true;
    }

    /**
     * Deletes and event if it exists
     *
     * @param date used to look up event list
     * @param eventName String to find specific event
     * @throws IOException If an input or output exception occurred
     * @see LocalDate
     * @return true if a event was found and it was deleted
     */
    public boolean deleteEvent(LocalDate date, String eventName) throws IOException {
        List<Event> eventsForDate = getEvents(date);
        Event eventToDelete = null;
        for(Event event : eventsForDate){
            if(event.getEventName().equals(eventName)){
                eventToDelete = event;
                break;
            }
        }

        if (eventToDelete != null) {
            eventsForDate.remove(eventToDelete);
            return true;
        }

        return false;
    }

    /**
     * Creates a user with specified information
     *
     * @param username String representing username
     * @param password String representing password
     * @param isAdmin boolean representing admin status of user
     * @return true if user is created
     */
    public boolean createUser(String username, String password, boolean isAdmin)
    {
        if(!users.containsKey(username))
        {
            User newUser = new User(username, password, isAdmin);
            users.put(username, newUser);
            return(true);
        }
        else
        {
            return(false);
        }
    }

    /**
     * Deletes user with specified username
     *
     * @param username String representing username
     * @return true if user was deleted else false
     */
    public boolean deleteUser(String username)
    {
        if (users.containsKey(username)) {
            users.remove(username);
            return true;
        }

        return false;
    }

    /**
     * Returns a hashmap of users
     *
     * @return users
     * @see HashMap
     * @see User
     */
    public HashMap<String, User> getUsers() {
        return users;
    }

    public User getUser(String username) {
        if(users.containsKey(username)){
            return users.get(username);
        }
        return null;
    }
}
