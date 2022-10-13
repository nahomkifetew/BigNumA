import java.util.EmptyStackException;
import student.TestCase;

/**
 * A tester class for the Stack.java class
 * 
 * @author Nahom Kifetew
 * @version 10/13/2022
 */

public class StackTest extends TestCase {

    // Field variables
    private Stack<String> s1;
    private Stack<String> s2;

    /**
     * initializes variables
     */
    public void setUp() {

        s1 = new Stack<String>();
        s2 = new Stack<String>();
        s1.push("A");
        s1.push("B");
        s1.push("C");

    }


    /**
     * test if stack is empty
     */
    public void testIsEmpty() {

        assertFalse(s1.isEmpty());

        s1.clear();
        assertTrue(s1.isEmpty());

    }


    /**
     * tests peek method
     */
    public void testPeek() {

        Exception exception = null;
        try {
            s2.peek();
        }
        catch (Exception e) {
            exception = e;
        }
        assertTrue("removeFront is giving an exception",
            exception instanceof EmptyStackException);
    }


    /**
     * tests pop method
     */
    public void testaPop() {
        assertEquals("C", s1.pop().toString());
        assertEquals(2, s1.size());

        Exception exception = null;
        try {
            s2.pop();
        }
        catch (Exception e) {
            exception = e;
        }
        assertTrue("removeFront is giving an exception",
            exception instanceof EmptyStackException);
    }


    /**
     * tests push method
     */
    public void testPush() {
        s1.push("D");
        assertEquals("D", s1.peek().toString());

        assertEquals(s1.size(), 4);
        int n = 0;
        while (n <= 995) {
            s1.push("N");
            n++;
            assertEquals(s1.size(), 4 + n);
            // assertFalse(n > 995);
        }
        assertTrue(n > 995);
        assertEquals(s1.size(), 1000);
        // Make a test where it includes expandCapacity
    }


    /**
     * tests size method
     */
    public void testSize() {
        assertEquals(3, s1.size());
        assertEquals(0, s2.size());
    }


    /**
     * tests clear method
     */
    public void testClear() {

        s1.clear();
        assertEquals(0, s1.size());
    }


    /**
     * tests toString method
     */
    public void testToString() {

        assertEquals("[, , ]", s1.toString());
        assertEquals("[]", s2.toString());

        // CHECK THIS
    }

}
