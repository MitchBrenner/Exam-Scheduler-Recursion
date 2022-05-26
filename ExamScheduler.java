//////////////// FILE HEADER (INCLUDE IN EVERY FILE)//////////////////////////
//
// Title:    P06 ExamScheduler
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

/**
 * Creating the Exam Scheduler
 */
public class ExamScheduler {

  /**
   * Calls helper method to set a schedule with the given rooms and courses
   *
   * @param rooms   - array of rooms
   * @param courses - array of courses
   * @return - a valid Schedule for the given set of rooms and courses, or
   * throws an IllegalStateException if no such schedule exists.
   * This method should contain only a call to the helper method,
   * providing an empty starting Schedule.
   */
  public static Schedule findSchedule(Room[] rooms, Course[] courses) {
    Schedule schedule;
    try {
      schedule = findScheduleHelper(new Schedule(rooms, courses), 0);
    } catch (IllegalStateException e) {
      throw new IllegalStateException("cannot make valid schedule");
    }
    return schedule;
  }

  /**
   * Helper method to findSchedule. recursive method that assigns all unassigned
   * courses in a Schedule beginning from the index provided as an argument
   *
   * @param schedule - empty schedule
   * @param index    - index of courses
   * @return - a new, valid schedule or throws an IllegalStateException
   */
  private static Schedule findScheduleHelper(Schedule schedule, int index) {
    // (1) BASE CASE
    if (schedule.getNumCourses() == index) {
      if (schedule.isComplete()) return schedule;
      else throw new IllegalStateException("invalid schedule");
    }
    // (2)
    if(schedule.isAssigned(index)) {
      schedule = findScheduleHelper(schedule, index + 1);
      return schedule;
    }
    // (3)
    if(!schedule.isAssigned(index)) {
      // Loop through rooms
      for(int currRoom = 0; currRoom < schedule.getNumRooms(); currRoom++) {
        try {
          // try assigning course
          Schedule tempSched = schedule.assignCourse(index, currRoom);
          System.out.println(tempSched);
          // if assigning prev course worked, try assigning next course
          // this returns this method
          return findScheduleHelper(tempSched, index +1);
        } catch (IllegalArgumentException e) {
          System.out.println(e.getMessage());
        } catch (IllegalStateException e){}
      }
    }

    return findScheduleHelper(schedule,index +1);
  }

  /**
   * Finds all possible schedule combinations and returns them as an array list of schedules.
   *
   * @param rooms - array of rooms
   * @param courses - array of courses
   * @return - an ArrayList containing all possible Schedules for
   *           the given set of rooms and courses, or empty schedule if no possibilities.
   */
  public static ArrayList<Schedule> findAllSchedules(Room[] rooms, Course[] courses) {
    ArrayList<Schedule> possibleSchedules;
    possibleSchedules = findAllSchedulesHelper(new Schedule(rooms, courses), 0);
    return possibleSchedules;
  }

  /**
   * (1) If the provided index is equal to the number of courses, check to see if the Schedule is
   *    complete. If so, add it to an ArrayList of possible schedules and return the ArrayList.
   *
   * (2) If the provided index corresponds to a course that has already been assigned to a room,
   *    recursively add all possible valid schedules from the following indexes to an ArrayList of
   *    Schedules and return this ArrayList.
   *
   * (3) If the provided index corresponds to a course that has NOT already been assigned to a
   *    room, iteratively try to assign it to each possible valid Room and recursively add all
   *    possible valid schedules from the following indexes to an ArrayList of Schedules and
   *    return this ArrayList.
   *
   * @param schedule - schedule parameter with given rooms and courses arrays
   * @param index - index to start with
   * @return - ArrayList of schedules containing every possible schedule that can be made
   *           from the passed in parameters.
   */
  private static ArrayList<Schedule> findAllSchedulesHelper(Schedule schedule, int index) {
    ArrayList<Schedule> possibleSchedules = new ArrayList<>();
    // (1)
    if(schedule.getNumCourses() == index) {
      if(schedule.isComplete()) {
        // Add to list
        possibleSchedules.add(schedule);
        return possibleSchedules;
      }
    }
    // (2)
    if(schedule.isAssigned(index)) {
      possibleSchedules.addAll(findAllSchedulesHelper(schedule, index + 1));
      return possibleSchedules;
    }
    // (3)
    if(!schedule.isAssigned(index)) {
      // Loop through rooms
      for(int currRoom = 0; currRoom < schedule.getNumRooms(); currRoom++) {
        try {
          // Try to assign course
          Schedule tempSched = schedule.assignCourse(index, currRoom);
          // if assigning prev course worked, try assigning next course
          possibleSchedules.addAll(findAllSchedulesHelper(tempSched, index + 1));
        } catch (IllegalArgumentException | IllegalStateException e) {
          // continue
        }
      }
    }
    return possibleSchedules;
  }

}

