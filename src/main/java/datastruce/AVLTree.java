package datastruce;

public class AVLTree<T extends Comparable> extends BinarySearchTree<T> {

    private class AVLNode extends BSTNode {

        private int high;

        public AVLNode(T data){
            super(data);
        }
    }


}
