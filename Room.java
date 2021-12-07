package model;

public class Room
{
  private String id;
  private int capacity;
  private String unitedWith;
  private boolean booked;

  public Room(String id, int capacity, String unitedWith)
  {
    this.id = id;
    this.capacity = capacity;
    this.unitedWith = unitedWith;
    this.booked = false;
  }
  public Room(String id, int capacity)
  {
    this.id = id;
    this.capacity = capacity;
    this.unitedWith = null;
    this.booked = false;
  }

  public String getId()
  {
    return id;
  }
  public int getCapacity()
  {
    return capacity;
  }
  public String getUnitedWith()
  {
    return unitedWith;
  }
  public boolean getIsBooked(){return booked;}

  public void setBooked(boolean booked)
  {
    this.booked = booked;
  }

  public String toString()
  {
    if(unitedWith == null)
      return "ID: " + this.id + ", capacity: " + this.capacity + ", is booked: " + this.booked;
    else
      return "ID: " + this.id + ", capacity: " + this.capacity + ", can be united with: " + this.unitedWith + ", is booked: " + this.booked;
  }

  public boolean equals(Object obj)
  {
    if(!(obj instanceof Room))
      return false;
    Room other = (Room) obj;
    if(unitedWith == null)
      if(other.unitedWith != null)
        return false;
    return id.equals(other.id) && capacity == other.capacity && unitedWith.equals(other.unitedWith) && this.booked == other.booked;
  }
}