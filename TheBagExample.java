public class TheBagExample {
    public static void main(String[] args) {
        Position3DArrayBag myBag = new Position3DArrayBag();
        System.out.println("Current Capacity: " + myBag.getCapacity());
        myBag.add(new Position3D());
        myBag.add(new Position3D());
        myBag.add(new Position3D());
        System.out.println("Current Capacity: " + myBag.getCapacity());
        myBag.trimToSize();
        System.out.println("Current Capacity: " + myBag.getCapacity());

        Position3DArrayBag cloneBag = new Position3DArrayBag(myBag);
        System.out.println("cloneBag size: " + cloneBag.size());
        System.out.println("myBag size: " + myBag.size());
        for(int i=0; i<cloneBag.size(); i++){
            System.out.println("CLONE Points share same address: " + (cloneBag.get(i) == myBag.get(i)));
            System.out.println("CLONE Points share same values: " + (cloneBag.get(i).equals(myBag.get(i))));
        }
        Position3DArrayBag unionBag = Position3DArrayBag.union(myBag, cloneBag);

        System.out.println(myBag.size());
        System.out.println(cloneBag.size());
        System.out.println(unionBag.size());
        for(int i=0; i<myBag.size(); i++){
            System.out.println("\nUNION Points share same address: " + (myBag.get(i) == unionBag.get(i)));
            System.out.println("UNION Points share same values: " + (myBag.get(i).equals(unionBag.get(i))));
        }
        for(int i=myBag.size(); i<unionBag.size(); i++){
            System.out.println("\nUNION Points share same address: " + (cloneBag.get(i - 3) == unionBag.get(i)));
            System.out.println("UNION Points share same values: " + (cloneBag.get(i - 3).equals(unionBag.get(i))));
        }

    }
}
class IntArrayBag {
    private int[ ] data;
    private int manyItems;
    public IntArrayBag( ) {
        final int INITIAL_CAPACITY = 2;
        manyItems = 0;
        data = new int[INITIAL_CAPACITY];
    }
    public void add(int element) {
        if (manyItems == data.length)
            ensureCapacity((manyItems + 1)*2);
        data[manyItems] = element;
        manyItems++;
    }
    public void ensureCapacity(int minimumCapacity) {
        int[ ] biggerArray;
        if (data.length < minimumCapacity)
        {
            biggerArray = new int[minimumCapacity];
            System.arraycopy(data, 0, biggerArray, 0, manyItems);
            data = biggerArray;
        }
    }
    public int getCapacity( ) {
        return data.length;
    }
    public void trimToSize( ) {
        int[ ] trimmedArray;
        if (data.length != manyItems) {
            trimmedArray = new int[manyItems];
            System.arraycopy(data, 0, trimmedArray, 0, manyItems);
            data = trimmedArray;
        }
    }
}
class Position3DArrayBag {
    private Position3D[] data;
    private int manyItems;
    public Position3DArrayBag( ) {
        final int INITIAL_CAPACITY = 2;
        manyItems = 0;
        data = new Position3D[INITIAL_CAPACITY];
    }
    public Position3DArrayBag(Position3DArrayBag other){
        data = new Position3D[other.getCapacity()];
        for(int i=0; i<other.manyItems;i++){
            data[i] = other.data[i].clone();
        }
        manyItems = other.manyItems;
    }
    public void add(Position3D element) {
        if (manyItems == data.length)
            ensureCapacity((manyItems + 1)*2);
        data[manyItems] = element;
        manyItems++;
    }
    public void ensureCapacity(int minimumCapacity) {
        Position3D[ ] biggerArray;
        if (data.length < minimumCapacity)
        {
            biggerArray = new Position3D[minimumCapacity];
            System.arraycopy(data, 0, biggerArray, 0, manyItems);
            data = biggerArray;
        }
    }
    public int getCapacity( ) {
        return data.length;
    }
    public int size(){
        return manyItems;
    }
    public void trimToSize( ) {
        Position3D[ ] trimmedArray;
        if (data.length != manyItems) {
            trimmedArray = new Position3D[manyItems];
            System.arraycopy(data, 0, trimmedArray, 0, manyItems);
            data = trimmedArray;
        }
    }

    public Position3D get(int index){
        return data[index];
    }

    public static Position3DArrayBag union(Position3DArrayBag bag1, Position3DArrayBag bag2){
        Position3DArrayBag unionBag = new Position3DArrayBag();
        unionBag.data = new Position3D[bag1.size() + bag2.size()];
        for(int i=0; i<bag1.size(); i++){
            unionBag.data[i] = bag1.data[i].clone();
        }
        int bag1Size = bag1.size();
        for(int i=0; i<bag2.size(); i++){
            unionBag.data[i + bag1Size ] = bag2.data[i].clone();
        }
        unionBag.manyItems = bag1.size() + bag2.size();
        return unionBag;

    }
}
/**
 * runtime analysis
 * 
 * add - Best case: O(1)    Worst case: O(n)
 * size Worst case: O(1)
 * union - O(n)
 * new constructor - O(n)
 */

//2,6,3,3,4,8,7