package ArrayBag2;

public class TheBagExample {
    public static void main(String[] args) {

        ArrayBag<Position3D> myBag = new ArrayBag<>();
        System.out.println("Current Capacity: " + myBag.getCapacity());

        myBag.add(new Position3D(10,10,10));
        myBag.add(new Position3D(20,20,20));
        myBag.add(new Position3D(10,10,10));
        
        System.out.println("Current Capacity: " + myBag.getCapacity());
        myBag.trimToSize();
        System.out.println("Current Capacity: " + myBag.getCapacity());
        System.out.println("Current size: " + myBag.size());

        ArrayBag<Position3D> copiedBag = new ArrayBag<>(myBag);
        copiedBag.add(new Position3D(40,40,40));
        System.out.println("Copied bag Size: " + copiedBag.size());
        System.out.println("Copied Bag Capacity: " + copiedBag.getCapacity());
        
        ArrayBag<Position3D> unionBag = ArrayBag.union(myBag, copiedBag);
        System.out.println("Union Bag Size: " + unionBag.size());
        System.out.println("Union Bag Capacity: " + unionBag.getCapacity());
        System.out.println(copiedBag.grab());
    }
}

class ArrayBag<E> {
    private Object[ ] data;
    private int manyItems;

    public ArrayBag(){
        final int INITIAL_CAPACITY = 2;
        manyItems = 0;
        data = new Object[INITIAL_CAPACITY];
    }

    public ArrayBag(ArrayBag<E> other) {
        this.manyItems = other.manyItems;
        // this.data = new Object[other.data.length];
        this.data=other.new Object [other.data.length];
        // for (int i = 0; i < other.manyItems; i++)
        //     this.data[i] = other.getData();//object constructor
    for ( int i = 0; i < other.manyItems; i++){
            try{
                this.data[i] = other.data[i].getClass().getMethod("clone").invoke(other.data[i]); 
            }
            catch (Exception e){
                throw new RuntimeException("Clone Failed", e);
            }
        }
    
    }
    public Object[] getData(){
        return this.data;
    }
    
    public void add(E element) {

        if (manyItems == data.length) {
            ensureCapacity((manyItems + 1)*2);
        }

        data[manyItems] = element;
        manyItems++;

    }
    
    public void ensureCapacity(int minimumCapacity) {
        Object[ ] biggerArray;
        if (data.length < minimumCapacity) {
            biggerArray = new Object[minimumCapacity];
            System.arraycopy(data, 0, biggerArray, 0, manyItems);
            data = biggerArray;
        }
    }

    public int getCapacity( ) {
        return data.length;
    }

    public int size( ) {
        return manyItems;
    }

    public void trimToSize( ) {
        
        Object[ ] trimmedArray;
        
        if (data.length !=manyItems) {
            trimmedArray = new Object[manyItems];
            System.arraycopy(data, 0, trimmedArray, 0, manyItems);
            data = trimmedArray;
        }

    }
    @SuppressWarnings("unchecked")
    public static <E extends Cloneable> ArrayBag<E> union(ArrayBag<E> bag1, ArrayBag<E>bag2) {
        ArrayBag<E> result = new ArrayBag<E>();
        result.ensureCapacity(bag1.manyItems + bag2.manyItems);
        
        try{
        
            for (int i = 0; i < bag1.manyItems; i++)
                result.add((E)bag1.data[i].getClass().getMethod("clone").invoke(bag1.data[i]));//don't need to declare clone?
            
            for (int i = 0; i < bag2.manyItems; i++)
               result.add((E)bag2.data[i].getClass().getMethod("clone").invoke(bag2.data[i]));
        }

        catch (Exception e){

            throw new AssertionError("Cloning not supported", e);
        
        }
        
        return result;
    }

    @SuppressWarnings("unchecked")
    public E grab(){
        int i;
        if(manyItems == 0){
            return null;
        }

        i = (int) (Math.random() * manyItems);
        return (E) data[i];
    }
    @SuppressWarnings("unchecked")
    public static <E> void addAll(ArrayBag<E> bag1, ArrayBag<E>bag2){
        Object[] hold = bag2.getData();
        for(int i=0; i<bag2.size();i++){
            bag1.add((E)hold[i]);
        }
    }
}
