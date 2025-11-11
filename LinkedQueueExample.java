import java.util.NoSuchElementException;

class LinkedNode<E> {
    private E data;
    private LinkedNode<E> link;

    public LinkedNode(E initialData, LinkedNode<E> initialLink) {
        data = initialData;
        link = initialLink;
    }

    public void setData(E newData) {
        data = newData;
    }

    public E getData() {
        return data;
    }

    public LinkedNode<E> getLink() {
        return link;
    }

    public void setLink(LinkedNode<E> newLink) {
        link = newLink;
    }

    public void addNodeAfter(E element) {
        link = new LinkedNode<E>(element, link);
    }
}

class LinkedQueue<E> implements Cloneable {
    private int manyNodes;
    private LinkedNode<E> front;
    private LinkedNode<E> rear;

    public LinkedQueue() {
        front = null;
        rear = null;
        manyNodes = 0;
    }

    public void add(E item) {
        if (isEmpty()) {
            front = new LinkedNode<E>(item, null);
            rear = front;
        } else {
            rear.addNodeAfter(item);
            rear = rear.getLink();
        }
        manyNodes++;
    }

    public boolean isEmpty() {
        return (manyNodes == 0);
    }

    public E remove() {
        if (manyNodes == 0)
            throw new NoSuchElementException("Queue underflow");

        E answer = front.getData();
        front = front.getLink();
        manyNodes--;

        if (manyNodes == 0)
            rear = null;

        return answer;
    }

    public int size() {
        return manyNodes;
    }

    //O(1) runtime
    public E peekRear(){
        if(rear==null) return null;
        return rear.getData();
    }
}


public class LinkedQueueExample{

    public static void main(String[] args){
        LinkedQueue<Position3D> positionQueue = new LinkedQueue<>();
        Position3D point1 = new Position3D(1.0, 2.0, 3.0);
        Position3D point2 = new Position3D(4.0, 5.0, 6.0);
        Position3D point3 = new Position3D(7.0, 8.0, 9.0);

        positionQueue.add(point1);
        System.out.println(positionQueue.size());
        positionQueue.add(point2);
        System.out.println(positionQueue.size());
        positionQueue.add(point3);
        System.out.println(positionQueue.size());
        
        Position3D removed = positionQueue.remove();
        System.out.println(removed);
        System.out.println(positionQueue.size());


        System.out.println(positionQueue.peekRear());

              
    }


}
