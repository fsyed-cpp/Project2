public class Playground {
    public static void main(String[] args) {
        System.out.println("Do any testing here");

        // Should be 33
        LinkedStack<Integer> linkedStack = new LinkedStack<Integer>();
        ResizableArrayStack<Integer> arrayStack = new ResizableArrayStack<>();
        String postfixExpression = "2 3 * 4 2 - / 5 6 * +";
        float stackResult = linkedStack.evaluatePostfix(postfixExpression);
        float arrayResult = arrayStack.evaluatePostfix(postfixExpression);
        System.out.println("The result using a linked chain stack is: " + stackResult);
        System.out.println("The result using a resizable array stack is: " + arrayResult);
    }
}
