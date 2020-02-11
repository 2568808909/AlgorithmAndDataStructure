package datastruce;

public class Test {
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree=new BinarySearchTree<>();
        tree.add(5);
        tree.add(3);
        tree.add(6);
        tree.add(2);
        tree.add(4);
        tree.add(8);
        tree.add(7);

        tree.preOrderNR();
        tree.remove(3);
        System.out.println();
        tree.preOrderNR();
    }
}
