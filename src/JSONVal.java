public class JSONVal
{

  // return the type
  // public String
  // toString ();
  //
  // public Object
  // get ();
  //
  // public String
  // type (); // is this necessary?

  

  /*
   * Add some boolean methods that will be used by the classes that extend this
   * one.
   */

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

  public <T> int compareTo(T val)
  {
    return this.toString().compareTo(val.toString());
  }

  public Object get()
    throws Exception
  {
    return null;
  }

  public Object type()
  {
    return null;
  }

}
