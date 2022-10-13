/**
 * Node implementation for Linked List
 * 
 * @author Nahom Kifetew, Josh Sapirstein
 *
 * @param <D>
 *            generic type
 * @version 10/12/2022
 */
public class LinkNode<D> {

    // The data element stored in the node.
    private D data;

    // The next node in the sequence.
    private LinkNode<D> next;

    /**
     * Creates a new node with the given data
     *
     * @param dataValue
     *            the data to put inside the node
     */
    public LinkNode(D dataValue) {
        data = dataValue;
    }


    /**
     * Sets the node after this node
     *
     * @param nextAddress
     *            the node after this one
     */
    public void setNext(LinkNode<D> nextAddress) {
        next = nextAddress;
    }


    /**
     * Gets the next node
     *
     * @return the next node
     */
    public LinkNode<D> getNext() {
        return next;
    }


    /**
     * Gets the data in the node
     *
     * @return the data in the node
     */
    public D getData() {
        return data;
    }


    /**
     * Used To Set A Data
     * 
     * @param data
     *            the data stored in the Node object.
     */
    public void setData(D data) {
        this.data = data;
    }

}
