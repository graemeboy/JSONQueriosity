/**
 * A general class for JSON values. All other JSON classes extend it.
 * 
 * @author Graeme Boy, Vasilisa Bashlovkina
 * @created April 23, 2014
 */
public class JSONVal
{

  // +------------------+---------------------------------------------------------
  // | Observer Methods |
  // +------------------+

  public boolean isNumber()
  {
    return false;
  } // isNumber()

  public boolean isString()
  {
    return false;
  } // isString()

  public boolean isObject()
  {
    return false;
  } // isObject

  public boolean isArray()
  {
    return false;
  } // isArray

  public boolean isConstant()
  {
    return false;
  } // isConstant()

  /**
   * Compare this to another JSONValue.
   * 
   * The default comparison is comparing the two values converted to strings.
   * @param val,  a JSONValue
   */
  public <T> int compareTo(T val)
  {
    return this.toString().compareTo(val.toString());
  }// compareTo(T0

  /**
   * Get the actual value of this
   * @return
   * @throws Exception
   */
  public Object get()
    throws Exception
  {
    return null;
  }// get()

}// JSONVal class
