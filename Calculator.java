import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        String operators = "+*^";
        // Loop to iterate and get each line(expression)
        while (scan.hasNextLine()) {

            // Get the expression/equation
            String expression = scan.nextLine(); // 123 456 * 789 +
            if (expression.isEmpty()) {
                continue;
            }
            expression = expression.replaceAll("\\s+", " ").trim();

            // Turn expression/equation into array
            String[] arrOfStr = expression.split(" "); // ["123", "456", "*",
                                                       // "789", "+"]
            int signCount = 0;
            for (String element : arrOfStr) {
                if (operators.contains(element)) {
                    signCount++;
                }
            }

            // THIS IS NEW
            if (signCount >= arrOfStr.length - signCount) {
                System.out.println(expression + " =");
                continue;
            }

            if (signCount > (arrOfStr.length) / 2) {
                System.out.println(expression + " =");
                expression = scan.nextLine();
                continue;
            }

            if (operators.contains(arrOfStr[0]) || !(operators.contains(
                arrOfStr[arrOfStr.length - 1]))) {
                System.out.println(expression + " =");

                continue;
            }

            if (arrOfStr.length - signCount >= 2 + signCount) {
                System.out.println(expression + " =");
                expression = scan.nextLine();
                continue;
            }

            else {

                int index = 0;
                for (String element : arrOfStr) {
                    if (operators.contains(element)) {
                        // signCount++;
                    }
                    else {

                        // Removes all leading zeros
                        arrOfStr[index] = element.replaceFirst("^0+(?!$)", "");
                    }
                    index++;
                }

                // Send one expression at a time to be computed
                String finalResult = compute(arrOfStr);
                finalResult = finalResult.replace("[", "");
                finalResult = finalResult.replace("]", "");
                finalResult = finalResult.replace(",", "");
                finalResult = finalResult.replace(" ", "");

                StringBuilder reverseChar = new StringBuilder(finalResult);
                finalResult = reverseChar.reverse().toString();

                int count = 0;
                while ((count + 1) < finalResult.length() && finalResult.charAt(
                    count) == '0') {
                    count++;
                }

                finalResult = finalResult.substring(count);
                if (finalResult.length() >= 1) {
                    System.out.println(expression + " = " + finalResult);
                }
            }

        }
        scan.close();
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

        if (array.length == 0) {
            return "";
        }

        // ["123", "456", "*", "789", "+"]
        String operators = "*+^";
        Stack<LinkedList<Integer>> stack = new Stack<LinkedList<Integer>>();

        // Step 1: iterate through the array and get each element in it
        for (String element : array) {
            // Reverse the element --> "123" -> "321"
            StringBuilder reverseChar = new StringBuilder(element);
            element = reverseChar.reverse().toString();

            // if element is not one of these [* + ^]
            if (!operators.contains(element)) {

                // Turn the element into array of chars
                char[] digits = element.toCharArray(); // "123" --> ["1", "2",
                                                       // "3"]

                // Convert the array of chars to link list
                LinkedList<Integer> linkListNum = listConverter(digits);

                // Add linklist number onto stack
                stack.push(linkListNum);
            }

            switch (element) {

                case "*":
                    if (stack.size() >= 2) {
                        LinkedList<Integer> product = multiply(stack.pop(),
                            stack.pop());
                        stack.push(product);
                    }
                    break;

                case "+":
                    if (stack.size() >= 2) {
                        LinkedList<Integer> sum = addLL(stack.pop(), stack
                            .pop());
                        stack.push(sum);
                    }
                    break;

                case "^":
                    if (stack.size() >= 2) {
                        LinkedList<Integer> result = expo(stack.pop(), stack
                            .pop());
                        stack.push(result);
                    }
                    break;
            }

        }

        return (stack.pop().toString());
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
    public LinkedList<Integer> addLL(

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


    // ********THIS IS THE NEW MULTIPLICATION METHOD*********
    /**
     * Multiplies two numbers represented as linked list
     * 
     * @param link1
     *            first number to be multiplied
     * @param link2
     *            second number to be multiplied
     * @return LinkedList
     *         representing the result
     */
    private LinkedList<Integer> multiply(
        LinkedList<Integer> link1,
        LinkedList<Integer> link2) {

        // Make a list that will contain the result of multiplication, m+n+1 can
        // be max size of the list
        LinkedList<Integer> result = makeEmptyList(link1.size() + link2.size()
            + 1);

        // Pointers for traversing the linked lists
        LinkNode<Integer> firstPtr;
        LinkNode<Integer> secondPtr = link2.getHead();
        LinkNode<Integer> resultPtr1 = result.getHead();
        LinkNode<Integer> resultPtr2;

        // multiply each Node of second list with first
        while (secondPtr != null) {
            int carry = 0;

            // Each time we start from the next of Node from which we started
            // last time
            resultPtr2 = resultPtr1;

            firstPtr = link1.getHead();

            while (firstPtr != null) {

                // Multiply first list's digit with current second list's digit
                int mul = firstPtr.getData() * secondPtr.getData() + carry;

                // Assign the product to corresponding Node of result
                resultPtr2.setData(resultPtr2.getData() + mul % 10); // error
                // result.add(resultPtr2.getData() + mul % 10);

                // Resultant Node itself can have more than 1 digit
                carry = mul / 10 + resultPtr2.getData() / 10;
                resultPtr2.setData(resultPtr2.getData() % 10);
                // result.add(resultPtr2.getData() % 10);

                firstPtr = firstPtr.getNext();
                resultPtr2 = resultPtr2.getNext();

            }

            // If carry is remaining from last multiplication
            if (carry > 0) {
                resultPtr2.setData(resultPtr2.getData() + carry);
            }

            resultPtr1 = resultPtr1.getNext();
            secondPtr = secondPtr.getNext();

        }

        // Return head of multiplication list
        return result;
    }


    /**
     * HELPER METHOD
     * Makes a link list filled with zeros
     * 
     * @param len
     *            length of the link lists that we are multiplying( link1 +
     *            link2)
     * @return
     *         return a linked list full of zeros
     */
    private LinkedList<Integer> makeEmptyList(int len) {
        LinkedList<Integer> temp = new LinkedList<Integer>();
        while (len > 1) {
            temp.add(0);
            len--;
        }
        return temp;
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

        LinkedList<Integer> result = x;

        LinkedList<Integer> zeroList = new LinkedList<Integer>();
        zeroList.add(1);

        StringBuilder reverseChar = new StringBuilder(n.toString().replace("[",
            "").replace("]", "").replace(",", "").replace(" ", ""));
        String newN = reverseChar.reverse().toString();

        int power = Integer.parseInt(newN.toString());

        // If exponent is 0 then result is always 1
        if (power == 0) {

            return zeroList;
        }

        if (power == 1) {

            return x;
        }
        // If power is even
        if ((power % 2) == 0) {

            // (x) ^ 2*(n/2)
            power = 2 * (power / 2);

            if (power == 1) {
                return x;
            }
            while (power > 1) {
                result = multiply(result, x);
                power--;
            }

            return result;
        }

        // If power is odd
        if (power % 2 != 0) {

            power = 2 * ((power - 1) / 2); // 2*(n-1)/2

            while (power > 1) {
                result = multiply(result, x);
                power--;
            }

            // x * (result)
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
