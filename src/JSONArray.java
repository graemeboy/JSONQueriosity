import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A class that represents arrays in JSON
 * 
 * @author Graeme Boy, Vasilisa Bashlovkina
 * @created April 23, 2014
 */

public class JSONArray
    extends JSONVal
{
  // +--------+----------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The actual array
   */
  ArrayList<JSONVal> array;

  // +--------------+----------------------------------------------------
  // | Constructors |
  // +--------------+
  /**
   * Make an empty JSONArray
   */
  public JSONArray()
  {
    this.array = new ArrayList<JSONVal>();
  } // JSONArray(String)

  // +------------------+----------------------------------------------------
  // | Standard methods |
  // +------------------+
  
  /**
   *  Add a JSONVal to the 
   * @param valIn
   */
  public void add(JSONVal valIn)
  {
    this.array.add(valIn);
  } // add()

  public ArrayList<JSONVal> get()
  {
    return this.array;
  } // get()

  public int size()
  {
    return this.array.size();
  } // size ()

  public String toString()
  {
    String output = "[";
    int arrayLen = this.array.size();
    for (int i = 0; i < arrayLen; i++)
      {

        output += this.array.get(i).toString();
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
  public <T> ArrayList where(String key, T val)
  {
    ArrayList<JSONObject> results = new ArrayList();
    JSONObject tempObj;
    // Needs to be an array of objects
    for (int i = 0; i < this.size(); i++)
      {
        // For each object
        if (this.array.get(i).isObject())
          {
            if ((tempObj = ((JSONObject) this.array.get(i))).get(key)
                                                            .compareTo(val) == 0)
              {
                // The object has a key-value pair that matches
                results.add(tempObj);
              } // if
          } // if
      } // for
    return results;
  } // where(String, T)
  
  /**
   * Function where, returns and array list of all of the objects within this
   * JSONArray where the value of the key is smaller that int
   * 
   * @param key
   * @param val
   * @return
   */
  public <T> ArrayList whereLess(String key, int maxCompare)
  {
    ArrayList<JSONObject> results = new ArrayList();
    // I will make this more readable than where, by using more fields.
    JSONObject tempObj;
    JSONNumber tempNum;
    //Needs to be an array of objects
    for (int i = 0; i < this.size(); i++)
      {
        // For each object
        if (this.array.get(i).isObject())
          {
            // This object has the correct key
            tempObj = (JSONObject) this.array.get(i);
            tempNum = (JSONNumber) tempObj.get(key);
            if (tempNum.compareTo(maxCompare) < 0)
              {
                // The object has a key-value pair that matches
                results.add(tempObj);
              } // if
          } // if
      } // for
    return results;
  } // where

  /**
   * Function where, returns and array list of all of the objects within this
   * JSONArray where the value of the key is greater that int
   * 
   * @param key
   * @param val
   * @return
   */
  public <T> ArrayList whereGreater(String key, int maxCompare)
  {
    // This could be abstrated to work with whereLess
    ArrayList<JSONObject> results = new ArrayList();
    // I will make this more readable than where, by using more fields.
    JSONObject tempObj;
    JSONNumber tempNum;
    //Needs to be an array of objects
    for (int i = 0; i < this.size(); i++)
      {
        // For each object
        if (this.array.get(i).isObject())
          {
            // This object has the correct key
            tempObj = (JSONObject) this.array.get(i);
            tempNum = (JSONNumber) tempObj.get(key);
            if (tempNum.compareTo(maxCompare) > 0)
              {
                // The object has a key-value pair that matches
                results.add(tempObj);
              } // if
          } // if
      } // for
    return results;
  } // where

  /**
   * Make an array of JSONVals for which the predicate holds
   * 
   * @param pred
   *          , a Predicate
   * @pre pred takes a single argument - a JSONObject
   */
  public <T> JSONArray filter(Predicate<T> pred)
  {
    // Make an array where objects that pass the predicate will be placed
    JSONArray result = new JSONArray();
    // Make a filtered iterator for this
    FilteredIterator<JSONVal> it = new FilteredIterator(this.iterator(), pred);
    // Go through this JSONArray and add elements that pass
    while (it.hasNext())
      {
        result.add(it.next());
      }// while hasNext
    return result;
  }// filter

  /**
   * Make an iterator
   * 
   * @return an iterator of this.array
   */
  public Iterator iterator()
  {
    return this.array.iterator();
  }// iterator()

  
  /**
   * Make an array of values of the returnKey for each JSONObject
   * in the array that satisfies the comparison between the value 
   * of the key parameter and the parameter val
   * 
   * @param returnKey, its value might be in the returned array 
   * @param comparison, indicates how val and the value of key will be compared
   * @param key, the key whose value gets compared
   * @param val
   * @pre comparison must be "=", "<" or ">"
   * @both key and returnKey must be in each member-object
   * @return an array of values of the returnKey for objects that satisfy the
   *         comparison
   * @throws Exception
   */
  public ArrayList<JSONVal> selectFilter(String returnKey, String comparison,
                                         String key, JSONVal val)
    throws Exception
  {
    // Make a new list where values will be put
    JSONArray intermediateResult = new JSONArray();
    char comparChar = comparison.charAt(0);
    // Filter the this into the array
    switch (comparChar)
      {
        case '=':
          intermediateResult = this.filter(Pred.equal(key, val));
          break;
        case '>':
          intermediateResult = this.filter(Pred.greater(key, val));
          break;
        case '<':
          intermediateResult = this.filter(Pred.smaller(key, val));
          break;
        default:
          throw new Exception(
                              "Incorrectly formatted input: expected =, > or <, given "
                                  + comparison);
      }// switch

    Iterator it = intermediateResult.iterator();
    ArrayList<JSONVal> result = new ArrayList<>();
    // Extract the values of the returnKey from the filtered array
    while (it.hasNext())
      {
        result.add(((JSONObject) (it.next())).get(returnKey));
      }// while hasNext
    return result;
  }// selsectFilter(String, String, String, T)

  /**
   * Get the element at position i
   * 
   * @param i
   * @pre i < this.size()
   * @return the JSONVal at position i
   */
  public JSONVal get(int i)
  {
    return this.array.get(i);
  }// get(int)

  public boolean isArray()
  {
    return true;
  } // isArray()
  
/**
 * Comapre this to another JSONArray
 * @param val
 * @return
 */
  public int compareTo(JSONArray val)
  {
    // Return 0 if String representation of JSONArray is the same.
    return (this.array.toString().compareTo(val.toString()));
  } // compareTo()
  
  /**
   * This function is not used because it is duplicated by selctFilter
   *  Select, returns the value of a given key, if it satisfies a
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
   *          what we are comparing to
   * @return
   */
  @SuppressWarnings("unchecked")
  public <T> ArrayList<T> select(String returnKey, String comparison,
                                 String key, T val)
  {

    ArrayList<T> results = new ArrayList<T>();
    JSONObject tempObj;
    // Needs to be an array of objects
    int compResult;
    JSONVal valToCompare;
    for (int i = 0; i < this.size(); i++)
      {
        // For each object
        if (this.array.get(i).isObject()
            && (valToCompare =
                (tempObj = ((JSONObject) this.array.get(i))).get(key)) != null)
          {
            if (valToCompare.isNumber())
              {
                compResult =
                    ((JSONNumber) valToCompare).compareTo(Integer.parseInt(val.toString()));
              } // if
            else if (valToCompare.isConstant())
              {
                compResult = ((JSONConstant) valToCompare).compareTo(val);
              } // else if
            else
              {
                compResult = valToCompare.compareTo(val);
              } // else
            if ((comparison.equals("=") && compResult == 0)
                || (comparison.equals("<") && compResult < 0)
                || (comparison.equals(">") && compResult > 0))
              {
                // The object has a key-value pair that matches
                results.add((T) tempObj.get(returnKey));
              } // if
          } // if
      } // for
    return results;
  } // select

}// JSONArray class
