package readers;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import model.*;

public class RoomReader
{

  public static void main(String[] args) throws Exception
  {
    /* file variable is created with the data from the text file */
    File file = new File("C:\\Users\\luisd\\Downloads\\Rooms.txt");

    Scanner in = new Scanner(file);
    RoomList roomList= new RoomList();
    while (in.hasNext())
    {

      String line = in.nextLine();
      String[] splittingline = line.split(",");
      String roomNumber = splittingline[0].trim();
      int roomSize = Integer.parseInt(splittingline[1].trim());
      String roomNumber2= splittingline[3].trim();
      Room room;

      if(roomNumber2!=null){

        room= new Room(roomNumber, roomSize, roomNumber2);

      }
      else
        room= new Room(roomNumber, roomSize);

      roomList.addRoom(room);
    }

  }

}