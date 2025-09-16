import javax.swing.text.Position;

public class TheBagExample {
    public static void main(String[] args) {
        Position3DArrayBag myBag = new Position3DArrayBag();
        System.out.println("Current Capacity: " + myBag.getCapacity());

        myBag.add(new Position3D(0,0,0));
        myBag.add(new Position3D(200,4892,2018329199));
        myBag.add(new Position3D());
        
        System.out.println("Current Capacity: " + myBag.getCapacity());
        myBag.trimToSize();
        System.out.println("Current Capacity: " + myBag.getCapacity());
        Position3DArrayBag cloneBag = new Position3DArrayBag(myBag);
        System.out.println(myBag == cloneBag);

        Position3DArrayBag unionBag = Position3DArrayBag.union(myBag, cloneBag);

        System.out.println(myBag.size());
        System.out.println(cloneBag.size());
        System.out.println(unionBag.size());
        System.out.println(unionBag.getBag());
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

class Position3DArrayBag implements Cloneable {
    private Position3D[] data;
    private int manyItems;

    public Position3DArrayBag( ) {
        final int INITIAL_CAPACITY = 2;
        manyItems = 0;
        data = new Position3D[INITIAL_CAPACITY];
    }

    public Position3DArrayBag(Position3DArrayBag array){
        manyItems = array.size();
        data = array.data.clone();
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

    public Position3D[] getBag(){
        return this.data;
    }

    public static Position3DArrayBag union(Position3DArrayBag bag1, Position3DArrayBag bag2){
        Position3DArrayBag unionBag = new Position3DArrayBag();
        unionBag.data = new Position3D[bag1.manyItems + bag2.manyItems];
        System.arraycopy(bag1.data, 0, unionBag.data, 0, bag1.manyItems);
        System.arraycopy(bag2.data, 0, unionBag.data, bag1.manyItems, bag2.manyItems);
        unionBag.manyItems = bag1.size() + bag2.size();
        return new Position3DArrayBag(unionBag);
    }
    
    // static Position3dArrayBag union(Position3DArrayBag bag1, Position3DArrayBag bag2){
    //     Position3D[] bag = new Position3D[bag1.size()+bag2.size()];
    //     Position3D[] hold = bag1.getBag();
    //     int count = 0;
    //     for(int i=0; i<hold.length; i++){
    //         if(hold[i]!=null){
    //             bag[count]=hold[i];
    //             count++;
    //         }
    //     }
    //     hold = bag2.getBag();
    //     for(int i=0; i<hold.length; i++){
    //         if(hold[i]!=null){
    //             bag[count]=hold[i];
    //             count++;
    //         }
    //     }
    //     return new Position3DArrayBag(bag);
    // }


}
 