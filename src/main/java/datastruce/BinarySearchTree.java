package datastruce;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二分搜素树(非平衡)
 *
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable> {

    private class BSTNode {
        private T data;
        private BSTNode left;
        private BSTNode right;
        private boolean console;

        private BSTNode(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.console = false;
        }
    }

    private BSTNode root;
    private int size;

    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * 向外部暴露添加数据的接口
     *
     * @param data 要添加的数据
     */
    public void add(T data) {
        if (root == null) {
            root = new BSTNode(data);
            size = 1;
        } else {
            add(root, data);
        }
    }

    /**
     * 向某个节点添加数据
     *
     * @param node 节点
     * @param data 数据
     */
    private void add(BSTNode node, T data) {
        T current = node.data;
        if (data.compareTo(current) < 0) {
            if (node.left == null) {
                node.left = new BSTNode(data);
                size++;
            } else {
                add(node.left, data);
            }
        } else if (data.compareTo(current) > 0) {
            if (node.right == null) {
                node.right = new BSTNode(data);
                size++;
            } else {
                add(node.right, data);
            }
        } else {
            throw new RuntimeException("已有相等元素在此树中");
        }
    }

    /**
     * 查询一个元素是否存在于树中
     *
     * @param data 待查询元素
     * @return 存在则返回true，否则为false
     */
    public boolean contain(T data) {
        return contain(root, data);
    }

    /**
     * 查询一个元素是否在一个节点的子树下
     *
     * @param bstNode 节点
     * @param data    元素
     * @return 存在则返回true，否则为false
     */
    private boolean contain(BSTNode bstNode, T data) {
        if (bstNode == null) {
            return false;
        }
        T current = bstNode.data;
        if (data.compareTo(current) < 0) {
            return contain(bstNode.left, data);
        } else if (data.compareTo(current) > 0) {
            return contain(bstNode.right, data);
        } else {
            return true;
        }
    }

    /**
     * 返回树中元素个数
     *
     * @return 树中元素个数
     */
    public int size() {
        return size;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(BSTNode bstNode) {
        if (bstNode == null) return;
        System.out.println(bstNode.data);
        preOrder(bstNode.left);
        preOrder(bstNode.right);
    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        inOrder(root);
    }


    private void inOrder(BSTNode bstNode) {
        if (bstNode == null) return;
        inOrder(bstNode.left);
        System.out.println(bstNode.data);
        inOrder(bstNode.right);
    }

    /**
     * 后序遍历
     */
    public void lastOrder() {
        lastOrder(root);
    }

    private void lastOrder(BSTNode bstNode) {
        if (bstNode == null) return;
        lastOrder(bstNode.left);
        lastOrder(bstNode.right);
        System.out.println(bstNode.data);
    }

    /**
     * 前序遍历非递归实现
     */
    public void preOrderNR() {
        Stack<BSTNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            BSTNode current = stack.pop();
            System.out.println(current.data);
            //由于栈先进后出，所以先将右子树压入栈，再讲左子树压入栈
            if (current.right != null) stack.push(current.right);
            if (current.left != null) stack.push(current.left);
        }
    }

    /**
     * 中序遍历非递归实现
     */
    public void inOrderNR() {
        Stack<BSTNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            BSTNode current = stack.peek();
            if (current.left != null && !current.left.console) {
                stack.push(current.left);
            } else {
                System.out.println(current.data);
                BSTNode console = stack.pop();
                console.console = true;
                if (current.right != null) {
                    stack.push(current.right);
                }
            }
        }
    }

    /**
     * 后序遍历非递归
     */
    public void lastOrderNR() {
        Stack<BSTNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            BSTNode current = stack.peek();
            if (current.left != null && !current.left.console) {
                stack.push(current.left);
            } else if (current.right != null && !current.right.console) {
                stack.push(current.right);
            } else {
                BSTNode console = stack.pop();
                System.out.println(console.data);
                console.console = true;
            }
        }
    }

    /**
     * 层序遍历
     */
    public void levelOrder() {
        Queue<BSTNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            BSTNode current = queue.remove();
            System.out.println(current.data);
            if (current.left != null) queue.offer(current.left);
            if (current.right != null) queue.offer(current.right);
        }
    }

    /**
     * 返回二叉树中最小值
     *
     * @return 二叉树中最小值
     */
    public T minimum() {
        return minimum(root);
    }

    private T minimum(BSTNode bstNode) {
        if (bstNode.left == null) {
            return bstNode.data;
        }
        return minimum(bstNode.left);
    }

    /**
     * 返回二叉树最大值
     *
     * @return 二叉树中最大值
     */
    public T maximum() {
        return maximum(root);
    }

    public T maximum(BSTNode bstNode) {
        if (bstNode.right == null) {
            return bstNode.data;
        }
        return maximum(bstNode.right);
    }

    /**
     * 删除最小值
     */
    public void removeMinimum() {
        root = removeMinimum(root);
    }

    /**
     * 删除某子树下的最小值
     * @param bstNode 子树
     * @return 返回删除最小值后的子树
     */
    private BSTNode removeMinimum(BSTNode bstNode) {
        if (bstNode.left == null) {
            size--;
            return bstNode.right;
        }
        bstNode.left = removeMinimum(bstNode.left);
        return bstNode;
    }

    /**
     * 删除最大值
     */
    public void removeMaximum() {
        root = removeMaximum(root);
    }

    /**
     * 删除某子树的最大值
     * @param bstNode 子树
     * @return 删除最大值后的子树
     */
    private BSTNode removeMaximum(BSTNode bstNode) {
        if (bstNode.right == null) {
            size--;
            return bstNode.left;
        }
        bstNode.right = removeMaximum(bstNode.right);
        return bstNode;
    }

    /**
     * 删除某一个节点
     * @param data 待删除元素
     */
    public void remove(T data) {
        root = remove(root, data);
    }

    /**
     * 删除某个节点下的某个元素
     * 删除只有右子树的节点，只需要让父节点指向自己的右子树
     * 删除只有做直属的节点，只需要让父节点指向自己的左子树
     *
     * 删除左右子树都有的节点，要寻找一个与自己值临近的节点代替自己
     * 也就是用右子树的最小值代替该节点，然后删除右子树最小值
     * @param bstNode 节点
     * @param data 待删除元素
     * @return 返回删除元素后的节点
     */
    private BSTNode remove(BSTNode bstNode, T data) {
        if (bstNode == null) {
            throw new RuntimeException("删除失败，无此元素");
        }
        T current = bstNode.data;
        if (data.compareTo(current) < 0) {
            bstNode.left = remove(bstNode.left, data);
        } else if (data.compareTo(current) > 0) {
            bstNode.right = remove(bstNode.right, data);
        } else {
            if (bstNode.left != null && bstNode.right != null) {
                bstNode.data = minimum(bstNode.right);
                bstNode.right = removeMinimum(bstNode.right);
            } else if (bstNode.left != null) {
                bstNode = bstNode.left;
            } else {
                bstNode = bstNode.right;
            }
        }
        return bstNode;
    }

}
