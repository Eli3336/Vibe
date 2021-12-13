package model;

public class Session
{
  private int number;
  private Course course;
  private int numberOfLessonsInSession;
  private Room room;
  private int numberOfLessonsForCourse;
  private int getNumberOfLessonsRemaining;
  private DateTime dateAndStartTime;
  private DateTime dateAndEndTime;

  public Session(int number, Course course)
  {
    this.number = number;
    this.course = course;
    this.numberOfLessonsInSession = 0;
    this.room = null;
    this.numberOfLessonsForCourse = calculateNumberOfLessonsForCourse(course);
    this.getNumberOfLessonsRemaining = numberOfLessonsForCourse - numberOfLessonsInSession;
    this.dateAndStartTime = null;
    this.dateAndEndTime = null;
  }
  public Session(int number, Course course, int numberOfLessonsInSession)
  {
    this.number = number;
    this.course = course;
    this.numberOfLessonsInSession = numberOfLessonsInSession;
    this.room = null;
    this.numberOfLessonsForCourse = calculateNumberOfLessonsForCourse(course);
    this.getNumberOfLessonsRemaining = numberOfLessonsForCourse - numberOfLessonsInSession;
    this.dateAndStartTime = null;
    this.dateAndEndTime = null;
  }
  public Session(int number, Course course, int numberOfLessonsInSession, DateTime dateAndStartTime, DateTime dateAndEndTime)
  {
    this.number = number;
    this.course = course;
    this.numberOfLessonsInSession = numberOfLessonsInSession;
    this.room = null;
    this.numberOfLessonsForCourse = calculateNumberOfLessonsForCourse(course);
    this.getNumberOfLessonsRemaining = numberOfLessonsForCourse - numberOfLessonsInSession;
    this.dateAndStartTime = dateAndStartTime;
    this.dateAndEndTime = dateAndEndTime;
  }
  public Session(int number, Course course, int numberOfLessonsInSession, Room room, DateTime dateAndStartTime, DateTime dateAndEndTime)
  {
    this.number = number;
    this.course = course;
    this.numberOfLessonsInSession = numberOfLessonsInSession;
    this.room = room;
    this.numberOfLessonsForCourse = calculateNumberOfLessonsForCourse(course);
    this.getNumberOfLessonsRemaining = numberOfLessonsForCourse - numberOfLessonsInSession;
    this.dateAndStartTime = dateAndStartTime;
    this.dateAndEndTime = dateAndEndTime;
  }

  public int calculateNumberOfLessonsForCourse(Course course)
  {
    int numberOfLessons = 0;
    /* 1 ects = 27.5 h
     1 lesson = 45 min
     10 ects = 275 h = 275*60 min = 16.500
     lessons = 275*60 / 45 = 366.(6)7
     */

    return numberOfLessons;
  }

  public DateTime calculateEndTimeForSession(int numberOfLessonsInSession)
  {
    int min = numberOfLessonsInSession * 45 + numberOfLessonsInSession * 5; //lesson + break
    int hour = 0;
    int day = dateAndStartTime.getDay();
    int month = dateAndStartTime.getMonth();
    int year = dateAndStartTime.getYear();
    while(min>59)
    {
      hour++;
      min-=60;
    }
    int newMin = dateAndStartTime.getMinute() + min;
    int newHour = dateAndStartTime.getHour() + hour;
    if(newMin > 59)
    {
      newHour++;
      newMin = newMin - 60;
    }
    if(newHour > 23)
    {
      day++;
      newHour = newHour - 24;
    }
    if(day>getMaxNumberOfDaysForMonth(month, year))
    {
      month++;
      day = day - getMaxNumberOfDaysForMonth(month, year);
    }
    if(month>12)
    {
      year++;
      month = month - 12;
    }
    DateTime dateAndEndTime = new DateTime(day, month, year, newHour, newMin);
    this.dateAndEndTime = dateAndEndTime;
    return dateAndEndTime;
  }

  public boolean isLeapYear(int year)
  {
    return year % 4 == 0 && (year % 100 != 0 || year % 400 ==0);
  }

  public int getMaxNumberOfDaysForMonth(int month, int year)
  {
    switch (month)
    {
      case 2:
        if (isLeapYear(year))
          return 29;
        return 28;

      case 4:
      case 6:
      case 9:
      case 11:
        return 30;
      default:
        return 31;
    }
  }

  public int getNumberOfLessonsForCourse()
  {
    return numberOfLessonsForCourse;
  }
  public int getGetNumberOfLessonsRemaining()
  {
    return getNumberOfLessonsRemaining;
  }
  public int getNumberOfLessonsInSession()
  {
    return numberOfLessonsInSession;
  }
  public void setNumberOfLessonsInSession(int number)
  {
    this.numberOfLessonsInSession = number;
    if(dateAndStartTime != null)
      calculateEndTimeForSession(numberOfLessonsInSession);
  }
  public void addLessonToSession()
  {
    this.numberOfLessonsInSession++;
    if(dateAndStartTime != null)
      calculateEndTimeForSession(numberOfLessonsInSession);
  }

  public void setRoom(Room room)
  {
    this.room = room;
  }

  public Room getRoom()
  {
    return room;
  }

  public int getNumber()
  {
    return number;
  }

  public Course getCourse()
  {
    return course;
  }

  public void setDateAndStartTime(DateTime dateAndStartTime)
  {
    this.dateAndStartTime = dateAndStartTime;
    calculateEndTimeForSession(numberOfLessonsInSession);
  }
  public DateTime getDateAndStartTime()
  {
    return dateAndStartTime;
  }
  public DateTime getDateAndEndTime()
  {
    return dateAndEndTime;
  }

  public String toString()
  {
    return "Number: " + number + ", course: " + course + ", number of lessons in session: " + numberOfLessonsInSession
        + ", room: " + room + ", number of lessons for course: " + numberOfLessonsForCourse + ", number of lessons remaining: "
        + getNumberOfLessonsRemaining + ", start: " + dateAndStartTime + ", end: " + dateAndEndTime;
  }

  public boolean equals(Object obj)
  {
    if(!(obj instanceof Session))
      return false;
    Session other = (Session) obj;
    if((room == null && other.room != null) ||(room != null && other.room == null))
      return false;
    else if((dateAndStartTime == null && other.dateAndStartTime != null) || (dateAndStartTime != null && other.dateAndStartTime == null))
      return false;
    else if((dateAndEndTime == null && other.dateAndEndTime != null) || (dateAndEndTime != null && other.dateAndEndTime == null))
      return false;
    else if(room == null && other.room == null)
      if(dateAndEndTime == null && other.dateAndEndTime == null)
        if(dateAndStartTime == null && dateAndStartTime == null)
          return this.number == other.number && this.course.equals(other.course) &&
              this.numberOfLessonsInSession == other.numberOfLessonsInSession &&
              this.numberOfLessonsForCourse == other.numberOfLessonsForCourse &&
              this.getNumberOfLessonsRemaining == other.getNumberOfLessonsRemaining;
        else
          return this.number == other.number && this.course.equals(other.course) &&
              this.numberOfLessonsInSession == other.numberOfLessonsInSession &&
              this.numberOfLessonsForCourse == other.numberOfLessonsForCourse &&
              this.getNumberOfLessonsRemaining == other.getNumberOfLessonsRemaining &&
              this.dateAndStartTime.equals(other.dateAndStartTime);
        else
          return this.number == other.number && this.course.equals(other.course) &&
              this.numberOfLessonsInSession == other.numberOfLessonsInSession &&
              this.numberOfLessonsForCourse == other.numberOfLessonsForCourse &&
              this.getNumberOfLessonsRemaining == other.getNumberOfLessonsRemaining &&
              this.dateAndStartTime.equals(other.dateAndStartTime) && this.dateAndEndTime.equals(other.dateAndEndTime);
    else
      return this.number == other.number && this.course.equals(other.course) &&
          this.numberOfLessonsInSession == other.numberOfLessonsInSession && this.room.equals(other.room) &&
          this.numberOfLessonsForCourse == other.numberOfLessonsForCourse &&
          this.getNumberOfLessonsRemaining == other.getNumberOfLessonsRemaining &&
          this.dateAndStartTime.equals(other.dateAndStartTime) && this.dateAndEndTime.equals(other.dateAndEndTime);
  }
}
