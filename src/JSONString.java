public class JSONString
    extends
      JSONVal
{
  String string;

  // Constructors
  public JSONString (String stringIn)
  {
    this.string = stringIn;
  } // JSONString

  public String
    toString ()
  {
    return this.string;
  }

  public String
    get ()
  {
    return this.string;
  }

  public String
    type ()
  {
    return "String";
  } // type()

  public int
    compareTo (String val)
  {
    return (this.string.compareTo ((String) val));
  } // compareTo
}
