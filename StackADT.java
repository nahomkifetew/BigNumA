/**
 * Stack interface
 * 
 * @author Nahom Kfietew
 *
 * @param <T>
 *            generic type
 * @version 10/13/2022
 */
public interface StackADT<T> {

    /**
     * Interface for stack data structure.
     * 
     * @param <T>
     *            The type of objects that the stack will hold.
     * 
     * @author Nahom Kifetew, Rami Gorle
     * @version 7/15/2022
     */

    /**
     * Checks if the stack is empty.
     * 
     * @return Returns true if the stack is empty and false otherwise.
     */
    public boolean isEmpty();


    /**
     * Checks the item at the top of the
     * stack without removing it.
     * 
     * @return Item at the top of the stack.
     */
    public T peek();


    /**
     * Removes the item at the top of
     * the stack.
     * 
     * @return The item that was removed.
     */
    public T pop();


    /**
     * Pushes an item onto the stack.
     * 
     * @param item
     *            Item to be pushed
     *            onto the stack.
     */
    public void push(T item);


    /**
     * Number of items in the stack.
     * 
     * @return The number of items in
     *         the stack.
     */
    public int size();


    /**
     * Removes all of items from the stack).
     */
    public void clear();

}
