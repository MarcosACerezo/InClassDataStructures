import java.util.EmptyStackException;

public class StackExample {
    public static void main(String[] args) {
        String[] myLine = {"Hello", "from", "the", "other", "side!"};
        ArrayStack<String> stackTest = new ArrayStack<>();

        for (String s : myLine) {
            stackTest.push(s);
        }

        System.out.println(stackTest.peek());

        for (String s : myLine) {
            System.out.print(stackTest.pop() + " ");
        }//O(n) runtime

        System.out.println();
        String test = "{{((({{[[[]]]}})))}}";
        System.out.println(ArrayStack.isBalanced(test));
        String case1 = "({[()]})";

        String case2 = "{[()()]}";

        String case3 = "([)]";

        String case4 = "((())";

        String case5 = "{[()(){}([])]({[]})}";

        String case6 = "{[()({[)]({})](])}";
        String case7 = "}}}";

        System.out.println(ArrayStack.isBalanced(case7));
        System.out.println(ArrayStack.isBalanced(case1));
        System.out.println(ArrayStack.isBalanced(case2));
        System.out.println(ArrayStack.isBalanced(case3));
        System.out.println(ArrayStack.isBalanced(case4));
        System.out.println(ArrayStack.isBalanced(case5));
        System.out.println(ArrayStack.isBalanced(case6));
        
    }
}

class ArrayStack<E> implements Cloneable {

    private E[] data;
    private int manyItems;

    @SuppressWarnings("unchecked")
    public ArrayStack() {
        final int INITIAL_CAPACITY = 10;
        manyItems = 0;
        data = (E[]) new Object[INITIAL_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public ArrayStack(int initialCapacity) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException(
                "initialCapacity too small " + initialCapacity);
        manyItems = 0;
        data = (E[]) new Object[initialCapacity];
    }

    @SuppressWarnings("unchecked")
    public void ensureCapacity(int minimumCapacity) {
        E[] biggerArray;

        if (data.length < minimumCapacity) {
            biggerArray = (E[]) new Object[minimumCapacity];
            System.arraycopy(data, 0, biggerArray, 0, manyItems);
            data = biggerArray;
        }
    }

    public boolean isEmpty() {
        return (manyItems == 0);
    }

    public E pop() {
        if (manyItems == 0)
            throw new EmptyStackException();
        return data[--manyItems];
    }

    public void push(E item) {
        if (manyItems == data.length) {
            ensureCapacity(manyItems * 2 + 1);
        }
        data[manyItems] = item;
        manyItems++;
    }

    public E peek(){
        if (manyItems == 0)
            throw new EmptyStackException();
        return data[manyItems-1];
    }

    public int size() {
        return manyItems;
    }

    public static boolean isBalanced(String testCase){
        final char Leftnormal = '(';
        final char Rightnormal = ')';
        final char Leftcurly = '{';
        final char Rightcurly = '}';
        final char Leftsquare = '[';
        final char Rightsquare = ']';
        
        ArrayStack<Character> stack = new ArrayStack();
        
        for(int i = 0; i < testCase.length(); i++){
            switch(testCase.charAt(i)){
                case Leftnormal:
                case Leftcurly:
                case Leftsquare:
                    stack.push(testCase.charAt(i));
                    break;
                case Rightnormal:
                    if(stack.pop()!=Leftnormal) return false;
                    break;
                case Rightcurly:
                    if(stack.pop()!=Leftcurly) return false;
                    break;
                case Rightsquare:
                    if(stack.pop()!=Leftsquare) return false;
                    break;
            }
        }
        return (stack.isEmpty());
    }
}
