package datastruce;

/**
 * 平衡二叉树
 *
 * @param <T>
 */
public class AVLTree<T extends Comparable> {

    private class AVLNode {

        private T data;
        private AVLNode left;
        private AVLNode right;
        private boolean console;
        private int high;

        public AVLNode(T data) {
            this.data = data;
            this.console = false;
            this.left = null;
            this.right = null;
            this.high = 0;
        }
    }

    private AVLNode root;
    private int size;

    public void add(T data) {
        if (root == null) {
            root = new AVLNode(data);
        } else {
            add(root, data);
        }
    }

    private void add(AVLNode avlNode, T data) {
        T current = avlNode.data;
        if (data.compareTo(current) < 0) {
            if (avlNode.left == null) {
                avlNode.left = new AVLNode(data);
                size++;
            } else {
                add(avlNode.left, data);
            }
        } else if (data.compareTo(current) > 0) {
            if (avlNode.right == null) {
                avlNode.right = new AVLNode(data);
                size++;
            } else {
                add(avlNode.right, data);
            }
        } else {
            throw new RuntimeException("已有相等元素在此树中");
        }
        avlNode.high++;
        int balanceFactor = getBalanceFactor(avlNode);
        if (Math.abs(balanceFactor) > 2) {
            if (balanceFactor > 0) {

            } else {

            }
        }
    }

    private void rightRotate(AVLNode avlNode) {

    }

    private void leftRotate(AVLNode avlNode) {

    }

    private int getBalanceFactor(AVLNode avlNode) {
        if (avlNode == null) {
            return 0;
        }
        return getHigh(avlNode.left) - getHigh(avlNode.right);
    }

    private int getHigh(AVLNode avlNode) {
        if (avlNode == null) {
            return 0;
        }
        return avlNode.high;
    }

}
