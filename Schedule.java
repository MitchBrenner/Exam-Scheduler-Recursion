//////////////// FILE HEADER (INCLUDE IN EVERY FILE)//////////////////////////
//
// Title:    P06 Schedule
// Course:   CS 300 Spring 2022
//
// Author:   Mitchell Brenner
// Email:    mkbrenner3@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION///////////////////
//
// Partner Name:    Aidan Carrig
// Partner Email:   acarrig@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _X_ Write-up states that pair programming is allowed for this assignment.
//   _X_ We have both read and understand the course Pair Programming Policy.
//   _X_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP//////////////////////////
//
// Persons:         None
// Online Sources:  None
//
//////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;

/**
 * Class containing methods and data field for Schedule Object
 */
public class Schedule {

  private Room[] rooms;
  private Course[] courses;
  private int[] assignments;

  /**
   * Constructor for Schedule, auto assigns assignments array with -1
   *
   * @param rooms list of rooms
   * @param courses list of courses
   */
  public Schedule(Room[] rooms, Course[] courses){
    this.rooms = rooms;
    this.courses = courses;
    assignments = new int[courses.length];
    for(int i = 0; i < assignments.length; i++){
      assignments[i] = -1;
    }
  }

  /**
   * Constructor for Schedule
   *
   * @param rooms list of rooms
   * @param courses list of courses
   * @param assignments list containing assignments
   */
  private Schedule(Room[] rooms, Course[] courses, int[] assignments){
    this.rooms = rooms;
    this.courses = courses;
    this.assignments = assignments;
  }

  /**
   * Returns the number of rooms available in this schedule
   *
   * @return the number of rooms available in this schedule
   */
  public int getNumRooms(){
    return rooms.length;
  }

  /**
   * Gets the room at a valid index
   *
   * @param index index to look for room
   * @return room at given index
   */
  public Room getRoom(int index){
    if(index < 0 || index >= rooms.length) throw new IndexOutOfBoundsException("invalid index");
    return rooms[index];
  }

  /**
   * Returns the number of courses in schedule
   *
   * @return the number of courses in schedule
   */
  public int getNumCourses(){
    return courses.length;
  }

  /**
   * Gets the course at a valid index
   *
   * @param index index of course to be looked for
   * @return course at given index
   */
  public Course getCourse(int index){
    if(index < 0 || index >= courses.length) throw new IndexOutOfBoundsException("invalid index");
    return courses[index];
  }

  /**
   * Returns true if and only if the course at the given index has been assigned
   * a room; false otherwise
   *
   * @param index index of course
   * @return true if course has been assigned, false otherwise
   */
  public boolean isAssigned(int index){
    if(index < 0 || index >= assignments.length) throw new IndexOutOfBoundsException("invalid index");
    if(assignments[index] == -1) return false;
    return true;
  }

  /**
   * Returns the Room object assigned to the course at the given index;
   * throws an IllegalArgumentException if the course has not been assigned a room, or an
   * IndexOutOfBoundsException with a descriptive error message if the given course index is invalid
   *
   * @param index index of course
   * @return room object assigned to the course at the given index
   */
  public Room getAssignment(int index){
    if(!isAssigned(index)) throw new IllegalArgumentException("course not assigned");
    if(index < 0 || index >= courses.length) throw new IndexOutOfBoundsException("invalid index");
    return rooms[assignments[index]];
  }

  /**
   * Checks to see if every course has been assigned a room
   *
   * @return true if and only if all courses have been assigned to rooms; false
   * otherwise
   */
  public boolean isComplete(){
    for (int assignment : assignments) {
      if (assignment == -1) {
        return false;
      }
    }
    return true;
  }

  /**
   * Assigns course in assignment
   *
   * throws an IndexOutOfBoundsException with a descriptive error
   * message if the given course or
   * room index is invalid, or an IllegalArgumentException with a descriptive error message if the
   * given course has already been assigned a room, or if the room does not have sufficient
   * capacity.
   *
   * @param courseIndex index of course
   * @param roomIndex index of roomIndex
   * @return a NEW Schedule object with the course at the first index assigned to the room at the
   *          second  index;
   */
  public Schedule assignCourse(int courseIndex, int roomIndex){

    if(courseIndex < 0 || courseIndex >= courses.length) throw new
            IndexOutOfBoundsException("invalid courseIndex");
    if(roomIndex < 0 || roomIndex >= rooms.length) throw new
            IndexOutOfBoundsException("invalid roomIndex");
    if(isAssigned(courseIndex)) throw new IllegalArgumentException("course index has already been" +
            " assigned");
    if(courses[courseIndex].getNumStudents() > rooms[roomIndex].getCapacity())
      throw new IllegalArgumentException("too many students");

    Room[] roomCopy = Arrays.copyOf(rooms, rooms.length);
    int[] assignmentsCopy = Arrays.copyOf(assignments, assignments.length);

    assignmentsCopy[courseIndex] = roomIndex;
    roomCopy[roomIndex] = rooms[roomIndex].reduceCapacity(courses[courseIndex].getNumStudents());

    return new Schedule(roomCopy, this.courses, assignmentsCopy);

  }

  /**
   * Creates a string representation of schedule
   *
   * @return returns a string representation of schedule
   */
  @Override
  public String toString(){
    String returnMe = "";
    for (int i = 0; i < courses.length; i++){
      if(isAssigned(i)){
        returnMe += courses[i].getName() + ": " + rooms[assignments[i]].getLocation() + ", ";
      } else {
        returnMe += courses[i].getName() + ": " + "Unassigned, ";
      }
    }
    return returnMe;
  }

}
