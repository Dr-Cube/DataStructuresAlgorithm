package BSTLearn;

public class RedBlackTree<Key extends Comparable<Key>, Value> {
    private rbNode root;
    private class rbNode{
        private Key key;
        private Value value;
        private boolean color;
        private rbNode left;
        private rbNode right;

    }
}
