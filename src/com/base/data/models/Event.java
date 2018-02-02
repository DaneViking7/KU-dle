package com.base.data.models;

import java.util.List;

public class Event
{
  private String m_eventName;
  private String m_creatorName;
  private List<User> m_attendees;
  //TODO private List<datetime> validTimes;

//  Event(name, date, a list of valid times);


  public Event(String eventName, String creatorName, List<User> attendees) {
    this.m_eventName = eventName;
    this.m_creatorName = creatorName;
    this.m_attendees = attendees;
  }

  /*
    @pre: A valid string
    @post: re-assgins the member variable m_name
    @return: none
    */
  public void setEventName(String eventName)
  {
    m_eventName = eventName;
  }

  /*
  @pre: none
  @post: none
  @return: returns the events name
  */
  public String getEventName()
  {
    return(m_eventName);
  }


  public String getM_creatorName() {
    return m_creatorName;
  }

  public void setM_creatorName(String m_creatorName) {
    this.m_creatorName = m_creatorName;
  }

  public List<User> getM_attendees() {
    return m_attendees;
  }

  public void setM_attendees(List<User> m_attendees) {
    this.m_attendees = m_attendees;
  }
}
