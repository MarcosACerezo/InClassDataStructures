class BTNode2<E> {
    private E data;
    private BTNode2<E> left, right;

    public BTNode2(E initData, BTNode2<E> initLeft, BTNode2<E> initRight) {
        data = initData;
        left = initLeft;
        right = initRight;
    }

    public E getData() { 
        return data; 
    }

    public BTNode2<E> getLeft() { 
        return left; 
    }

    public BTNode2<E> getRight() { 
        return right; 
    }

    public void setData(E newData) { 
        data = newData; 
    }

    
    public void setLeft(BTNode2<E> newLeft) { 
        left = newLeft; 
    }

    public void setRight(BTNode2<E> newRight) { 
        right = newRight; 
    }

    public void print(int depth) {
        for (int i = 1; i <= depth; i++)
            System.out.print(" ");
        System.out.println(data);

        if (left != null)
            left.print(depth + 1);
        else if (right != null) {
            for (int i = 1; i <= depth + 1; i++)
                System.out.print(" ");
            System.out.println("--");
        }

        if (right != null)
            right.print(depth + 1);
        else if (left != null) {
            for (int i = 1; i <= depth + 1; i++)
                System.out.print(" ");
            System.out.println("--");
        }
    }
}

class BSTree<E extends Comparable<E>> {
    private BTNode2<E> root;

    public BSTree() { 
        root = null; 
    }

    public void add(E data) { 
        root = addNode(root, data); 
    }

    private BTNode2<E> addNode(BTNode2<E> node, E data) {
        if (node == null)
            return new BTNode2<E>(data, null, null);

        if (data.compareTo(node.getData()) <= 0)
            node.setLeft(addNode(node.getLeft(), data));
        
        else if (data.compareTo(node.getData()) > 0)
            node.setRight(addNode(node.getRight(), data));

        return node;
    }

    public BTNode2<E> getRoot(){
        return root;
    }

    // O(n) runtime
    public boolean contains(E target, BTNode2<E> node){
        // assumes that the tree is in order
        if(node == null) return false;
        if(target.compareTo(node.getData()) == 0)
            return true;
        
        else if(target.compareTo(node.getData()) < 0)
            return contains(target, node.getLeft());

        else
            return contains(target, node.getRight());

    }

    public boolean orderlessContains(E target, BTNode<E> node){
        if(node.getData().compareTo(target)==0) return true;
        return(orderlessContains(target,node.getLeft())||orderlessContains(target,node.getLeft()));
    }

    public void printInOrder(){
        printInOrder(root);
    }

    public void printInOrder(BTNode2<E> node){
        
        if(node.getLeft() != null)
            printInOrder(node.getLeft());
        
        System.out.println(node.getData());
          
        if(node.getRight() != null)
            printInOrder(node.getRight());
    }

    public int size(BTNode2<E> node){
        if(node == null)
            return 0;
        
        return 1 + size(node.getLeft()) + size(node.getRight());
    }
}

public class TreeExample {
    public static void main(String[] args) {
        BSTree<Integer> bst = new BSTree<Integer>();
        bst.add(50);
        bst.add(30);
        bst.add(70);
        bst.add(20);
        bst.add(40);
        bst.add(60);
        bst.add(80);
        bst.add(40); // duplicate; ignored by BST logic

        bst.printInOrder();
        System.out.println(bst.contains(30, bst.getRoot()));
        System.out.println(bst.contains(35, bst.getRoot()));

        System.out.println(bst.size(bst.getRoot()));
    }
}
