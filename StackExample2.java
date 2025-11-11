import java.util.EmptyStackException;

class Node<E> {
    private E data;
    private Node<E> link;

    public Node(E initialData, Node<E> initialLink) {
        data = initialData;
        link = initialLink;
    }

    public void setData(E newData) { 
        data = newData; 
    }
    public E getData() { 
        return data; 
    }
    public Node<E> getLink() { 
        return link; 
    }
    public void setLink(Node<E> newLink) { 
        link = newLink; 
    }
}

class LinkedStack<E> implements Cloneable {
    private Node<E> top;

    public LinkedStack() {
        top = null;
    }

    public boolean isEmpty() {
        return (top == null);
    }

    public E peek() {
        if (top == null)
            throw new EmptyStackException();
        return top.getData();
    }

    public E pop() {
        E answer;
        if (top == null)
            throw new EmptyStackException();

        answer = top.getData();
        top = top.getLink();
        return answer;
    }

    public void push(E item) {
        top = new Node<E>(item, top);
    }

    public void display() {
        Node<E> current = top;
        while (current != null) {
            System.out.print(current.getData() + " ");
            current = current.getLink();
        }
        System.out.println();
    }

    public Node<E> getTop(){
        return top;
    }

    public LinkedStack<E> flip(){
        
        LinkedStack<E> newStack = new LinkedStack<>();
        Node<E> cursor = top;
        while(cursor != null){
            newStack.push(cursor.getData());
            cursor = cursor.getLink();
        }
        return newStack;
    }

    // O(n) runtime
    public static <E> boolean equals(LinkedStack<E> stack1, LinkedStack<E> stack2){
        
        Node<E> cursor1 = stack1.getTop();
        Node<E> cursor2 = stack2.getTop();
        
        for(; cursor1 != null && cursor2 != null; cursor1 = cursor1.getLink(), cursor2 = cursor2.getLink())

            if(!(cursor1.getData().equals(cursor2.getData())))
                return false;

        return cursor1 == null && cursor2 == null;
    }    
}

public class StackExample2 {
    public static void main(String[] args) {
        String s = "racecar";
        String r = "fish";
        LinkedStack<Character> stack1 = new LinkedStack<Character>();
        LinkedStack<Character> stack2 = new LinkedStack<Character>();
        LinkedStack<Character> stack3 = new LinkedStack<Character>();
        System.out.println(s);

        for (int i = 0; i < s.length(); i++){
            stack1.push(s.charAt(i));
            stack2.push(s.charAt(i));
        }
        // for (i)t i=0; i<r.length
        // stack3.push('e');

        System.out.println(LinkedStack.equals(stack1, stack2));
        System.out.println(LinkedStack.equals(stack2, stack3));
    }
}
