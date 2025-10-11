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

    public static <E> Node<E> listSearch(Node<E> head, E target) {

        Node<E> cursor;
        for (cursor = head; cursor != null; cursor = cursor.link)
            if (cursor.data.equals(target))
                return cursor;
        return null;
    }

    public static <E> Node<E> listPosition(Node<E> head, int position) {
        Node<E> cursor;
        int i;

        if (position <= 0)
            throw new IllegalArgumentException("position is not positive");

        cursor = head;
        for (i = 1; (i < position) && (cursor != null); i++)
            cursor = cursor.link;
        return cursor;
    }
}

class LinkedBag<E> implements Cloneable {
    private Node<E> head;
    private int manyNodes;

    public LinkedBag(){
        head = null;
        manyNodes = 0;
    }

    public void add(E element) {
        head = new Node<E>(element, head);
        manyNodes++;
    }

    
    public int countOccurrences(E target) {
        int answer = 0;
        Node<E> cursor = Node.listSearch(head, target);

        while (cursor != null) {
            answer++;
            // Move the cursor to the next occurrence of the target
            cursor = cursor.getLink();
            cursor = Node.listSearch(cursor, target);
        }

        return answer;
    }

    public Node<E> getHead(){
        return head;
    }

    
    public boolean remove(E target) {
        Node<E> targetNode = Node.listSearch(head, target);

        if (targetNode == null)
            return false;
        else {
            targetNode.setData(head.getData());
            head = head.getLink();
            manyNodes--;
            return true;
        }
    }

    public int size() {
        return manyNodes;
    }
    
    //the run time would be O(n) worst case because of the listPosition method
    
    public E grab(){
        if(manyNodes == 0){
            throw new IllegalStateException("There are no elements in the bag");
        }
        Node<E> rand = Node.listPosition(head, (int)(manyNodes * Math.random() + 1));//select node
        return rand.getData();//return data from node
    }

    //O(n) runtime
    @SuppressWarnings("unchecked")
    public static <E extends Cloneable> LinkedBag<E> union(LinkedBag<E> bag1, LinkedBag<E> bag2) {
        LinkedBag<E> result = new LinkedBag<E>();
        Node<E> cursor;
        try{
            for(cursor=bag1.head; cursor != null; cursor = cursor.getLink()){
            result.add((E)cursor.getData().getClass().getMethod("clone").invoke(cursor.getData()));
            }
            cursor = bag2.head;
            for(cursor=bag2.head; cursor != null; cursor = cursor.getLink()){
                result.add((E)cursor.getData().getClass().getMethod("clone").invoke(cursor.getData()));
            }
            result.manyNodes = bag1.manyNodes + bag2.manyNodes;
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }


}




public class GenericBagExample {
    public static void main(String[] args) {
        LinkedBag<Position3D> positionBag = new LinkedBag<>();
        System.out.println("Current size: " + positionBag.size());

        //filling bag
        positionBag.add(new Position3D(1, 2, 3));
        positionBag.add(new Position3D(6, 9, 0));
        positionBag.add(new Position3D(4, 2, 0));
        positionBag.add(new Position3D(8, 00, 8135));
        positionBag.add(new Position3D(1, 2, 3));
        positionBag.add(new Position3D(1, 2, 3));
        positionBag.add(new Position3D(1, 2, 3));
        positionBag.add(new Position3D(1, 2, 3));

        //comparisons
        Position3D OneTwoThreeVector = new Position3D(1, 2, 3);
        Position3D FourFiveSixVector = new Position3D(4, 5, 6);
        System.out.printf("There are %s copies of %s\n", positionBag.countOccurrences(OneTwoThreeVector), OneTwoThreeVector);
        System.out.printf("There are %s copies of %s\n", positionBag.countOccurrences(FourFiveSixVector), FourFiveSixVector);

        System.out.println("Current size: " + positionBag.size());
        
        //grab test
        System.out.println("Grab functionality test");
        for(int i =0; i < 10; i++){
            //System.out.println();
            System.out.println(positionBag.grab());
        }

        LinkedBag<Position3D> secondBag = new LinkedBag<>();
        secondBag.add(new Position3D(8, 00, 8135));
        secondBag.add(new Position3D(1, 2, 3));
        secondBag.add(new Position3D(1, 2, 3));
        secondBag.add(new Position3D(1, 2, 3));
        secondBag.add(new Position3D(1, 2, 3));

        LinkedBag<Position3D> unionBag = LinkedBag.union(secondBag, positionBag);
        System.out.println(unionBag.size());
        Node<Position3D> cursor = unionBag.getHead();
        System.out.println("\n\n Union Bag Information\n");
        while(cursor != null){
            System.out.println(cursor.getData());
            cursor = cursor.getLink();
        }

        System.out.printf("Size of position bag: %d\n", positionBag.size());
        System.out.printf("Size of second bag: %d\n", secondBag.size());
        System.out.printf("Size of union bag: %d\n", unionBag.size());

    }
}


