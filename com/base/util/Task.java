package com.base.util;

import com.base.data.models.User;

/**
 * Custom task object: contains a name and attendee
 */
public class Task {
    private String taskName;
    private User attendee;

    /**
     * Constructor, signifies which user will handle the task
     * @param taskName name for task
     * @param attendee the user object that will handle the task
     * the event at the time.
     */
    public Task(String taskName, User attendee){
        this.taskName = taskName;
        this.attendee = attendee;
    }

    /**
     * Get the name of the task
     * @return the variable taskName
     */
    public String getTask() {
        return taskName;
    }

    /**
     * Set the name of the task
     * @param taskName sets local taskName to this variable
     */
    public void setTask(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Get the user object managing the task
     * @return attendee, the user object managing the task
     */
    public User getAttendee() {
        return attendee;
    }

    /**
     * Set the attendee with a new user object
     * @param attendee attendee to manage the task
     */
    public void setAttendee(User attendee) {
        this.attendee = attendee;
    }
    
    /**
	 * Gets task
	 *
	 * @return formatted string
	 */
	@Override
    public String toString() {
        return taskName;
    }
}