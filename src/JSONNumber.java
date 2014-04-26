/**
 * A class for representing numbers in JSON
 * 
 * @author Graeme Boy, Vasilisa Bashlovkina
 * @created April 23, 2014
 */
import java.math.BigDecimal;
import java.math.BigInteger;

public class JSONNumber
    extends JSONVal
{

  // +--------+----------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * String representing the number
   */
  String number;

  /**
   * Flag that is set if this number has a decimal point
   */
  boolean isDecimal;

  // +--------------+----------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Make a JSONNumber that might contain a decimal point
   * 
   * @param number
   * @param decimal
   *          (set if number contains a decimal point
   * @pre number must be correctly formatted: start with a minus sign or a digit
   *      other than 0, contain a single decimal point or no decimal point and
   *      consist entirely of digits otherwise
   * 
   */
  public JSONNumber(String number, boolean decimal)
  {
    this.number = number.trim();
    this.isDecimal = decimal;
  } // JSONNumber(String, boolean)

  /**
   * Make a JSONNumber without a decimal point
   * 
   * @param num
   * @pre number must be correctly formatted: start with a minus sign or a digit
   *      other than 0, contain a single decimal point or no decimal point and
   *      consist entirely of digits otherwise
   */
  public JSONNumber(String num)
  {
    this.number = num;
    this.isDecimal = false;
  }// JSONNumber(String)

  // +---------+---------------------------------------------------------
  // | Methods |
  // +---------+
  /**
   * Get the value of this number in BigDecimal form
   * 
   * @throws Exception
   *           if the number isn't correctly formatted
   */
  public BigDecimal get()
    throws Exception
  {
    try
      {
        BigDecimal value = new BigDecimal(this.number);
        return value;
      }// try
    catch (Exception e)
      {
        throw new Exception("Incorrect formatting of number" + this.number);
      }// catch
  }// get()

  /**
   * Get the value of this number in BigInteger form
   * 
   * @throws Exception
   *           if the number isn't correctly formatted
   */
  public BigInteger getBigInteger()
    throws Exception
  {
    try
      {
        BigInteger value = new BigInteger(this.number);
        return value;
      }// try
    catch (Exception e)
      {
        throw new Exception("Incorrect formatting of number" + this.number);
      }// catch
  }// getBigInteger()

  /**
   * Get the value of this number in int form
   * 
   * @return integer value of number
   */
  public int getInteger()
  {
    return Integer.parseInt(this.number);
  }// getInteger()

  /**
   * Get the string representing this number
   */
  public String toString()
  {
    return this.number;
  }// toString()

  /**
   * Get the type of the JSONNumber
   * 
   * @return true
   */
  public boolean isNumber()
  {
    return true;
  } // isNumber()

  /**
   * Compare this number to another
   * 
   * @param val
   *          , the number compared to this
   * @return 0 is this == val
   * @return an int i > 0 if this > val
   * @return an int i < 0 if this < val
   * @throws Exception
   */
  public <T> int compareTo(T val)
  {
    return (new BigDecimal(this.number)).compareTo(new BigDecimal(
                                                                  val.toString()));
  }//compareTo (T)

}// JSONNumber class
