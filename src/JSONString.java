/**
 * A class for representing strings in JSON
 * 
 * @author Graeme Boy, Vasilisa Bashlovkina
 * @created April 23, 2014
 */

public class JSONString
    extends JSONVal
{
  String string;

  // Constructors
  public JSONString(String stringIn)
  {
    this.string = stringIn;
  } // JSONString

  public String toString()
  {
    return this.string;
  }

  public String get()
  {
    return this.string;
  }

  public boolean isString()
  {
    return true;
  } // type()

  public int compareTo(String val)
  {
    return (this.string.compareTo((String) val));
  } // compareTo
  
}// JSONString class
