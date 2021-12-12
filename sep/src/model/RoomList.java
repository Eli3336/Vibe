package model;

import java.util.ArrayList;

public class RoomList
{
   private ArrayList<Room> roomList;

   public RoomList()
   {
      this.roomList = new ArrayList<>();
   }

   public RoomList getAllRooms()
   {
      RoomList rooms = new RoomList();
      for(int i=0;i<roomList.size();i++)
         rooms.addRoom(roomList.get(i));

      return rooms;
   }
   public ArrayList<Room> getAllRoomsAsArrayList()
   {
      return roomList;
   }

   public void addRoom(Room room)
   {
      roomList.add(room);
   }

   public Room getRoomByID(String id)
   {
      for(int i=0;i<roomList.size();i++)
      {
         if(id.equals(roomList.get(i).getId()))
            return roomList.get(i);
      }
      return null;
   }
   public RoomList getRoomsBySize(int capacity)
   {
      RoomList roomsBySize = new RoomList();
      for(int i=0;i<roomList.size();i++)
      {
         if(capacity == roomList.get(i).getCapacity())
            roomsBySize.addRoom(roomList.get(i));
      }
      return roomsBySize;
   }
   public RoomList getRoomsBySizeBiggerThan(int capacity)
   {
      RoomList roomsBySizeBiggerThan = new RoomList();
      for(int i=0;i<roomList.size();i++)
      {
         if(capacity <= roomList.get(i).getCapacity())
            roomsBySizeBiggerThan.addRoom(roomList.get(i));
      }
      return roomsBySizeBiggerThan;
   }
   public RoomList getUnitableRooms()
   {
      RoomList unitableRooms = new RoomList();
      for(int i=0;i<roomList.size();i++)
      {
         if(roomList.get(i).getUnitedWith() != null)
            unitableRooms.addRoom(roomList.get(i));
      }
      return unitableRooms;
   }

   public Room getRoomUnitedWith(Room room)
   {
      RoomList unitableRooms = getUnitableRooms();

      for(int i=0;i<unitableRooms.roomList.size();i++)
      {
         if(roomList.get(i).getId().equals(room.getUnitedWith()))
            return roomList.get(i);
      }
      return null;
   }
   public ArrayList<Room> getAllUnbookedRooms()
   {
      ArrayList<Room> unbookedRooms = new ArrayList<>();
      for(int i=0;i<roomList.size();i++)
      {
         if(!roomList.get(i).getIsBooked())
            unbookedRooms.add(roomList.get(i));
      }
      return unbookedRooms;
   }
   public ArrayList<Room> getAllBookedRooms()
   {
      ArrayList<Room> bookedRooms = new ArrayList<>();
      for(int i=0;i<roomList.size();i++)
      {
         if(roomList.get(i).getIsBooked())
            bookedRooms.add(roomList.get(i));
      }
      return bookedRooms;
   }

   public String toString()
   {
      return "Room list: " + roomList;
   }


}
