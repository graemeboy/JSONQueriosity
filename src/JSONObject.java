/**
 * A class that represents JSON objects using ArrayLists
 * 
 * @author Graeme Boy, Vasilisa Bashlovkina
 * @created April 23, 2014
 */
import java.util.ArrayList;

public class JSONObject
    extends
      JSONVal
{
  // Fields
  // Keys are only strings.
  ArrayList<String> keys;
  // A JSONVal can contain objects, numbers, strings, etc.
  ArrayList<JSONVal> vals;
  int i;

  // Constructors
  public JSONObject ()
  {
    keys = new ArrayList<String> ();
    vals = new ArrayList<JSONVal> ();
  } // JSONObject

  // Methods

  // Add a key to the key list
  public void
    addKey (String keyIn)
  {
    this.keys.add (keyIn);
  } // addKey (String)

  public void
    addVal (JSONVal val)
  {
    vals.add (val);
  } // addVal (JSONVal)

  /**
   * returns the value associated to the given key. Eff. is O(n), n = number of
   * keys
   * 
   * @param key
   * @return
   */
  public JSONVal
    get (String keyIn)
  {
    for (int i = 0; i < this.keys.size (); i++)
      {
        if (this.keys.get (i).compareTo (keyIn) == 0)
          {
            return this.vals.get (i);
          } // if
      } // for
    return null; // no such key was found.
  } // get()

  public String
    toString ()
  {

    String output = "{\n";
    JSONVal val;
    int keysLen = this.keys.size ();
    String tab = "    ";
    for (int i = 0; i < keysLen; i++)
      {
        val = this.vals.get (i);
        if (val == null)
          {
            return "null";
          } // if

        if (val instanceof JSONObject)
          {
            // This is awfully hacky, and only actually
            // looks good with json objects nested once
            tab = "  ";
          } // if

        output += tab + "\"" + this.keys.get (i) + "\": " + val.toString ();

        if (i < keysLen - 1)
          {
            output += ",";
          }
        output += "\n";

      } // for
    output += tab + "}";
    return output;
  } // toString()

  public Object
    get ()
  {
    return "[Object]";
  }

  public boolean
    isObject ()
  {
    return true;
  } // type()

  public <T>
    int
    compareTo (T val)
  {
    // This is somewhat awkward to do.
    // But return 0 if string representations are the same.
    return (this.toString ().compareTo (val.toString ()));
  }
}