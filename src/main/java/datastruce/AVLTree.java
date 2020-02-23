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
        return reBalance(node);
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

    /**
     * 删除某一个节点
     *
     * @param key 待删除元素
     */
    public void remove(K key) {
        if (size == 0) {
            throw new RuntimeException("树中元素个数为0，没有可删除的元素");
        }
        root = remove(root, key);
    }

    /**
     * 删除某个节点下的某个元素
     * 删除只有右子树的节点，只需要让父节点指向自己的右子树
     * 删除只有做直属的节点，只需要让父节点指向自己的左子树
     * <p>
     * 删除左右子树都有的节点，要寻找一个与自己值临近的节点代替自己
     * 也就是用右子树的最小值代替该节点，然后删除右子树最小值
     *
     * @param node 节点
     * @param key  待删除元素
     * @return 返回删除元素后的节点
     */
    private Node remove(Node node, K key) {
        if (node == null) {
            throw new RuntimeException("删除失败，无此元素");
        }
        K current = node.key;
        if (key.compareTo(current) < 0) {
            node.left = remove(node.left, key);
        } else if (key.compareTo(current) > 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.left != null && node.right != null) {
                node.key = minimum(node.right);
                node.right = removeMinimum(node.right);
            } else if (node.left != null) {
                node = node.left;
            } else {
                node = node.right;
            }
            size--;
        }
        return reBalance(node);
    }

    /**
     * 删除最小值
     */
    public void removeMinimum() {
        if (size == 0) {
            throw new RuntimeException("树中元素个数为0，没有可删除的元素");
        }
        root = removeMinimum(root);
    }

    /**
     * 删除某子树下的最小值
     *
     * @param node 子树
     * @return 返回删除最小值后的子树
     */
    private Node removeMinimum(Node node) {
        if (node.left == null) {
            size--;
            return node.right;
        }
        node.left = removeMinimum(node.left);
        return node;
    }

    /**
     * 删除最大值
     */
    public void removeMaximum() {
        if (size == 0) {
            throw new RuntimeException("树中元素个数为0，没有可删除的元素");
        }
        root = removeMaximum(root);
    }

    /**
     * 删除某子树的最大值
     *
     * @param node 子树
     * @return 删除最大值后的子树
     */
    private Node removeMaximum(Node node) {
        if (node.right == null) {
            size--;
            return node.left;
        }
        node.right = removeMaximum(node.right);
        return node;
    }

    /**
     * 返回二叉树中最小值
     *
     * @return 二叉树中最小值
     */
    public K minimum() {
        if (size == 0) {
            throw new RuntimeException("树中元素个数为0，没有最小值");
        }
        return minimum(root);
    }

    private K minimum(Node node) {
        if (node.left == null) {
            return node.key;
        }
        return minimum(node.left);
    }

    /**
     * 返回二叉树最大值
     *
     * @return 二叉树中最大值
     */
    public K maximum() {
        if (size == 0) {
            throw new RuntimeException("树中元素个数为0，没有最大值");
        }
        return maximum(root);
    }

    public K maximum(Node node) {
        if (node.right == null) {
            return node.key;
        }
        return maximum(node.right);
    }

    private Node reBalance(Node node) {
        if (node == null) {
            return null;
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
}
