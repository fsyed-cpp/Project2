/**
 * Playground class is used for testing the logic for our LinkedStack and ArrayStack
 */
public class Playground {

    /**
     * Main function for playground
     * @param args main args
     */
    public static void main(String[] args) {
        System.out.println("Do any testing here");

        // Should be 33
        ResizableArrayStack<Integer> arrayStack = new ResizableArrayStack<>();
        String postfixExpression = "2 3 * 4 2 - / 5 6 * +";
        float arrayResult = arrayStack.evaluatePostfix(postfixExpression);
        System.out.println("The evaluation of our postfix expression using a resizable array stack is: " + arrayResult);

        // Should be "2 3 * 4 2 - / 5 6 * +"
        LinkedStack<Integer> linkedStack = new LinkedStack<Integer>();
        String infixExpression = "2 * 3 / (4 - 2) + 5 * 6";
        try {
            String postfixResult = linkedStack.convertToPostfix(infixExpression);

            System.out.println("The postfix expression using a linked stack is: " + postfixResult);
        } catch (Exception exception) {
            System.out.println("Error when attempting to convert to postfix: " + exception);
        }
    }
}
