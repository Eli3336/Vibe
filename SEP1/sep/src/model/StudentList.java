package model;

import java.util.ArrayList;

public class StudentList
{
  private ArrayList<Student> studentList;

  public StudentList()
  {
    this.studentList = new ArrayList<>();
  }

  public int getSize()
  {
    return studentList.size();
  }

  public void addStudent(Student student)
  {
    studentList.add(student);
  }

  public void removeStudentByID(int id)
  {
    for(int i=0;i<studentList.size();i++)
    {
      if(id == studentList.get(i).getStudentID())
        studentList.remove(studentList.get(i));
    }
  }
  public StudentList getAllStudents()
  {
    StudentList students = new StudentList();
    for(int i=0;i<studentList.size();i++)
      students.addStudent(studentList.get(i));
    return students;
  }

  public StudentList getStudentsByClassID(String classID)
  {
    StudentList studentsByClass = new StudentList();
    for(int i=0;i<studentList.size();i++)
    {
      if(classID.equals(studentList.get(i).getClassID()))
      {
        studentsByClass.addStudent(studentList.get(i));
      }
    }
    return studentsByClass;
  }
  public Student getStudentByID(int studentID)
  {
    for(int i=0;i<studentList.size();i++)
    {
      if(studentID == studentList.get(i).getStudentID())
        return studentList.get(i);
    }
    return null;
  }
  public StudentList getStudentsByName(String name)
  {
    StudentList studentsByName = new StudentList();
    for(int i=0;i<studentList.size();i++)
    {
      if(name.equals(studentList.get(i).getName()))
        studentsByName.addStudent(studentList.get(i));
    }
    return studentsByName;
  }

  public String toString()
  {
    return "List: " + studentList;
  }
}
