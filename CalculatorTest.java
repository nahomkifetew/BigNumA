import java.io.File;
import java.io.IOException;
import java.util.EmptyStackException;
import student.TestCase;

/**
 * 
 * @author Nahom Kifetew
 * @version 7/19/2022
 */
public class CalculatorTest extends TestCase {

    private Calculator calc;
    private File file;

    /**
     * Set up every method
     */
    public void setUp() {
        file = new File("tester1");
        calc = new Calculator(file);
    }


    /**
     * Test method for fileReader() in Calculator class
     * 
     * @throws IOException
     *             exception thrown if file not found
     */
    public void testFileReader() throws IOException {
        calc.fileReader();
    }


    /**
     * Test method for compute() in Calculator class
     */
    public void testCompute() {

        String[] equation1 = { "1", "2", "*", "3", "+" };
        String[] equation2 = { "1", "+", "+" };
        String[] equation3 = { "+", "1", "2" };
        String[] equation4 = { "1", "1", "+" };
        String[] equation5 = { "1", "1", "^" };
        String[] equation6 = { "1", "0", "^" };
        String[] equation7 = { "100", "2000", "*" };
        String[] equation8 = { "1", "2", "^" };

        String result1 = calc.compute(equation1);
        String result4 = calc.compute(equation4);
        String result5 = calc.compute(equation5);
        String result6 = calc.compute(equation6);
        String result7 = calc.compute(equation7);
        String result8 = calc.compute(equation8);

        assertEquals(result1, "[5]");
        assertEquals(result4, "[2]");
        assertEquals(result5, "[1]");
        assertEquals(result6, "[1]");
        assertEquals(result7, "[0, 0, 0, 0, 0, 2]");
        assertEquals(result8, "[1]");

        Exception exception1 = null;
        try {
            String result2 = calc.compute(equation2);
        }
        catch (Exception exp) {
            exception1 = exp;
        }

        assertNotNull(exception1);
        assertTrue(exception1 instanceof EmptyStackException);

        Exception exception2 = null;
        try {
            String result3 = calc.compute(equation3);
        }
        catch (Exception exp) {
            exception2 = exp;
        }

        assertNotNull(exception1);
        assertTrue(exception2 instanceof EmptyStackException);

    }


    /**
     * 
     */
    public void testAddLL() {

        LinkedList<Integer> num1 = new LinkedList<Integer>();
        LinkedList<Integer> num2 = new LinkedList<Integer>();
        LinkedList<Integer> numZero = new LinkedList<Integer>();

        LinkedList<Integer> result = new LinkedList<Integer>();

        // 187652
        num1.add(2);
        num1.add(5);
        num1.add(6);
        num1.add(7);
        num1.add(8);
        num1.add(1);

        // 187652
        num2.add(2);
        num2.add(5);
        num2.add(6);
        num2.add(7);
        num2.add(8);
        num2.add(1);

        // 375304
        result = calc.addLL(num2, num2);
        assertEquals("[4, 0, 3, 5, 7, 3]", result.toString());

        result = calc.addLL(numZero, numZero);
        assertEquals("[]", result.toString());

    }

}
