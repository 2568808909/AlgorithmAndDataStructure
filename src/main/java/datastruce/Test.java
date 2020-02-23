package datastruce;

public class Test {
    public static void main(String[] args) {
        AVLTree<Integer,Integer> tree=new AVLTree<>();
        tree.add(12,1);
        tree.add(8,1);
        tree.add(18,1);
        tree.add(5,1);
        tree.add(11,1);
        tree.add(3,1);
        tree.add(7,1);
        tree.add(2,1);

        System.out.println(tree.isBalance());
        System.out.println(tree.isBST());
    }
}
