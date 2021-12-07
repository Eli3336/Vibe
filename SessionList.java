package model;

import java.util.ArrayList;

public class SessionList
{
  private ArrayList<Session> sessionList;

  public SessionList()
  {
    this.sessionList = new ArrayList<>();
  }

  public void addSession(Session session)
  {
    sessionList.add(session);
  }
  public void removeSession(Session session)
  {
    sessionList.remove(session);
  }

  public int calculateNumberOfLessonsForCourse(Course course)
  {
    return sessionList.get(0).calculateNumberOfLessonsForCourse(course);
  }
  public ArrayList<Session> getAllSessions()
  {
    return sessionList;
  }

  public SessionList getAllSessionsAsList()
  {
    SessionList sessions = new SessionList();
    for(int i=0;i<sessionList.size();i++)
    {
      sessions.addSession(sessionList.get(i));
    }
    return sessions;
  }

  public ArrayList<Session> getBookedSessions()
  {
    ArrayList<Session> bookedSessions = new ArrayList<>();
    for(int i=0;i<sessionList.size();i++)
    {
      if(sessionList.get(i).getRoom() != null)
        bookedSessions.add(sessionList.get(i));
    }
    return bookedSessions;
  }
  public ArrayList<Session> getUnbookedSessions()
  {
    ArrayList<Session> unbookedSessions = new ArrayList<>();
    for(int i=0;i<sessionList.size();i++)
    {
      if(sessionList.get(i).getRoom() == null)
        unbookedSessions.add(sessionList.get(i));
    }
    return unbookedSessions;
  }

  public ArrayList<Room> getRoomsAvailableInPeriod(DateTime dateAndStartTime, DateTime dateAndEndTime, RoomList rooms)
  {
    ArrayList<Room> roomsAvailable = new ArrayList<>();
    for(int i=0;i<rooms.getAllBookedRooms().size();i++)
    {
      int ok=1;
      for(int j=0;j<sessionList.size();j++)
      {
        if(sessionList.get(j).getRoom().equals(rooms.getAllBookedRooms().get(i)))
          if(sessionList.get(j).getDateAndStartTime().isAfter(dateAndStartTime) &&
              dateAndEndTime.isAfter(sessionList.get(j).getDateAndStartTime()))
              ok=0;
          else if(sessionList.get(j).getDateAndEndTime().isAfter(dateAndStartTime) &&
              dateAndEndTime.isAfter(sessionList.get(j).getDateAndEndTime()))
            ok=0;
      }
      if(ok==1)
        roomsAvailable.add(rooms.getAllBookedRooms().get(i));
    }
    for(int i=0;i<rooms.getAllUnbookedRooms().size();i++)
    {
      roomsAvailable.add(rooms.getAllUnbookedRooms().get(i));
    }
    return roomsAvailable;
  }
  public ArrayList<Room> getRoomsAvailableInPeriodBySize(int capacity, DateTime dateAndStartTime, DateTime dateAndEndTime, RoomList rooms)
  {
    ArrayList<Room> roomsAvailableBySize = getRoomsAvailableInPeriod(dateAndStartTime, dateAndEndTime, rooms);
    for(int i=0;i<roomsAvailableBySize.size();i++)
    {
      if(roomsAvailableBySize.get(i).getCapacity() != capacity)
        roomsAvailableBySize.remove(roomsAvailableBySize.get(i));
    }
    return roomsAvailableBySize;
  }
  public ArrayList<Room> getRoomsAvailableInPeriodBySizeBiggerThan(int capacity, DateTime dateAndStartTime, DateTime dateAndEndTime, RoomList rooms)
  {
    ArrayList<Room> roomsAvailableBySizeBiggerThan = getRoomsAvailableInPeriod(dateAndStartTime, dateAndEndTime, rooms);
    for(int i=0;i<roomsAvailableBySizeBiggerThan.size();i++)
    {
      if(roomsAvailableBySizeBiggerThan.get(i).getCapacity() >= capacity)
        roomsAvailableBySizeBiggerThan.remove(roomsAvailableBySizeBiggerThan.get(i));
    }
    return roomsAvailableBySizeBiggerThan;
  }
  public ArrayList<Room> getUnitableRoomsInPeriod(DateTime dateAndStartTime, DateTime dateAndEndTime, RoomList rooms)
  {
    ArrayList<Room> unitableRooms = getRoomsAvailableInPeriod(dateAndStartTime, dateAndEndTime, rooms);
    for(int i=0;i<unitableRooms.size();i++)
    {
      if(unitableRooms.get(i).getUnitedWith() == null)
        unitableRooms.remove(unitableRooms.get(i));
      else
      {
        Room room = rooms.getRoomByID(unitableRooms.get(i).getUnitedWith());
        if(!(getRoomsAvailableInPeriod(dateAndStartTime, dateAndEndTime, rooms).contains(room)))
          unitableRooms.remove(unitableRooms.get(i));
      }
    }
    return unitableRooms;
  }

  public String toString()
  {
    return "List: " + sessionList;
  }
}