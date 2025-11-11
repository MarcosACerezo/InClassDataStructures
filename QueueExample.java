import java.util.NoSuchElementException;

class ArrayQueue<E> implements Cloneable {
    private E[] data;
    private int manyItems;
    private int front;
    private int rear;

    public ArrayQueue() {
        final int INITIAL_CAPACITY = 5;
        manyItems = 0;
        data = (E[]) new Object[INITIAL_CAPACITY];
    }

    public void add(E item) {
        if (manyItems == data.length)
            ensureCapacity(manyItems * 2 + 1);

        if (manyItems == 0) {
            front = 0;
            rear = 0;
        } else {
            rear = nextIndex(rear);
        }

        data[rear] = item;
        manyItems++;
    }

    public void ensureCapacity(int minimumCapacity) {
        E[] biggerArray;
        int n1, n2;

        if (data.length >= minimumCapacity)
            return;
        else if (manyItems == 0)
            data = (E[]) new Object[minimumCapacity];
        else if (front <= rear) {
            biggerArray = (E[]) new Object[minimumCapacity];
            System.arraycopy(data, front, biggerArray, front, manyItems);
            data = biggerArray;
        } else {
            biggerArray = (E[]) new Object[minimumCapacity];
            n1 = data.length - front;
            n2 = rear + 1;
            System.arraycopy(data, front, biggerArray, 0, n1);
            System.arraycopy(data, 0, biggerArray, n1, n2);
            front = 0;
            rear = manyItems - 1;
            data = biggerArray;
        }
    }

    public boolean isEmpty() {
        return (manyItems == 0);
    }

    private int nextIndex(int i) {
        if (++i == data.length)
            return 0;
        else
            return i;
    }

    public E remove() {
        if (manyItems == 0)
            throw new NoSuchElementException("Queue underflow");

        E answer = data[front];
        front = nextIndex(front);
        manyItems--;
        return answer;
    }

    // O(1) runtime
    public int size(){
        if(rear <= front)
            return data.length-front + rear+1;
        return Math.abs(front-rear) + 1;
    }

    // @Override
    // public <E> boolean equals(ArrayQueue<E> comparison){
    //     return false;
    // }
}

// Dummy ArrayStack class assumed (must match your implementation)
class ArrayStack<E> {
    private java.util.ArrayList<E> data = new java.util.ArrayList<>();

    public void push(E item) {
        data.add(item);
    }

    public E pop() {
        if (data.isEmpty())
            throw new NoSuchElementException("Stack underflow");
        return data.remove(data.size() - 1);
    }
}

public class QueueExample {
    public static void main(String[] args) {
        String str1 = "radar";
        ArrayQueue<Character> q1 = new ArrayQueue<>();
        ArrayStack<Character> s1 = new ArrayStack<>();

        System.out.println(str1);

        for (int i = 0; i < str1.length(); i++) {
            q1.add(str1.charAt(i));
            s1.push(str1.charAt(i));
        }

        System.out.printf("Size of radar queue: %d\n", q1.size());

        int res1 = 0;
        while (!q1.isEmpty()) {
            if (!q1.remove().equals(s1.pop()))
                ++res1;
        }

        System.out.println("The result is: " + res1);

        //

        String str2 = "juice";
        ArrayQueue<Character> q2 = new ArrayQueue<>();
        ArrayStack<Character> s2 = new ArrayStack<>();

        System.out.println(str2);

        for (int i = 0; i < str2.length(); i++) {
            q2.add(str2.charAt(i));
            s2.push(str2.charAt(i));
        }

        System.out.printf("Size of juice queue: %d\n", q1.size());

        int res2 = 0;
        while (!q2.isEmpty()) {
            if (!q2.remove().equals(s2.pop()))
                ++res2;
        }

        System.out.println("The result is: " + res2);
    }
}
