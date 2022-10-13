import student.TestCase;

/**
 * Tester Class for LinkedList
 * 
 * @author Nahom Kifetew
 * @version 10/13/2022
 */
public class LinkedListTest extends TestCase {

    // Field variables
    private LinkedList<Integer> list1;
    private LinkedList<Integer> list2;

    /**
     * initialize variables
     */
    public void setUp() {

        list1 = new LinkedList<Integer>();
        list2 = new LinkedList<Integer>();
    }


    /**
     * Test the size method
     */
    public void testSize() {

        // Check if add method adds properly
        list1.add(1);
        list1.add(2);
        assertEquals(list1.size(), 2);

        // Check adding a null object
        Exception exception = null;
        try {
            list1.add(null);
        }
        catch (Exception exp) {
            exception = exp;
        }
        assertNotNull(exception);
        assertTrue(exception instanceof IllegalArgumentException);

    }


    /**
     * Test get method
     */
    public void testGet() {
        list2.add(1);
        list2.add(2);

        int num = list2.get(1);
        assertEquals(num, 2);

        int numEmpty = list2.get(0);
        assertEquals(numEmpty, 1);

        // Check null object
        Exception exception = null;
        try {
            list1.get(0);
        }
        catch (Exception exp) {
            exception = exp;
        }
        assertNotNull(exception);
        assertTrue(exception instanceof IndexOutOfBoundsException);

    }


    /**
     * Test add integer to linked list
     */
    public void testAdd() {
        list2.add(1);
        list2.add(2);
        list2.add(3);

        assertEquals(list2.size(), 3);

        // int num = list2.get(2);
        // assertEquals(num, 3);
        // assertEquals(list2.get(2), 3);

    }


    /**
     * Test if linked list is empty
     */
    public void testIsEmpty() {
        assertTrue(list2.isEmpty());

        assertTrue(list1.isEmpty());

        list1.add(1);
        assertFalse(list1.isEmpty());

        list2.add(1);
        // list2.remove();
        // assertTrue(list2.isEmpty());
        // assertEquals(list2.size(), 1);
        // Check remove function
    }


    /**
     * Test if integers are converted into a string
     */
    public void testToString() {
        list1.add(1);
        list1.add(2);
        assertEquals("[1, 2]", list1.toString());

        assertEquals("[]", list2.toString());
    }

}
