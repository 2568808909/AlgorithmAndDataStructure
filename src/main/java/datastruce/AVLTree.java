package datastruce;

import java.util.ArrayList;
import java.util.List;

/**
 * 平衡二叉树
 * 最早的自平衡二分搜索结构
 * 满二叉树：除了叶子结点外，其他的所有节点都有左右两颗子树
 * 完全二叉树：非叶子节点的右子树可能为空，但左子树一定存在
 * 平衡二叉树：对于任意一个节点，其左子树与右子树的高度相差不能超过1
 */
public class AVLTree<K extends Comparable<K>, V> {

    private class Node {
        private K key;
        private V value;
        private Node left, right;
        private int height; //当前节点高度值

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            height = 1;
        }


    }

    private Node root;
    private int size;

    AVLTree() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int getHeight(Node node) {
        return node == null ? 0 : node.height;
    }

    public void add(K key, V value) {
        root = add(key, value, root);
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
        //更新高度
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        //计算平衡因子
        int balanceFactor = getBalanceFactor(node);

        //不满足平衡二叉树条件
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {//LL 进行一次右旋转
            return rightRotate(node);
        } else if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {//LR 对左子树进行一次左旋转后，自身进行一次进行一次右旋转
            node.left = leftRotate(node.left);
            return rightRotate(node);
        } else if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {//RR 进行一次左旋转
            return leftRotate(node);
        } else if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {//RL 对右子树进行一次右旋转后，自身进行一次进行一次左旋转
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private int getBalanceFactor(Node node) {
        return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    private Node leftRotate(Node node) {
        Node result = node.right;
        Node lLeft = result.left;

        result.left = node;
        node.right = lLeft;
        //更新高度
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        result.height = Math.max(getHeight(result.left), getHeight(result.right)) + 1;
        return result;
    }

    private Node rightRotate(Node node) {
        Node result = node.left;
        Node rRight = result.right;
        result.right = node;
        node.left = rRight;
        //更新高度
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        result.height = Math.max(getHeight(result.left), getHeight(result.right)) + 1;
        return result;
    }

    //判断该二叉树是否为二分搜索树
    public boolean isBST() {
        List<K> list = new ArrayList<>();
        inOrder(root, list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).compareTo(list.get(i - 1)) <= 0)
                return false;
        }
        return true;
    }

    /**
     * 判断该树是否平衡
     *
     * @return
     */
    public boolean isBalance() {
        return isBalance(root);
    }


    private boolean isBalance(Node node) {
        if (node == null) return true;
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }
        return isBalance(node.left) && isBalance(node.right);
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
