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

  public JSONVal()
  {

  }

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
