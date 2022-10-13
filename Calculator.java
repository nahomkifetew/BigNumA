import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Scanner;

/**
 * This class does needed calculation
 * 
 * @author Nahom Kifetew
 * @version 7/19/2022
 *
 */
public class Calculator {

    private File file;

    /**
     * 
     * @param fileInput
     *            given file to parse and calculate
     */
    public Calculator(File fileInput) {
        file = fileInput;
    }


    /**
     * Get file and parse line by line then send an expression at a time
     * 
     * @throws IOException
     *             if file is not proper
     */
    public void fileReader() throws IOException {

        // File holding expressions
        Scanner scan = new Scanner(file);
        String operators = "+*/";
        // Loop to iterate and get each line(expression)
        while (scan.hasNextLine()) {

            // Get the expression/equation
            String expression = scan.nextLine(); // 123 456 * 789 +

            // Turn expression/equation into array
            String[] arrOfStr = expression.split(" "); // ["123", "456", "*",
                                                       // "789", "+"]
            int signCount = 0;
            for (String element : arrOfStr) {
                if (operators.contains(element)) {
                    signCount++;
                }
            }

            if (signCount >= (arrOfStr.length) / 2) {
                System.out.println(expression + " =");
                continue;
            }

            if (operators.contains(arrOfStr[0]) || !(operators.contains(
                arrOfStr[arrOfStr.length - 1]))) {
                System.out.println(expression + " ="); // Write to file

                continue;
            }

            // Send one expression at a time to be computed
            String finalResult = compute(arrOfStr);
            finalResult = finalResult.replace("[", "");
            finalResult = finalResult.replace("]", "");
            finalResult = finalResult.replace(",", "");
            finalResult = finalResult.replace(" ", "");

            StringBuilder reverseChar = new StringBuilder(finalResult);
            finalResult = reverseChar.reverse().toString();
            System.out.println(expression + " = " + finalResult);

        }

    }


    /**
     * Does postfix computation
     * 
     * @param array
     *            array of expression
     * 
     * @return String
     *         representing the final value
     * 
     */
    public String compute(String[] array) {

        // ["123", "456", "*", "789", "+"]
        String operators = "*+-/^";
        Stack<LinkedList<Integer>> stack = new Stack<LinkedList<Integer>>();

        // Step 1: iterate through the array and get each element in it
        for (String element : array) {

            // Reverse the element --> "123" -> "321"
            StringBuilder reverseChar = new StringBuilder(element);
            element = reverseChar.reverse().toString();

            // if element is not one of these [* + - /^]
            if (!operators.contains(element)) {

                // Turn the element into into array of chars
                char[] digits = element.toCharArray(); // "123" --> ["1", "2",
                                                       // "3"]

                // Convert the array of chars to link list
                LinkedList<Integer> linkListNum = listConverter(digits);

                // Add link list onto stack
                stack.push(linkListNum);
            }

            switch (element) {
                case "*":
                    LinkedList<Integer> product = multiply(stack.pop(), stack
                        .pop());
                    stack.push(product);
                    break;

                case "+":
                    LinkedList<Integer> sum = addLL(stack.pop(), stack.pop());
                    stack.push(sum);
                    break;

                case "^":
                    LinkedList<Integer> result = expo(stack.pop(), stack.pop());
                    stack.push(result);
                    break;

            }

        }

        // System.out.println(stack.pop());
        return stack.pop().toString();
    }


    /**
     * 
     * @param num1
     *            first number to be added
     * @param num2
     *            second number to be added
     * @return LinkedList
     *         representing the sum
     */
    private LinkedList<Integer> addLL(

        LinkedList<Integer> num1,
        LinkedList<Integer> num2) {

        LinkNode<Integer> headPopNum1 = num1.getHead();
        LinkNode<Integer> headPopNum2 = num2.getHead();

        // Create two variable
        LinkNode<Integer> temp = null;
        LinkNode<Integer> resultLink = null;

        int count = 0;
        int remainder = 0;
        int sum = 0;

        while (headPopNum1 != null || (headPopNum2 != null)) {

            count++;
            sum = remainder;

            if (headPopNum1 != null) {
                sum = sum + headPopNum1.getData();
                headPopNum1 = headPopNum1.getNext();
            }

            if (headPopNum2 != null) {
                sum = sum + headPopNum2.getData();
                headPopNum2 = headPopNum2.getNext();
            }

            remainder = sum / 10;
            sum = sum % 10;
            // Check if it first node for the result

            if (count == 1) {
                temp = new LinkNode<Integer>(sum);
                resultLink = temp;
            }
            else {
                LinkNode<Integer> tempSum = new LinkNode<Integer>(sum);
                temp.setNext(tempSum);
                temp = temp.getNext();
            }

        }
        if (remainder != 0) {
            LinkNode<Integer> tempNode = new LinkNode<Integer>(remainder);
            temp.setNext(tempNode);
        }

        LinkedList<Integer> result = new LinkedList<Integer>();
        while (resultLink != null) {

            result.add(resultLink.getData());
            resultLink = resultLink.getNext();
        }

        return result;
    }


    /**
     * 
     * @param num1
     *            first number to be multiplied ( it has been reversed) 1->2->3
     *            = 3->2->1
     * @param num2
     *            second number to be multiplied ( it has been reversed)
     * @return LinkedList
     *         representing the product
     */
    public LinkedList<Integer> multiply(
        LinkedList<Integer> num1,
        LinkedList<Integer> num2) {

        // Temporary pointer nodes to traverse
        LinkNode<Integer> ptr2 = num2.getHead();

        // Stack to collect all products and add them at the end
        Stack<LinkedList<Integer>> stack = new Stack<LinkedList<Integer>>();

        // Multiply each node of num2 LinkedList with the num1
        while (ptr2 != null) {

            // To hold product of the two numbers
            LinkedList<Integer> result = new LinkedList<Integer>();

            // Pointer here so that it would reset when we iterate
            LinkNode<Integer> ptr1 = num1.getHead();

            // To carry remainders if product is above 0-9
            int carry = 0;

            // This is to have proper places for number so when that we can add
            // properly
            for (int i = 0; i < stack.size(); i++) {
                result.add(0);
            }

            while (ptr1 != null) {

                int product = ptr1.getData() * ptr2.getData() + carry;

                // Check if the product is single digit between 0-9
                // or else carry a number
                if (product > 10) {
                    int save = product % 10; // number to be added into the
                                             // LinkedList
                    carry = product / 10; // the carry over
                    result.add(save);
                }
                else {
                    result.add(product);
                }

                // Go to the next data on num1
                ptr1 = ptr1.getNext();
            }

            // If we are at the end of the multiplication and we have a carry
            // we add the carry to our LinkedList
            if (carry > 0) {
                result.add(carry);
            }

            // Go to the next data on num2
            ptr2 = ptr2.getNext();

            // Push the result to the stack
            stack.push(result);

        }

        LinkedList<Integer> sum = new LinkedList<Integer>();
        while (!stack.isEmpty()) {
            sum = addLL(sum, stack.pop());
        }
        return sum;

    }


    /**
     * Computes the Exponentiation of a number to a certain power
     * 
     * @param n
     *            represents the exponent number /operand
     * @param x
     *            number representing to be taken to the power
     * @return LinkedList
     *         representing the result
     * 
     */
    private LinkedList<Integer> expo(
        LinkedList<Integer> n,
        LinkedList<Integer> x) {

        LinkedList<Integer> result = new LinkedList<Integer>();
        LinkedList<Integer> zeroList = new LinkedList<Integer>();
        zeroList.add(1);

        int power = Integer.parseInt(n.toString().replace("[", "").replace("]",
            ""));

        // If exponent is 0 then result is always 1
        if (power == 0) {

            return zeroList;
        }

        // If power is even
        if ((power % 2) == 0) {

            int count = 0;
            power = power / 2;
            result = multiply(x, x);

            while (count < power) {
                result = multiply(result, x);
                count++;
            }

            return result;
        }

        // If power is odd
        if (power % 2 != 0) {

            int count = 0;
            power = (power - 1) / 2;

            result = multiply(x, x);
            while (count < power) {
                result = multiply(result, x);
                count++;
            }

            result = multiply(result, x);

            return result;
        }

        return x;
    }


    /**
     * 
     * @param array
     *            array of chars to be converted into linked list
     * @return LinkedList
     *         representing the converted array of chars
     */
    private LinkedList<Integer> listConverter(char[] arrayOfChars) {

        // Step 1: Convert char array into int array
        LinkedList<Integer> chainNum = new LinkedList<Integer>();
        for (char digit : arrayOfChars) {

            // Convert each char to an int
            int number = Character.getNumericValue(digit);

            // Add the digit to a linked list
            chainNum.add(number);

        }
        // Step 2: Convert int array into linked list
        return chainNum;

    }

}
