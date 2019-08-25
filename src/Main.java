import BSTLearn.BSTree;
import BSTLearn.AVLTree;
import java.util.*;
import java.math.*;
import java.text.*;
import java.io.*;
import BSTLearn.*;
public class Main {
    public static void main(String[] args){
        int[] arr = {4, 27, 6, 34, 29, 17, 0, 7, 10, 31};
//        System.out.printf("希尔排序结果为：%s\n\n", Arrays.toString(SortLearn.ShellSort(arr.clone())));

        BSTree<Integer, Character> tree = new BSTree<>();
        tree.put(4, 'o');
        tree.put(5, ',');
        tree.put(2, 'l');
        tree.put(0, 'h');
        tree.put(1, 'e');
        tree.put(9, 'a');
        tree.put(3, 'l');
        tree.put(6, 'j');
        tree.put(8, 'v');
        tree.put(7, 'a');
        System.out.println("bst");
        tree.print();
        tree.printPre();
//        tree.delete(1);
//        tree.delete(6);
//        tree.delete(8);
        System.out.println("\n删除8");
        tree.print();
        tree.printPre();
//        tree.put(8, 'v');
//        tree.print();
//        tree.printPre();
//        tree.deleteMin();
//        System.out.println("\n删除最小节点");
//        tree.print();
//        tree.printPre();
//
        System.out.println("\n\navl树");
        AVLTree<Integer, Character> avl = new AVLTree<>();
        avl.put(0, 'h');
        avl.put(9, 'a');
        avl.put(6, 'j');
        avl.put(7, 'a');
        avl.put(8, 'v');
        avl.put(1, 'e');
        avl.put(2, 'l');
        avl.put(5, ',');
        avl.put(3, 'l');
        avl.put(4, 'o');
//        avl.print();
//        System.out.println(avl.get(8));
//        avl.print();
//        avl.delete(4);
//        System.out.println("\n删除根节点逗号");
//        avl.deleteMax();
        avl.print();
        avl.printPre();
//        System.out.println("\n删除最小值");
//        avl.deleteMin();
        System.out.println("\n\n删除7");
        avl.delete(7);
        avl.print();
        avl.printPre();

        System.out.println("\n插入5, d");
        avl.put(5, 'd');
        avl.print();
        avl.printPre();

        System.out.println("\n删除8");
        avl.delete(8);
        avl.print();
        avl.printPre();

        System.out.println("\n\n删除9");
        avl.delete(9);
        avl.print();
        avl.printPre();

    }
}
