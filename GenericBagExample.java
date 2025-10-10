import javax.swing.text.Position;

class LinkedNode<E> {
    private Object data;
    private LinkedNode<E> link;

    public LinkedNode(Object initialData, LinkedNode<E> initialLink) {
        data = initialData;
        link = initialLink;
    }

    public void setData(Object newData) {
        data = newData;
    }

    public E getData() {
        return (E) data;
    }

    public LinkedNode<E> getLink() {
        return link;
    }

    public void setLink(LinkedNode<E> newLink) {
        link = newLink;
    }

    public static LinkedNode listSearch(LinkedNode head, Object target) {

        LinkedNode cursor;
        for (cursor = head; cursor != null; cursor = cursor.link)
            if (cursor.data.equals(target))
                return cursor;
        return null;
    }

    public static LinkedNode listPosition(LinkedNode head, int position) {
        LinkedNode cursor;
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
    private LinkedNode<E> head;
    private int manyLinkedNodes;

    public LinkedBag(){
        head = null;
        manyLinkedNodes = 0;
    }

    public void add(E element) {
        head = new LinkedNode(element, head);
        manyLinkedNodes++;
    }

    public int countOccurrences(E target) {
        int answer = 0;
        LinkedNode<E> cursor = LinkedNode.listSearch(head, target);

        while (cursor != null) {
            answer++;
            // Move the cursor to the next occurrence of the target
            cursor = cursor.getLink();
            cursor = LinkedNode.listSearch(cursor, target);
        }

        return answer;
    }

    public LinkedNode getHead(){
        return head;
    }

    public boolean remove( E target) {
        LinkedNode targetLinkedNode = LinkedNode.listSearch(head, target);

        if (targetLinkedNode == null)
            return false;
        else {
            targetLinkedNode.setData(head.getData());
            head = head.getLink();
            manyLinkedNodes--;
            return true;
        }
    }

    public int size() {
        return manyLinkedNodes;
    }
    
    //the run time would be O(n) worst case because of the listPosition method
    @SuppressWarnings("unchecked")
    public E grab(){
        LinkedNode rand = LinkedNode.listPosition(head, (int)(manyLinkedNodes * Math.random() + 1));
        return (E)rand.getData();
    }

    //O(n) runtime
    @SuppressWarnings("unchecked")
    public static <E extends Cloneable> LinkedBag<E> union(LinkedBag<E> bag1, LinkedBag<E> bag2) {
        LinkedBag<E> result = new LinkedBag<E>();
        LinkedNode<E> cursor = bag1.head;
        try{
            while(cursor != null){
            result.add((E)cursor.getData().getClass().getMethod("clone").invoke(cursor.getData()));
            cursor = cursor.getLink();
            }
            cursor = bag2.head;
            while(cursor != null){
                result.add((E)cursor.getData().getClass().getMethod("clone").invoke(cursor.getData()));
                cursor = cursor.getLink();
            }
            result.manyLinkedNodes = bag1.manyLinkedNodes + bag2.manyLinkedNodes;
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }


}




public class GenericBagExample {
    public static void main(String[] args) {
        LinkedBag<Position3D> positionBag = new LinkedBag();
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

        LinkedBag<Position3D> secondBag = new LinkedBag();
        secondBag.add(new Position3D(8, 00, 8135));
        secondBag.add(new Position3D(1, 2, 3));
        secondBag.add(new Position3D(1, 2, 3));
        secondBag.add(new Position3D(1, 2, 3));
        secondBag.add(new Position3D(1, 2, 3));

        LinkedBag<Position3D> unionBag = LinkedBag.union(secondBag, positionBag);
        System.out.println(unionBag.size());
        LinkedNode<Position3D> cursor = unionBag.getHead();
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


