package com.base.util;

import com.base.data.models.User;

import java.time.LocalTime;
import java.util.List;

public class Time {
    private LocalTime time;
    private List<User> attendees;

    /*
    * @pre: Takes in a time and list of users
    * @post: assign local variables to passed in values
    * @return: nothing
     */

    /**
     * Constructor, signifies which users are available for a specific
     * time.
     * @param time
     * @param attendees is the list of user objects that can/will attend
     * the event at the time.
     */
    public Time(LocalTime time, List<User> attendees){
        this.time = time;
        this.attendees = attendees;
    }

    /**
     * GET THAT TIME
     * @return the variable time that we declared in this file because its
     * a getter, get it, we GET the variable WE declared
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * SET THAT TIME
     * @param time SET THAT TIME TO THIS VARIABLE
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * Get the list of user objects
     * @return attendees, the list of user objects that I just talked about
     */
    public List<User> getAttendees() {
        return attendees;
    }

    /**
     * Set the attendees with a new list of user objects
     * @param attendees
     */
    public void setAttendees(List<User> attendees) {
        this.attendees = attendees;
    }
}
