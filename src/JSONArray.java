import java.math.BigDecimal;
import java.util.ArrayList;

public class JSONArray
    extends
      JSONVal
{
  // Make an arraylist of JSONVals

  ArrayList<JSONVal> array;

  public JSONArray ()
  {
    this.array = new ArrayList<JSONVal> ();
  } // JSONArray(String)

  public void
    add (JSONVal valIn)
  {
    this.array.add (valIn);
  } // add()

  public ArrayList<JSONVal>
    get ()
  {
    return this.array;
  } // get()

  public int
    size ()
  {
    return this.array.size ();
  } // size ()

  public String
    toString ()
  {
    String output = "[";
    int arrayLen = this.array.size ();
    for (int i = 0; i < arrayLen; i++)
      {

        output += this.array.get (i).toString ();
        if (i < arrayLen - 1)
          {
            output += ", ";
          } // if
      } // for
    output += "]";
    return output;
  } // toString()

  /**
   * Function where, returns and array list of all of the objects within this
   * JSONArray that match the key and value specified
   * 
   * @param key
   * @param val
   * @return
   */
  public <T>
    ArrayList
    where (String key, T val)
  {
    ArrayList<JSONObject> results = new ArrayList ();
    JSONObject tempObj;
    // Needs to be an array of objects
    for (int i = 0; i < this.size (); i++)
      {
        // For each object
        if (this.array.get (i).type ().equals ("Object"))
          {
            if ((tempObj = ((JSONObject) this.array.get (i))).get (key)
                                                             .compareTo (val) == 0)
              {
                // The object has a key-value pair that matches
                results.add (tempObj);
              } // if
          } // if
      } // for
    return results;
  } // where

  public <T>
    ArrayList
    whereLess (String key, int maxCompare)
  {
    ArrayList<JSONObject> results = new ArrayList ();
    // I will make this more readable than where, by using more fields.
    JSONObject tempObj;
    JSONNumber tempNum;
    // Needs to be an array of objects
    // for (int i = 0; i < this.size (); i++)
    // {
    // // For each object
    // if (this.array.get (i).type ().compareTo ("Object") == 0)
    // {
    // // This object has the correct key
    // tempObj = (JSONObject) this.array.get (i);
    // tempNum = (JSONNumber) tempObj.get (key);
    // if (tempNum.lessThan (maxCompare))
    // {
    // // The object has a key-value pair that matches
    // results.add (tempObj);
    // } // if
    // } // if
    // } // for
    return results;
  } // where

  /**
   * function whereGreater
   * 
   * @param key
   * @param maxCompare
   * @return
   */
  public <T>
    ArrayList
    whereGreater (String key, int maxCompare)
  {
    // This could be abstrated to work with whereLess
    ArrayList<JSONObject> results = new ArrayList ();
    // I will make this more readable than where, by using more fields.
    JSONObject tempObj;
    JSONNumber tempNum;
    // Needs to be an array of objects
    // for (int i = 0; i < this.size (); i++)
    // {
    // // For each object
    // if (this.array.get (i).type ().compareTo ("Object") == 0)
    // {
    // // This object has the correct key
    // tempObj = (JSONObject) this.array.get (i);
    // tempNum = (JSONNumber) tempObj.get (key);
    // if (tempNum.greaterThan (maxCompare))
    // {
    // // The object has a key-value pair that matches
    // results.add (tempObj);
    // } // if
    // } // if
    // } // for
    return results;
  } // where

  /**
   * function Select, returns the value of a given key, if it satisfies a
   * comparison. E.g. select("name", "equals", "Graeme") would return my name in
   * an ArrayList of people with those keys.
   * 
   * @pre returnKey must not be an object
   * @param <T>
   * 
   * @param key
   * @param comparison
   * @param return key
   * @param val
   * @return
   */
  public <T>
    ArrayList<T>
    select (String returnKey, String comparison, String key, T val)
  {

    ArrayList<T> results = new ArrayList<T> ();
    JSONObject tempObj;
    // Needs to be an array of objects
    int compResult;
    JSONVal valToCompare;
    for (int i = 0; i < this.size (); i++)
      {
        // For each object
        if (this.array.get (i).type ().equals ("Object")
            && (valToCompare = (tempObj = ((JSONObject) this.array.get (i))).get (key)) != null)
          {
            
            if (valToCompare.type ().equals ("Number"))
              {
                compResult = ((JSONNumber) valToCompare).compareTo (Integer.parseInt (val.toString ()));
              } // if
            else if (valToCompare.type ().equals ("Constant"))
              {
                compResult = ((JSONConstant) valToCompare).compareTo (val);
              } // else if
            else
              {
                compResult = valToCompare.compareTo (val);
              } // else
            if ((comparison.equals ("=") && compResult == 0)
                || (comparison.equals ("<") && compResult < 0)
                || (comparison.equals (">") && compResult > 0))
              {
                // The object has a key-value pair that matches
                results.add ((T) tempObj.get (returnKey));
              } // if
          } // if
      } // for
    return results;
  }

  /**
   * Returns the element at i
   * 
   * @param i
   * @return
   */
  public JSONVal
    get (int i)
  {
    return this.array.get (i);
  }

  public String
    type ()
  {
    return "Array";
  } // type()

  public int
    compareTo (JSONArray val)
  {
    // Return 0 if String representation of JSONArray is the same.
    return (this.array.toString ().compareTo (val.toString ()));
  } // compareTo()
}