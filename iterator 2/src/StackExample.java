import java.util.Iterator;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;  

class Node <E> {    
    private E data;    
    private Node<E> link;         
    
    public Node(E initialData, Node<E> initialLink)  {       
        data = initialData;       
        link = initialLink;    
    }        
    
    public void setData(E newData)  {  data = newData;  }     
    public E getData( ) {  return data;  }    
    public Node<E> getLink( )  {   return link; }     
    public void setLink(Node<E> newLink) {  link = newLink;   } 
}  

class LinkedStack<E> implements Cloneable, Iterable<E> {
    private Node<E> top;      
    
    public LinkedStack( )   {       
        top = null;    
    }       
    
    public boolean isEmpty( )   {       
        return (top == null);    
    }    
    
    public E peek( )    {       
        if (top == null)         
            throw new EmptyStackException( );       
        return top.getData( );    
    }    
    
    public E pop( )   {       
        E answer;              
        
        if (top == null)          
            throw new EmptyStackException( );              
        
            answer = top.getData( );       
            top = top.getLink( );       
            return answer;    
    }     
    
    public void push(E item)   {       
        top = new Node<E>(item, top);    
    }    
    
    //O(n) runtime
    public void display() {         
        Iterator <E> it = this.getIterator();      
        while (it.hasNext()) {             
            System.out.print(it.next());                   
        }         
        System.out.println();     
    }

    public Iterator <E> getIterator(){
        return new StackIterator (top);
    }

     class StackIterator  implements Iterator<E> {
        private Node<E> current;
        public StackIterator (Node<E> head) {
            current = head;
        }

        public boolean hasNext( ) {
            return (current != null);
        }

        public E next( ) {
            E answer;
            if (!hasNext( ))
                throw new NoSuchElementException("The stack is empty");
            answer = current.getData( );
            current = current.getLink( );
            return answer;
        } 

        public void remove( ) {
            throw new UnsupportedOperationException("You are not allowed to remove an element from the stack!");
        }
    }

    public Node<E> getTop() {
        return top;
    }
    //O(n) runtime
    public static <E>boolean equals(LinkedStack<E> stack1, LinkedStack<E> stack2) {
        
        Node<E> cursor1 = stack1.getTop();
        Node<E> cursor2 = stack2.getTop();

        for(; cursor1 != null && cursor2 != null; 
            cursor1 = cursor1.getLink(), cursor2 = cursor2.getLink()) {
            
            if(!cursor1.getData().equals(cursor2.getData())) {
                return false;
            }
            
        }
        return cursor1 == null && cursor2 == null;
    }

    public LinkedStack<E> flip(){
        LinkedStack<E> flippedStack = new LinkedStack<E>();
        
        for(Node<E> cursor = this.getTop(); cursor != null; cursor = cursor.getLink()) {
            flippedStack.push(cursor.getData());
        }
        return flippedStack;
    }

    @Override
    public Iterator<E> iterator() {
        return getIterator();
    }
}

public class StackExample { 
    public static void main (String[] args) {         
        String s = "racecar";
        LinkedStack<Character> myStack = new LinkedStack<Character>(); 
        

        for(int i=0; i<s.length(); i++) {
            myStack.push(s.charAt(i));
        }
        
        Iterator <Character> myIt = myStack.getIterator();

        while (myIt.hasNext()) {             
            System.out.print(myIt.next());                   
        }
        System.out.println();
        System.out.println(myStack.peek());

        LinkedStack<Position3D> positionStack = new LinkedStack<Position3D>();
        positionStack.push(new Position3D(1,2,3));
        positionStack.push(new Position3D(4,5,6));
        positionStack.push(new Position3D(7,8,9));
        positionStack.display();

    }
}  