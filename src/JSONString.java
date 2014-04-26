/**
 * A class for representing strings in JSON
 * 
 * @author Graeme Boy, Vasilisa Bashlovkina
 * @created April 23, 2014
 */

public class JSONString
    extends JSONVal
{
  // +--------+----------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The actual string
   */
  String string;

  // +--------------+----------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Make a new JSONString
   * 
   * @param stringIn
   */
  public JSONString(String stringIn)
  {
    this.string = stringIn;
  } // JSONString

  // +---------+---------------------------------------------------------
  // | Methods |
  // +---------+
  /**
   *  The standard toString method
   */
  public String toString()
  {
    return this.string;
  }// toString()

  /**
   * Get the "value" of this string
   * (necessary because the superclass has this method)
   */
  public String get()
  {
    return this.string;
  }// get()

  /**
   * Determine whether this is a string
   * @return true
   */
  public boolean isString()
  {
    return true;
  } // isString()

  /**
   * Comapre this string to another
   * @param <T>
   * 
   * @param val
   * @return 0 is equal, < 0 is smaller, > 0 if greater 
   */

  public <T> int compareTo(T val)
  {
    return (this.string.compareTo(val.toString()));
  } // compareTo(String)

}// JSONString class
