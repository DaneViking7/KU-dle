package com.base.data.interfaces;

import com.base.data.models.Event;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

/**
 * Interface for gson to convert json of events to a HashMap of events with date as key
 */
public class Events {
    private HashMap<LocalDate, List<Event>> events;

    /**
     * Creates a hashmap of event objects
     *
     * @param events list of events for date
     */
    public Events(HashMap<LocalDate, List<Event>> events) {
        this.events = events;
    }

    /**
     * Just a getter, you know how to use these
     * @return the hashmap of events
     */
    public HashMap<LocalDate, List<Event>> getEvents() {
        return events;
    }

    /**
     * Do I really need to explain a setter?
     * @param events list of events for date
     */
    public void setEvents(HashMap<LocalDate, List<Event>> events) {
        this.events = events;
    }
}