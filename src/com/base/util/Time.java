package com.base.util;

import com.base.main.CalendarUI;
import com.base.data.models.User;

import java.time.LocalTime;
import java.util.List;

/**
 * Custom time object: contains a LocalTime and list of attendees
 */
public class Time {
    private LocalTime time;
    private List<User> attendees;
    private static boolean twentyFourMode;

    /**
     * Constructor, signifies which users are available for a specific
     * time.
     * @param time time for event
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
     * @param attendees list of attendees for time
     */
    public void setAttendees(List<User> attendees) {
        this.attendees = attendees;
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
		{
			//System.out.print("false\n");
			return false;
		}
		attendees.add(attendee);
		//System.out.print("true\n");
		return true;
	}

	public static void setTwentyFourMode(boolean twentyfour)
	{
		twentyFourMode = twentyfour;
	}

    /**
	 * Gets time
	 *
	 * @return formatted string
	 */
	@Override
    public String toString() {
    	String temp;
    	if(twentyFourMode)
    	{
    		temp = String.format("%02d:%02d", time.getHour(), time.getMinute());
    	}
    	else
    	{
    		if(time.getHour() > 12)
    			temp = String.format("%02d:%02d PM", (time.getHour()-12), time.getMinute());
    		else
    			temp = String.format("%02d:%02d AM", time.getHour(), time.getMinute());
    	}
        return temp;
    }
}
