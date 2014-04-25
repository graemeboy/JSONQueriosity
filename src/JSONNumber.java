import java.math.BigDecimal;
import java.math.BigInteger;

public class JSONNumber
    extends
      JSONVal
{
  // Started saving number as a string for convenience.
  // Now I think it's a good idea, actually. Parse on get?
  // We probably ought to do them all as strings. Except arrays...
  String number;
  boolean isDecimal;

  public JSONNumber (String number, boolean decimal)
  {
    this.number = number.trim();
    this.isDecimal = decimal;

  } // JSONNumber

  public BigDecimal
    get ()
  {
    return new BigDecimal (this.number);
  }

  /**
   * function getBigInteger
   * 
   * @return BigInteger representation of this number
   */
  public BigInteger
    getBigInteger ()
  {
    return new BigInteger (this.number);
  }

  /**
   * function getInteger
   * 
   * @return integer value of number
   */
  public int
    getInteger ()
  {
    return Integer.parseInt (this.number);
  }

  /**
   * function isDecimal
   * 
   * @return boolean, whether decimal
   */
  public boolean
    isDecimal ()
  {
    return isDecimal;
  }

  public String
    toString ()
  { 
    return number;
  }// toString()

  public String
    type ()
  {
    return "Number";
  } // type()

  public <T>
    int
    compareTo (int val)
  {
    return get().compareTo(new BigDecimal(val));
  }

  /**
   * function lessThan, returns true if maxCompare < the integer value of this number
   * @param maxCompare
   * @return boolean
   */
  public boolean
    lessThan (int maxCompare)
  {
    return (getInteger () < maxCompare);
  }

  /**
   * function greaterThan, returns true if maxCompare < the integer value of this number
   * @param maxCompare
   * @return
   */
  public boolean
    greaterThan (int minCompare)
  {
    return (getInteger () > minCompare);
  }
}// JSONNumber class
