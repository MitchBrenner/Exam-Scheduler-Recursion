//////////////// FILE HEADER (INCLUDE IN EVERY FILE)//////////////////////////
//
// Title:    P06 Room
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
 * Class containing methods and data fields for the Room Object
 */
public class Room {

  private String location;
  private int capacity;

  /**
   * Constructor for Room, throws IllegalArgumentException if capacity is negative
   *
   * @param location location of the room
   * @param capacity opacity of the room
   */
  public Room(String location, int capacity){
    if(capacity < 0) throw new IllegalArgumentException("capacity is negative");
    this.location = location;
    this.capacity = capacity;
  }

  /**
   * Gets the location of the room
   *
   * @return the location of the room
   */
  public String getLocation(){
    return location;
  }

  /**
   * Gets the capacity of the room
   *
   * @return the capacity of the room
   */
  public int getCapacity(){
    return capacity;
  }

  /**
   * Reduces the capacity of the room by the given int, throws IllegalArgumentException if trying
   * to remove more than the capacity
   *
   * @param reduce number to reduce the capacity by
   * @return a new Room object with the new capacity
   */
  public Room reduceCapacity(int reduce){
    if(reduce > capacity) throw new IllegalArgumentException("too much reducing");
    return new Room(this.location, capacity - reduce);
  }

}
