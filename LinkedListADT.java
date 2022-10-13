/**
 * Definitions for all of the methods of Linked List implementation
 *
 * @author Nahom Kifetew
 * @param <T>
 *            generic type
 * @version 10/13/2022
 */
public interface LinkedListADT<T> {

    /**
     * Gets the number of elements in the list
     *
     * @return the number of elements
     */
    public abstract int size();


    /**
     * Adds the object to the end of the list.
     *
     * @param obj
     *            the object to add
     * @throws IllegalArgumentException
     *             if obj is null
     */
    public abstract void add(T obj);


    /**
     * Checks if the array is empty
     *
     * @return if the array is empty
     */
    public abstract boolean isEmpty();


    /**
     * Returns a string representation of the list If a list contains A, B, and
     * C, the following should be returned "[A, B, C]" (Without the quotations)
     *
     * @return a string representing the list
     */
    public abstract String toString();
}
