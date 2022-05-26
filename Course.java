//////////////// FILE HEADER (INCLUDE IN EVERY FILE)//////////////////////////
//
// Title:    P06 Course
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

/**
 * Class containing methods and data fields for the Course Object
 */
public class Course {

  private String name;
  private int numStudents;

  /**
   * Constructor for Course
   *
   * @param name name of Course
   * @param numStudents number of students in course
   * @throws IllegalArgumentException if number of students is less than 0
   */
  public Course(String name, int numStudents) throws IllegalArgumentException{
    if(numStudents < 0) throw new IllegalArgumentException();
    this.name = name;
    this.numStudents = numStudents;
  }

  /**
   * Returns name of course
   *
   * @return name of course
   */
  public String getName(){
    return name;
  }

  /**
   * Returns number of students in course
   *
   * @return number of students in course
   */
  public int getNumStudents(){
    return numStudents;
  }

}
