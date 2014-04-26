import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterators that filter elements.
 */
public class FilteredIterator<T>
    implements Iterator<T>
{
  // +--------+----------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The iterator we're filtering.
   */
  Iterator<T> base;

  /**
   * The predicate used to do the filtering.
   */
  Predicate<? super T> pred;

  /**
   * Flag that keeps track of whether hasNext was previously called
   */
  boolean hasNexted;
  /**
   * Flag that keeps track of whether next was previously called
   */
  boolean nexted;

  /**
   * Object that was previously returned by base
   */
  T recent;

  /**
   * Most recent result of hasNext()
   */

  boolean recentHasNext;

  // +--------------+----------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a version of base which returns only the values for which pred holds.
   */
  public FilteredIterator(Iterator<T> base, Predicate<? super T> pred)
  {
    this.base = base;
    this.pred = pred;
    this.recent = null;
    this.hasNexted = false;
    this.nexted = false;
    this.recentHasNext = false;
  } // FilteredIterator(Iterator<T>, Predicate<? super T>)

  // +---------+---------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Determine if any elements remain for which pred holds.
   * 
   * @pre none
   * @post this has not moved or returned anything
   * @post hasNexted flag has been set to true
   * @return true if there is a next element for which pred holds false
   *         otherwise
   */
  public boolean hasNext()
  {
    if (this.hasNexted)
      return this.recentHasNext;
    this.hasNexted = true;
    T temp = null;

    while (this.base.hasNext())
      {
        temp = this.base.next();
        if (this.pred.test(temp))
          {
            this.recent = temp;
            this.recentHasNext = true;
            return true;
          }// if this.pred holds
      }// while this.base.hasNext
    this.recentHasNext = false;
    return false;
  } // hasNext()

  /**
   * Get the next element for which pred holds.
   * 
   * @pre this.hasNext() == true
   * @post this.moved to the next element
   * @post hasNexted flag is set to false nexted flag is set to true
   * @return the element immediately after this
   * @throws NoSuchElementException
   *           when there are no more elements for which pred holds.
   */
  public T next()
    throws NoSuchElementException
  {

    if (this.hasNexted)
      {
        this.hasNexted = false;
        this.nexted = true;
        return this.recent;
      }// hasNext was called
    else
      {
        if (this.hasNext())
          {
            this.hasNexted = false;
            this.nexted = true;
            return this.recent;
          }// if hasNext
        else
          throw new NoSuchElementException();
      }// hasNext wasn't called
  } // next()

  /**
   * Remove the element most recently returned by next.
   * 
   * @pre nexted == true
   * @post the element previously returned by next() is removed
   * @post hasNexted flag is set to false nexted flag is set to false
   * @throws IllegalStateException
   *           If the next method has not yet been called, or the remove method
   *           has already been called after the last call to the next method,
   *           or if the hasNext method has been called after the last call to
   *           the next method.
   */
  public void remove()
    throws IllegalStateException
  {
    if (!this.nexted)
      throw new IllegalStateException(
                                      "The next menthod has not yet been called");

    this.hasNexted = false;
    this.nexted = false;
    this.base.remove();
  } // remove()
} // class FilteredIterator<T>
