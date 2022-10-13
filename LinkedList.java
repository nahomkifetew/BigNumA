/**
 * Linked List implementation
 *
 * @author Nahom Kifetew
 * @param <T>
 *            generic type to represent
 * @version 10/13/2022
 */

public class LinkedList<T> implements LinkedListADT<T> {

    private LinkNode<T> head;
    private int size;

    /**
     * Class constructor
     */
    public LinkedList() {
        head = null;
        size = 0;

    }


    /**
     * Gets the head pointer of the linked list
     * 
     * @return head
     *         the head pointer of the linked list
     */
    public LinkNode<T> getHead() {
        return head;
    }


    /**
     * Setter method
     * 
     * @param h
     *            head node
     */
    public void setHead(LinkNode<T> h) {
        head = h;
    }


    @Override
    public int size() {

        return size;
    }


    @Override
    public void add(T data) {

        if (data == null) {
            throw new IllegalArgumentException("Object is null");
        }

        LinkNode<T> current = head;

        // Initialize Node in case of having no elements
        if (isEmpty()) {
            head = new LinkNode<T>(data);
        }
        else {

            // Start from the head then add element at the end
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(new LinkNode<T>(data));
        }

        size++;
    }


    @Override
    public boolean isEmpty() {

        return size == 0;
    }


    @Override
    public String toString() {
        String result = "[";

        LinkNode<T> current = head;
        while (current != null) {
            result += "" + current.getData();
            current = current.getNext();
            if (current != null) {
                result += ", ";
            }
        }
        result += "]";
        return result;
    }


    /**
     * Gets the object at the given position
     *
     * @param index
     *            where the object is located
     * @return The object at the given position
     * @throws IndexOutOfBoundsException
     *             if no node at the given index
     */
    public T get(int index) {
        LinkNode<T> current = head;
        int currentIndex = 0;
        T data = null;
        while (current != null) {
            if (currentIndex == index) {
                data = current.getData();
            }
            currentIndex++;
            current = current.getNext();
        }

        // check if the data was null...
        if (data == null) {
            // ... if so throw an exception
            throw new IndexOutOfBoundsException("Index exceeds the size.");
        }
        return data;
    }

}
