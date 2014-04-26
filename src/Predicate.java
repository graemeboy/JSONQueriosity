/**
 * Simple predicates.
 */
public interface Predicate<T>
{
  /**
   * Determine if the predicate holds on a particular value.
   */
  public boolean test(T val);
} // interface Predicate<T>
