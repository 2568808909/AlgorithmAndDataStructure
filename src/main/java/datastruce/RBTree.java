package datastruce;

import java.util.ArrayList;
import java.util.List;

/**
 * 红黑树
 */
public class RBTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        private K key;
        private V value;
        private Node left, right;
        private boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.color = RED;
        }
    }

    private Node root;
    private int size;

    RBTree() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(K key, V value) {
        root = add(key, value, root);
        root.color = BLACK;
    }

    /**
     * 判断一个节点是否为红色，空节点也算为黑色
     *
     * @param node 要判断的节点
     * @return 非空且节点为红色返回true，否则返回false
     */
    private boolean isRed(Node node) {
        if (node == null) return false;
        return node.color == RED;
    }

    /**
     * 颜色反转
     *
     * @param node 要反转的节点
     */
    private void flipColor(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = RED;
    }

    private Node add(K key, V value, Node node) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        K current = node.key;
        if (key.compareTo(current) < 0) {
            node.left = add(key, value, node.left);
        } else if (key.compareTo(current) > 0) {
            node.right = add(key, value, node.right);
        } else {
            node.value = value;
        }
        return node;
    }

    /**
     * 左旋转
     *
     * @param node
     * @return
     */
    private Node leftRotate(Node node) {
        Node result = node.right;
        Node lLeft = result.left;
        result.left = node;
        node.right = lLeft;
        result.color = node.color;
        node.color = RED;
        return result;
    }

    /**
     * 右旋转
     *
     * @param node
     * @return
     */
    private Node rightRotate(Node node) {
        Node result = node.left;
        Node rRight = result.right;
        result.right = node;
        node.left = rRight;
        result.color = node.color;
        node.color = RED;
        return result;
    }

    /**
     * 判断该二叉树是否为二分搜索树
     * 中序遍历访问二分搜索树会按照元素大小顺序访问
     *
     * @return
     */
    public boolean isBST() {
        List<K> list = new ArrayList<>();
        inOrder(root, list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).compareTo(list.get(i - 1)) <= 0)
                return false;
        }
        return true;
    }

    private void inOrder(Node node, List<K> keys) {
        if (node == null) {
            return;
        }
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

}
