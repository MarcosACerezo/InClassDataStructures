public class PhoneDirectory{
    public static void main(String[] args){
        Table directory = new Table(7);
        directory.put("Hope", 2110);
        directory.put("Sophia", 4626);
        directory.put("Claire", 2433);
        directory.put("Freidrich", 3545);
        directory.put("Leonardo", 1100);
        directory.put("Layla", 3363);
        directory.put("Astrid", 8927);
        // directory.put("Haruto", 8888);
        
        String hold = "(\"Hope\", 2110), (\"Sophia\", 4625), (\"Claire\", 2433),\r\n" + //
            "(\"Friedrich\", 3545), (\"Leonardo\", 1100), ( \"Layla\", 3363),\r\n" + //
            "(\"Astrid\", 8927), ( \"Haruto\", 8888).";
        String[][] inputValue = Table.split(hold);
        // for(int i=0; i<inputValue.length; i++){
        //     directory.put(inputValue[i][0],inputValue[i][1]);
        // }
        for(int i=0; i<inputValue.length; i++){
            System.out.println(inputValue[i][0]+": "+inputValue[i][0].hashCode());
        }

        System.out.printf("Size before removal: %s", directory.size());
        System.out.println(directory);
        //Cannot search for the extension because the table is not designed in that manner
        directory.remove("Claire");
        System.out.printf("Size after removal: %s", directory.size());
        
        System.out.println(directory);
        //best case
        //worst case



    }
}

class Table {
    private int manyItems;
    private Object[ ] keys;
    private Object[ ] data;
    private boolean[ ] hasBeenUsed;

    public Table(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("Capacity is negative");
        keys = new Object[capacity];
        data = new Object[capacity];
        hasBeenUsed = new boolean[capacity];
    }

    public boolean containsKey(Object key) {
        return findIndex(key) != -1;
    }

    private int findIndex(Object key) {
    int count = 0;
    int i = hash(key);
        while (count < data.length && hasBeenUsed[i]) {
            if (key.equals(keys[i]))
                return i;
            count++;
            i = nextIndex(i);
        }
        return -1;
    }

    private int hash(Object key){
        System.out.println(Math.abs(key.hashCode( )%10) % data.length);
        return Math.abs(key.hashCode( )%10) % data.length;
    }
    private int nextIndex(int i) {
        if (i+1 == data.length)
            return 0;
        else
            return i+1;
    }
    public Object put(Object key, Object element) {
        int index = findIndex(key);
        Object answer;
        if (index != -1) { // The key is already in the table.
            answer = data[index];
            data[index] = element;
            return answer;
        }
        else if (manyItems < data.length) { // The key is not yet in this Table.
            index = hash(key);
            while (keys[index] != null)
                index = nextIndex(index);
            keys[index] = key;
            data[index] = element;
            hasBeenUsed[index] = true;
            manyItems++;
            return null;
        }
        else // The table is full.
            throw new IllegalStateException("Table is full.");
    }
    
    public Object remove(Object key){
        int index = findIndex(key);
        Object answer = null;
        if (index != -1) {
            answer = data[index];
            keys[index] = null;
            data[index] = null;
            manyItems--;
        }
        return answer;
    }
        
    public Object get(Object key) {
        int index = findIndex(key);
        if (index == -1)
            return null;
        else
            return data[index];
    }
    
    public int size(){
        return manyItems;
    }
    

    public static String[][] split(String input){
        String modInput = input.replace("(","").replace(")","");
        String[] hold = input.split(",");
        String[][] returnval = new String[hold.length/2][2];
        for(int i=0; i<hold.length; i++){
            returnval[i/2][i%2] = hold[i];
        }
    return returnval;
    }
    @Override
    public String toString(){
        String response = "";
        System.out.println();
        for(int i=0; i<manyItems;i++){
            response += String.format("Person: %s \t Code: %s\n", keys[i], data[i]);
        }
        return response;
    }
}
