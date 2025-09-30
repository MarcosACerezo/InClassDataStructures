//This needs to be worked on
import java.util.Arrays;

class ArrayBag<E extends Comparable<E>> implements Cloneable {
    private E data[];
    private int manyItems;

    public ArrayBag( ) {
        final int INITIAL_CAPACITY = 10;
        manyItems = 0;
        data = (E[]) new Comparable[INITIAL_CAPACITY];
    }
    public void add(E element){
        if (manyItems == data.length)
            ensureCapacity((manyItems + 1)*2);
        data[manyItems] = element;
        manyItems++;
    }
    public void ensureCapacity(int minimumCapacity) {
        E biggerArray[ ];
        if (data.length < minimumCapacity) {
            biggerArray = (E[]) new Comparable[minimumCapacity];
            System.arraycopy(data, 0, biggerArray, 0, manyItems);
            data = biggerArray;
        }
    }


    public int countOccurrences(E target){
        int answer =0, index;
        for (index = 0; index < manyItems; index++)
            if (target.equals(data[index]))
                answer++;
        return answer;
    }
    public int getCapacity( ) {
        return data.length;
    }
    public int size( ) {
        return manyItems;
    }

    //O(n) runtime
    public int search(E target){
        for(int i=0; i<manyItems; i++){
            if(target.equals(data[i])) return i;
        }
        return -1;
    }

    //
    public static <E> int binarySearch(ArrayBag bag, int target) {
        //slows down to O(n^2) because we call the sort method every time
        Arrays.sort(bag.data, 0, bag.manyItems);
        //implement using compareTo method
        int left = 0;
        int right = data.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
        // Check if target is present at mid
            if ((int)data[mid] == target) {
                return mid;
            }
        // If target greater, ignore left half
            if ((int)data[mid] < target) {
                left = mid + 1;
            }
        // If target is smaller, ignore right half
            else {
            right = mid - 1;
            }
        }
        // Target was not found in the array
        return -1;
    }
}

public class Example {
    public static void main(String[] args) {
        ArrayBag<Integer> myBag = new ArrayBag<Integer>();
        myBag.add(20);
        myBag.add(-100);
        myBag.add(10);
        myBag.add(3);
        myBag.add(46);
        myBag.add(3);
        myBag.add(40);
        myBag.add(344534534);
        myBag.add(-2);
        System.out.println("There are "+ myBag.countOccurrences(3)+ " copies.");
        System.out.println("There are "+ myBag.countOccurrences(30)+ " copies.");
        int index = myBag.search(10);
        if(index == -1){
            System.out.println("Target not in list");
        }else{
            System.out.println("10 is at index: "+ index + ".");
        }
        

        System.out.println("Current size: " + myBag.size());
        System.out.println(myBag.binarySearch(-100));
    }
}
