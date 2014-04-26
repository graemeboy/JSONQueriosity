import java.math.BigDecimal;
import java.util.Comparator;

/**
 * Helpers for working with predicates. From Exam 2 Problem 2 by Vasilisa
 * Bashlovkina and Samuel A. Rebelsky
 */
public class Pred
{
  // +-----------+-------------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * A method that accepts any parameter.
   */
  public static Predicate<Object> ACCEPT = new Predicate<Object>()
    {
      public boolean test(Object val)
      {
        return true;
      }
    }; // new Predicate<Object>()

  /**
   * A method that rejects any parameter.
   */
  public static Predicate<Object> REJECT = new Predicate<Object>()
    {
      public boolean test(Object val)
      {
        return false;
      } // test(Object)
    }; // new Predicate<Object>

  // +----------------+--------------------------------------------------
  // | Static Methods |
  // +----------------+

  /**
   * Create a new predicate that holds when both of two predicates hold.
   * 
   * @return pred a predicate
   * @pre none
   * @post pred.test(val) holds if and only if left.test(val) holds and
   *       right.test(val) holds.
   */
  public static <T> Predicate<T> and(final Predicate<? super T> left,
                                     final Predicate<? super T> right)
  {
    return new Predicate<T>()
      {
        public boolean test(T val)
        {
          return left.test(val) && right.test(val);
        }// test(T)intermediateResult = this.filter(Pred.greater(key, (JSONVal) val));
      }; // new Predicate<T>
  } // and(Predicate<T>, Predicate<T>)

  /**
   * Create a new predicate that holds when another predicate does not hold, and
   * vice versa.
   * 
   * @return pred a predicate
   * @pre none
   * @post pred.test(val) holds if and only if derp.test(val) does not hold.
   */
  public static <T> Predicate<T> not(final Predicate<T> derp)
  {
    return new Predicate<T>()
      {
        public boolean test(T val)
        {
          return !derp.test(val);
        }// test(T)
      }; // new Predicate<T>
  } // not(Predicate<T>)

  /**
   * Create a new predicate that holds when either or both of two predicates
   * hold.
   * 
   * @return pred a predicate
   * @pre none
   * @post pred.test(val) holds if and only if left.test(val) holds or
   *       right.test(val) holds.
   */
  public static <T> Predicate<T> or(final Predicate<? super T> left,
                                    final Predicate<? super T> right)
  {
    return new Predicate<T>()
      {
        public boolean test(T val)
        {
          return left.test(val) || right.test(val);
        }// test(T)
      }; // new Predicate<T>
  } // or(Predicate<T>, Predicate<T>)

  //  /**
  //   * Returns a predicate that holds if val is equal to otherVal
  //   * 
  //   * @param otherValintermediateResult = this.filter(Pred.greater(key, (JSONVal) val));
  //   * @pre otherVal and val have the same type
  //   */
  //  public static <T> Predicate<T> equal(final T obj1)
  //  {
  //    return new Predicate<T>()
  //      {
  //        @Override
  //        public boolean test(T val)
  //        {
  //          return obj1.equals(val);
  //        }// test
  //      };// new Predicate(T)
  //  }// equal(T,T);
  //
  //  /**
  //   *  Val predicate
  //   */
  //  public static <T> Predicate<T> eqVal(final JSONObject obj, final String key)
  //  {
  //    return new Predicate<T>()
  //      {
  //        @Override
  //        public boolean test(T val)
  //        {
  //          // Find the matching key in the object and get its value.
  //          // Then compare it to the parameter
  //          return obj.get(key).compareTo(val) == 0;
  //        }// test
  //      };// new Predicate(T)
  //  }//eqVal

  /**
   *  Determine whether given key has given value
   *  
   *  @param key
   *  @param value
   *  @pre the parameter object of the returned Predicate must be a JSONObject           
   *  @pre key must be in the parameter object
   *  @post val is unchanged
   *  @return true if the value of the key in the object matches given value
   *  @return false otherwise
   */
  public static <T> Predicate<T> equal(final String key, final JSONVal value)
  {
    return new Predicate<T>()
      {
        @Override
        public boolean test(T object)
        {
          // Assume parameter is a JSONObject. Find the value corresponding 
          // to the given key and compare it to the given value 
          JSONVal temp = ((JSONObject) object).get(key);
          return ((temp != null) && (value.compareTo(temp) == 0));
        }// test
      };// new Predicate(T)
  }//equal(String, JSONVal)

  /**
   * Greater 
   */
  public static <T> Predicate<T> greater(final String key, final JSONVal value)
  {
    return new Predicate<T>()
        {
          @Override
          public boolean test(T object)
          {
            JSONVal temp = ((JSONObject) object).get(key);
            System.out.println("object greater is " + temp.toString());
            if (temp.isNumber())
              return ((JSONNumber)temp).compareTo(value) > 0;
            return ((temp != null) && (temp.compareTo(value) > 0));
            //return value.compareTo(((JSONObject) object).get(key)) > 0;
          }// test
        };// new Predicate(T)
  }//greater(String, JSONVal)
  
  /**
   * Smaller
   */
  public static <T> Predicate<T> smaller(final String key, final JSONVal value)
  {
    return new Predicate<T>()
        {
          @Override
          public boolean test(T object)
          {
            JSONVal temp = ((JSONObject) object).get(key);
            if (temp.isNumber())
              {
                try
                  {
                    return ((JSONNumber)temp).compareTo(new BigDecimal(value.toString()))  < 0;
                  }
                catch (Exception e)
                  {
                    return false;
                  }
              }// if number
            return ((temp != null) && (temp.compareTo(value) < 0));
          }// test
        };// new Predicate(T)
  }//smaller(String, JSONVal)
  /**
   * Greater with comparators
   * @param obj
   * @param order
   * @return
   */
  public static <T> Predicate<T>
    greater(final T obj, final Comparator<T> order)
  {
    return new Predicate<T>()
      {
        @Override
        public boolean test(T val)
        {
          return order.compare(val, obj) > 0;
        }// test
      };// new Predicate(T)
  }// greater(T, Comparator<T>)

  /**
   * Greater
   */
  public static <T> Predicate<T>
    smaller(final T obj, final Comparator<T> order)
  {
    return new Predicate<T>()
      {
        @Override
        public boolean test(T val)
        {
          return order.compare(val, obj) < 0;
        }// test
      };// new Predicate(T)
  }// smaller(T, Comparator<T>);

  /**
   * Convert a predicate that works on a superclass to a predicate that works on
   * a subclass.
   */
  public static <T> Predicate<T> munge(final Predicate<? super T> pred)
  {
    return new Predicate<T>()
      {
        public boolean test(T val)
        {
          return pred.test(val);
        } // test(T)
      }; // new Predicate<T>
  } // munge(Predicate)
} // class Pred
