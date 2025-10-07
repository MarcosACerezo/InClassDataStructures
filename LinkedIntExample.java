import javax.swing.text.Position;

class IntNode {
    private int data;
    private IntNode link;

    public IntNode(int initialData, IntNode initialLink) {
        data = initialData;
        link = initialLink;
    }

    public void setData(int newData) {
        data = newData;
    }

    public int getData() {
        return data;
    }

    public IntNode getLink() {
        return link;
    }

    public void setLink(IntNode newLink) {
        link = newLink;
    }

    public static IntNode listSearch(IntNode head, int target) {
        IntNode cursor;
        for (cursor = head; cursor != null; cursor = cursor.link)
            if (target == cursor.data)
                return cursor;
        return null;
    }

    public static IntNode listPosition(IntNode head, int position) {
        IntNode cursor;
        int i;

        if (position <= 0)
            throw new IllegalArgumentException("position is not positive");

        cursor = head;
        for (i = 1; (i < position) && (cursor != null); i++)
            cursor = cursor.link;
        return cursor;
    }
}

class IntLinkedBag implements Cloneable {
    private IntNode head;
    private int manyNodes;

    public IntLinkedBag() {
        head = null;
        manyNodes = 0;
    }

    public void add(int element) {
        head = new IntNode(element, head);
        manyNodes++;
    }

    public int countOccurrences(int target) {
        int answer = 0;
        IntNode cursor = IntNode.listSearch(head, target);

        while (cursor != null) {
            answer++;
            // Move the cursor to the next occurrence of the target
            cursor = cursor.getLink();
            cursor = IntNode.listSearch(cursor, target);
        }

        return answer;
    }

    public boolean remove(int target) {
        IntNode targetNode = IntNode.listSearch(head, target);

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
    public int grab(){
        IntNode randInt = IntNode.listPosition(head, (int)(manyNodes * Math.random() + 1));
        return randInt.getData();

        //Intnode cursor;
        //int i = (int)(manyNodes * Math.random())+1;
        //cursor = IntNode.listPosition(head, i);
        //return cursor.getData();
    }
}

class Position3DNode {
    private Position3D data;
    private Position3DNode link;

    public Position3DNode(Position3D initialData, Position3DNode initialLink) {
        data = initialData;
        link = initialLink;
    }

    public void setData(Position3D newData) {
        data = newData;
    }

    public Position3D getData() {
        return data;
    }

    public Position3DNode getLink() {
        return link;
    }

    public void setLink(Position3DNode newLink) {
        link = newLink;
    }

    public static Position3DNode listSearch(Position3DNode head, Position3D target) {
        Position3DNode cursor;
        for (cursor = head; cursor != null; cursor = cursor.link)
            if (cursor.data.equals(target))
                return cursor;
        return null;
    }

    public static Position3DNode listPosition(Position3DNode head, int position) {
        Position3DNode cursor;
        int i;

        if (position <= 0)
            throw new IllegalArgumentException("position is not positive");

        cursor = head;
        for (i = 1; (i < position) && (cursor != null); i++)
            cursor = cursor.link;
        return cursor;
    }
}

class Position3DLinkedBag implements Cloneable {
    private Position3DNode head;
    private int manyNodes;

    public Position3DLinkedBag() {
        head = null;
        manyNodes = 0;
    }

    public void add(Position3D element) {
        head = new Position3DNode(element, head);
        manyNodes++;
    }

    public int countOccurrences(Position3D target) {
        int answer = 0;
        Position3DNode cursor = Position3DNode.listSearch(head, target);

        while (cursor != null) {
            answer++;
            // Move the cursor to the next occurrence of the target
            cursor = cursor.getLink();
            cursor = Position3DNode.listSearch(cursor, target);
        }

        return answer;
    }

    public boolean remove(Position3D target) {
        Position3DNode targetNode = Position3DNode.listSearch(head, target);

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
    public Position3D grab(){
        Position3DNode randPosition3D = Position3DNode.listPosition(head, (int)(manyNodes * Math.random() + 1));
        return randPosition3D.getData();
    }
}




public class LinkedIntExample {
    public static void main(String[] args) {
        IntLinkedBag myBag = new IntLinkedBag();

        System.out.println("Current size: " + myBag.size());

        myBag.add(100);
        myBag.add(150);
        myBag.add(100);

        System.out.println("There are " + myBag.countOccurrences(100) + " copies.");
        System.out.println("There are " + myBag.countOccurrences(300) + " copies.");

        myBag.remove(100);
        System.out.println("Current size: " + myBag.size());
        
        
        System.out.println("grab() test\n");
        myBag.add(332);
        myBag.add(231);
        myBag.add(865);
        myBag.add(1);
        myBag.add(898);
        for(int i =0; i < 5; i++){
            //System.out.println();
            System.out.println(myBag.grab());
        }

        Position3DLinkedBag positionBag = new Position3DLinkedBag();

        System.out.println("Current size: " + positionBag.size());

        positionBag.add(new Position3D(1, 2, 3));
        positionBag.add(new Position3D(6, 9, 0));
        positionBag.add(new Position3D(4, 2, 0));

        System.out.println("There are " + positionBag.countOccurrences(new Position3D(1, 2, 3)) + " copies.");
        System.out.println("There are " + positionBag.countOccurrences(new Position3D(4, 5, 6)) + " copies.");

        myBag.remove(100);
        System.out.println("Current size: " + positionBag.size());
        
        
        positionBag.add(new Position3D(8, 00, 8135));
        positionBag.add(new Position3D(1, 2, 3));
        positionBag.add(new Position3D(1, 2, 3));
        positionBag.add(new Position3D(1, 2, 3));
        positionBag.add(new Position3D(1, 2, 3));
        for(int i =0; i < 5; i++){
            //System.out.println();
            System.out.println(positionBag.grab());
        }
    }
}
