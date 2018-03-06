package com.base.util;

import com.base.data.models.Event;
import com.base.data.models.User;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

/**
 * Custom utilities class: contains methods to validate client data
 */
public class Utilities {
    /**
     * Method to validate times
     *
     * @param times list of times to compare to
     * @param timeToCompare time to check
     * @return true if time is valid (does not overlap with other times) else false
     * @deprecated
     */
    public static boolean isTimeValid(Time timeToCompare, List<Time> times) {
        int timeToCompareInMin = timeToCompare.getTime().getHour() * 60 + timeToCompare.getTime().getMinute();
        int timeFromListInMin = 0;

        for (Time time : times) {
            timeFromListInMin = time.getTime().getHour() * 60 + time.getTime().getMinute();
            if (Math.abs(timeToCompareInMin - timeFromListInMin) < 20) {
                return false;
            }
        }

        return true;
    }

    /**
     * Signify if the username is taken or not
     *
     * @param existingUsers a hashmap of existing users with taken usernames
     * @param username the username the user wants to use with their new account
     * @return true if the username is taken
     */
    public static boolean userExists(HashMap<String, User> existingUsers, String username) {
        return (existingUsers.containsKey(username));
    }

    /**
     * Checks if event name is taken
     *
     * @param events hashmap of events
     * @param date key to get events of date
     * @param eventName name of event to check
     * @return true if event name is taken else false
     */
    public static boolean eventExists(HashMap<LocalDate, List<Event>> events, LocalDate date, String eventName) {
        if (!events.containsKey(date))
            return false;

        for (Event event : events.get(date)) {
            if (event.getEventName().equals(eventName))
                return true;
        }

        return false;
    }
}