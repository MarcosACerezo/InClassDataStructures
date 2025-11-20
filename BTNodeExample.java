class BTNode<E> {
    private E data;
    private BTNode<E> left, right;

    public BTNode(E initialData, BTNode<E> initialLeft, BTNode<E> initialRight) {
        data = initialData;
        left = initialLeft;
        right = initialRight;
    }

    public E getData() { 
        return data; 
    }

    public BTNode<E> getLeft() { 
        return left; 
    }

    public BTNode<E> getRight() { 
        return right; 
    }

    public void setData(E newData) { 
        data = newData; 
    }

    public void setLeft(BTNode<E> newLeft) { 
        left = newLeft; 
    }

    public void setRight(BTNode<E> newRight) { 
        right = newRight; 
    }

    public void print(int depth) {
        for (int i = 1; i <= depth; i++)
            System.out.print(" ");

        System.out.println(data);

        if (left != null) {
            left.print(depth + 1);
        } else if (right != null) {
            for (int i = 1; i <= depth + 1; i++)
                System.out.print(" ");
            System.out.println("--");
        }

        if (right != null) {
            right.print(depth + 1);
        } else if (left != null) {
            for (int i = 1; i <= depth + 1; i++)
                System.out.print(" ");
            System.out.println("--");
        }
    }

    public static <E> long treeSize(BTNode<E> root) {
        if (root == null)
            return 0;
        else
            return 1 + treeSize(root.left) + treeSize(root.right);
    }

    public static <E> BTNode<E> buildTree(E[] vals, int[] positions, int index){
        //assumes loose leafs are on left
        //assumes left values are first in array
        //assumes that positions are in order, list from top to cottom
      BTNode<E> root = new BTNode<E>(vals[index], null, null);
      boolean left = true;
      for (int i = 0; i< positions.length; i++){
           if(positions[i] == index){
               if(left) { 
                root.setLeft(buildTree(vals, positions, i));
                left = false;
               } else { root.setRight(buildTree(vals, positions, i));}
           }
      }
      return root;
    }

    public BTNode<E> getLeftMost(){
        BTNode cursor = this;
        while(cursor.left != null){
            cursor=cursor.getLeft();
        }
        return cursor;

    }
    public BTNode<E> getRightMost(){
        BTNode cursor = this;
        while(cursor.right != null){
            cursor=cursor.getRight();
        }
        return cursor;

    }
}



public class BTNodeExample{
    public static void main(String[] args){
        BTNode<Integer> test = BTNode.buildTree(new Integer[]{1,7,9,2,6,9,5,11,5},new int[]{-1,0,0,1,1,2,4,4,5},0);
  
        BTNode<Integer> root = new BTNode(1,
            new BTNode<Integer>(7, 
            new BTNode<Integer>(2, null, null), 
            new BTNode<Integer>(6, new BTNode<Integer>(5, null, null), new BTNode<Integer>(11, null, null))), 
            new BTNode<Integer>(9, null, new BTNode<Integer>(9, 
            new BTNode<Integer>(5, null, null), null)));

        System.out.println("Printed Tree:");
        root.print(0);
        System.out.printf("Nodes in tree: %d\n", BTNode.treeSize(root));
        System.out.printf("Leftmost Node: %s\n", root.getLeftMost().getData());
        System.out.printf("Rightmost Node: %s\n", root.getRightMost().getData());
    }
}


