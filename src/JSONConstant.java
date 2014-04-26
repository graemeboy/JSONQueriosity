/**
 * A class for JSON constants - null, true and false
 * 
 * @author Graeme Boy, Vasilisa Bashlovkina
 * @created April 23, 2014
 * 
 */

public class JSONConstant
    extends JSONVal
{
  // +--------+----------------------------------------------------------
  // | Fields |
  // +--------+
  /**
   * Flag for true
   */
  boolean isTrue;
  /**
   * Flag that is set if this is null
   */
  boolean isNull;

  // +--------------+----------------------------------------------------
  // | Constructors |
  // +--------------+
  /**
   * Make a new constant based on the first char of specialIn
   * @param specialIn
   */
  public JSONConstant(char specialIn)
  {
    if (specialIn == 't')
      {
        isTrue = true;
      } // if
    else if (specialIn == 'n')
      {
        isNull = true;
      } // else if
  } // JSONSpecial

  public Boolean get()
  {
    if (isNull)
      {
        return null;
      } // if

    return this.isTrue;
  } // get ()

  public String toString()
  {
    if (isNull)
      {
        return "null";
      } // if
    return String.valueOf(this.isTrue);
  } // toString ()

  public boolean isConstant()
  {
    return true;
  } // type()

  public int compareTo(boolean val)
  {
    // This is somewhat awkward to do.
    if (this.get() == val)
      {
        return 0;
      }
    else
      {
        return -1;
      }
  }

  public <T> int compareTo(T val)
  {
    // This is somewhat awkward to do.
    return val.toString().compareTo(this.toString());
    //    if ((val.equals ("true") && this.isTrue && this.isNull == false)
    //        || (val.equals ("false") && this.isTrue == false && this.isNull == false)
    //        || (val.equals ("null") && this.isNull))
    //      {
    //        return 0;
    //      } // else if
    //
    //    return -1;
  }
} // class JSONSpecial
