//////////////// FILE HEADER (INCLUDE IN EVERY FILE)//////////////////////////
//
// Title:    P06 ExamSchedulerTester
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

import java.util.ArrayList;

public class ExamSchedulerTester {

  /**
   * Calls all tester methods
   *
   * @param args arguments in any
   */
  public static void main(String[] args) {
    if(testCourseConstructor() && testRoom() && testScheduleAccessors() && testAssignCourse()
            && testFindSchedule() && testFindAllSchedules()){
      System.out.println("All tests passed");
    }else{
      System.out.println("Failed");
    }
  }

  /**
   * Checks the correctness of the Course Constructor
   *
   * @return true if all tests passed, false otherwise
   */
  public static boolean testCourseConstructor(){
    try{
      Course cs300 = new Course("cs300", -300);
      System.out.println("Course Constructor Failed");
      return false;
    } catch (IllegalArgumentException e){
      // passed
    }
    return true;
  }

  /**
   * Checks the correctness of the Room constructor and methods
   *
   * @return true if all tests passed, false otherwise
   */
  public static boolean testRoom(){
    Room room1;
    try{
      room1 = new Room("room1", -100);
      System.out.println("(1) Room Class Failed");
      return false;
    } catch(IllegalArgumentException e){
      // passed
    }
    try{
      room1 = new Room("room1", 10);
      room1.reduceCapacity(11);
      System.out.println("(2) Room Class Failed");
      return false;
    } catch (IllegalArgumentException e){
      // passed
    }
    return true;
  }

  /**
   * Checks the correctness of the Schedule Accessor methods
   *
   * @return true if all tests passed, false otherwise
   */
  public static boolean testScheduleAccessors(){
    Room[] rooms = {new Room("Bascom 272", 100),
            new Room("Bascom 100", 50)};
    Course[] courses = {new Course("CS200", 50),
            new Course("CS300", 50)};

    Schedule schedule = new Schedule(rooms, courses);

    if(!schedule.toString().equals("CS200: Unassigned, CS300: Unassigned, ")){
      System.out.println("(1) ScheduleAccessor Failed");
      return false;
    }

    if(schedule.getNumCourses() != 2 || schedule.getNumRooms() != 2){
      System.out.println("(2) ScheduleAccessor Failed");
      return false;
    }

    if(schedule.getRoom(0) != rooms[0] ||
            schedule.getCourse(1) != courses[1]){
      System.out.println("(3) ScheduleAccessor Failed");
      return false;
    }

    return true;
  }

  /**
   * Checks the correctness of the assignCourse method
   *
   * @return true if all tests passed, false otherwise
   */
  public static boolean testAssignCourse(){

    Room[] rooms = {new Room("Bascom 272", 100),
            new Room("Bascom 100", 200)};
    Course[] courses = {new Course("CS200", 50),
            new Course("CS300", 99)};
    Schedule schedule = new Schedule(rooms, courses);

    // Checks index out of bounds works
    try{
      schedule = schedule.assignCourse(-1,-1);
      System.out.println("(1) assignCourse failed");
      return false;
    }catch(IndexOutOfBoundsException e){
      // passed
    }

    // Check assignCourse and getAssignment
    schedule = schedule.assignCourse(0,0);
    if(schedule.getAssignment(0) != schedule.getRoom(0)){
      System.out.println("(2) assignCourse failed");
      return false;
    }

    // Testing too many people in room
    try{
      schedule.assignCourse(1, 0);
      System.out.println("(3) assignCourse failed");
      return false;
    } catch (IllegalArgumentException e){
      // passed
    }

    // Check isComplete with full and not full list
    if(schedule.isComplete()){
      System.out.println("(1) isComplete failed");
      return false;
    }
    schedule = schedule.assignCourse(1,1);
    if(!schedule.isComplete()){
      System.out.println("(2) isComplete failed");
      return false;
    }

    return true;
  }

  /**
   * Checks the correctness of the findSchedule method
   *
   * @return true if all tests passed, false otherwise
   */
  public static boolean testFindSchedule(){

    Course[] courses = new Course[]{
            new Course("C1", 15),
            new Course("C2", 90),
            new Course("C3", 70)
    };
    Room[] rooms = new Room[]{
            new Room("R1", 75),
            new Room("R2", 100),
            new Room("R3", 15)
    };
    Schedule schedule = ExamScheduler.findSchedule(rooms, courses);

    if(!schedule.toString().equals("C1: R3, C2: R2, C3: R1, ")){
      return false;
    }

    return true;
  }

  /**
   * Checks the correctness of the findAllSchedules method
   *
   * @return true if all tests passed, false otherwise
   */
  public static boolean testFindAllSchedules(){
    Course[] courses = new Course[]{
            new Course("C1", 15),
            new Course("C2", 80),
            new Course("C3", 70)
    };
    Room[] rooms = new Room[]{
            new Room("R1", 75),
            new Room("R2", 100),
            new Room("R3", 15)
    };
    ArrayList<Schedule> schedule = ExamScheduler.findAllSchedules(rooms, courses);

    if(!schedule.toString().equals("[C1: R2, C2: R2, C3: R1, , C1: R3, C2: R2, C3: R1, ]")){
      return false;
    }

    return true;
  }

}
