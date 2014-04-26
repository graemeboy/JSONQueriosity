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
  /**
   * Determine whether this is a JSONNumber
   */
  public boolean isNumber()
  {
    return false;
  } // isNumber()

  /**
   * Determine whether this is a JSONString
   */
  public boolean isString()
  {
    return false;
  } // isString()

  /**
   * Determine whether this is a JSONObject
   */
  public boolean isObject()
  {
    return false;
  } // isObject

  /**
   * Determine whether this is a JSONArray
   */
  public boolean isArray()
  {
    return false;
  } // isArray

  /**
   * Determine whether this is a JSONConstant
   */
  public boolean isConstant()
  {
    return false;
  } // isConstant()

  // +------------------+---------------------------------------------------------
  // | Standard Methods |
  // +------------------+

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
