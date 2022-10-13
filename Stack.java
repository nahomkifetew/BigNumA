import java.util.EmptyStackException;

/**
 * Array based stack data structure implementation
 * 
 * @author Nahom Kifetew, Rami Gorle
 *
 * @param <T>
 *            generic type
 * @version 7/19/2022
 */
public class Stack<T> implements StackADT<T> {

    private T[] array;

    private int size = 0;

    private int capacity;

    /**
     * Default Constructor
     * 
     * @param capacity
     *            how big the array holds
     */
    @SuppressWarnings("unchecked")
    public Stack(int capacity) {

        T[] stack = (T[])new Object[capacity];
        array = stack;
        size = 0;
        this.capacity = capacity;
    }


    /**
     * Default Constructor
     */
    public Stack() {
        this(50);
    }


    @Override
    public boolean isEmpty() {

        return size == 0;
    }


    @Override
    public T peek() {

        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return array[size - 1];
    }


    @Override
    public T pop() {

        T t = peek();
        size--;

        return t;
    }


    @Override
    @SuppressWarnings("unchecked")
    public void push(Object item) {

        if (size >= capacity) {
            expandCapacity();
        }
        array[size] = (T)item;
        size++;
    }


    /**
     * The size of the stack
     */
    @Override
    public int size() {

        return size;
    }


    /**
     * Clears our stack
     */
    @Override
    public void clear() {

        size = 0;
    }


    /**
     * Expands the capacity of the stack by doubling its current capacity.
     */
    private void expandCapacity() {
        @SuppressWarnings("unchecked")
        T[] newArray = (T[])new Object[this.capacity * 2];

        int i = 0;
        while (i < this.capacity) {
            newArray[i] = this.array[i];
            i++;
        }

        this.array = newArray;
        this.capacity *= 2;
    }


    /**
     * Returns the string representation of the stack.
     * 
     * [] (if the stack is empty)
     * [bottom, item, ..., item, top] (if the stack contains items)
     * 
     * @return the string representation of the stack.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append('[');

        boolean firstItem = true;

        for (int i = 0; i < this.size(); i++) {
            if (!firstItem) {
                builder.append(", ");
            }

            else {
                firstItem = false;
            }
        }
        builder.append(']');
        return builder.toString();
    }

}
