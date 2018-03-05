package com.base.data.models;

import com.base.util.Time;
import com.base.util.Task;

import java.time.LocalDate;
import java.util.List;

/**
 * Event object: contains event name, creator name, date, list of times, list of attendees
 * and list of tasks.
 */
public class Event {
    private String eventName;
    private String creatorName;
    private String description;
    private LocalDate date;
    private List<Time> times;
    private List<User> attendees;
    private List<Task> tasks;

    /**
     * Creates an event with the specified values
     *
     * @param eventName String representing event name
	 * @param description String representing description of event
     * @param creatorName String representing creator name
     * @param date LocalDate representing event's date
     * @param times List of times for event
     * @param attendees List of attendees for event
     * @param tasks List of tasks for event
     * @see LocalDate
     * @see Time
     * @see List
     */
    public Event(String eventName, String description, String creatorName, LocalDate date, List<Time> times, List<User> attendees, List<Task> tasks) {
        this.eventName = eventName;
        this.description = description;
        this.creatorName = creatorName;
        this.date = date;
        this.times = times;
        this.setAttendees(attendees);
        this.tasks = tasks;
    }

    /**
     * Returns the event's name
     *
     * @return the event name
     */
    public String getEventName()
    {
        return(eventName);
    }

    /**
     * Re-assigns the member variable eventName
     *
     * @param eventName String representing the event name
     */
    public void setEventName(String eventName)
    {
        this.eventName = eventName;
    }

    /**
     * Returns the event creator name
     *
     * @return the creator's name
     */
    public String getCreatorName() {
        return creatorName;
    }

    /**
     * Re-assigns the member variable creatorName
     *
     * @param creatorName String representing event creator's name
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    /**
     * Returns the date of the event
     *
     * @return event date
     * @see LocalDate
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Re-assigns the member variable date
     *
     * @param date A valid LocalDate for event
     * @see LocalDate
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Returns the list of times for the event
     *
     * @return list of Times
     * @see Time
     * @see List
     */
    public List<Time> getTimes() {
        return times;
    }

    /**
     * Re-assigns the member variable times
     *
     * @param times A valid list of times for an event
     * @see Time
     * @see List
     */
    public void setTimes(List<Time> times) {
        this.times = times;
    }

    /**
     * Returns the attendees of the event
     * @return attendees The list of attendees
     */
	public List<User> getAttendees() {
		return attendees;
	}

	/**
	 * Re-assigns the member variable attendees
	 *
	 * @param attendees The list of attendees
	 */
	public void setAttendees(List<User> attendees) {
		this.attendees = attendees;
	}

    /**
     * Returns the list of tasks for the event
     *
     * @return list of Tasks
     * @see Task
     * @see List
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Re-assigns the member variable tasks
     *
     * @param tasks A valid list of tasks for an event
     * @see Task
     * @see List
     */
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
	
	/**
	 * Checks to see if the user is already attending
	 *
	 * @param attendee The attendee attending
	 * @return if they are already going or not
	 */
	public boolean attendeeExists(User attendee)
	{
		if(!attendees.isEmpty())
		{
			for(int i = 0; i < attendees.size(); i++)
			{
				if(attendees.get(i).equals(attendee))
					return true;
			}
		}
		return false;
	}

	/**
	 * Adds an attendee to the attendees list
	 *
	 * @param attendee The attendee attending
	 * @return if it worked or not
	 */
	public boolean addAttendee(User attendee)
	{
		if(attendeeExists(attendee))
			return false;
		attendees.add(attendee);
		return true;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString()
	{
		return eventName;
	}
}
