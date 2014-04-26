/**
 *  Simple predicates.
 *  
 *  From Exam 2 Problem 2 
 * @author Samuel A. Rebelsky
 * @author Vasilisa Bashlovkina
 */
public interface Predicate<T>
{
  /**
   * Determine if the predicate holds on a particular value.
   */
  public boolean test(T val);
} // interface Predicate<T>
