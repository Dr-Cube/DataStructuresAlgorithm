package BSTLearn;

//二叉查找树，主要参考橙书p250
//所有方法要有一个对外暴露的接口
public class BSTree<Key extends Comparable<Key>, Value> {
    private Node root;
    private class Node
    {
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private int N;
//        private int H;

        public Node(Key key, Value value, int N)
        {
            this.key = key;
            this.value = value;
            this.N = N;
//            this.H = -1;
        }
    }

    //public修饰的size方法为外部接口
    public int size()
    {
        return size(root);
    }
    private int size(Node x)
    {
        if(x==null) return 0;
        else return x.N;
    }

    //得到key节点的value
    public Value get(Key key)
    {
        return get(root, key);
    }
    private Value get(Node x, Key key)
    {
        if(x==null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp<0) return get(x.left, key);
        else if(cmp>0) return get(x.right, key);
        else return x.value;
    }

    //插入
    public void put(Key key, Value value)
    {
        root = put(root, key, value);
    }
    private Node put(Node x, Key key, Value value)
    {
        if(x==null) return new Node(key, value, 1);
        int cmp = key.compareTo(x.key);
        if(cmp<0) x.left = put(x.left, key, value);
        else if(cmp>0) x.right = put(x.right, key, value);
        else
        {
            System.out.println("该节点已存在，更改value");
            x.value = value;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min()
    {
        return min(root).key;
    }
    private Node min(Node x)
    {
        if(x.left==null) return x;
        return min(x.left);
    }

    public Key max()
    {
        return max(root).key;
    }
    private Node max(Node x)
    {
        if(x.right==null) return x;
        return x.right;
    }

    //删除最小节点
    public void deleteMin()
    {
        //更新root
        root = deleteMin(root);
    }
    private Node deleteMin(Node x)
    {
        if(x.left==null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    //删除节点
    public void delete(Key key)
    {
        root = delete(root, key);
    }
    private Node delete(Node x, Key key)
    {
        if(x==null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp<0) x.left = delete(x.left, key);
        else if(cmp>0) x.right = delete(x.right, key);
        else
        {
            if(x.left==null) return x.right;
            if(x.right==null) return x.left;
            Node temp = x;
            x = min(temp.right);
            x.right = deleteMin(temp.right);
            x.left = temp.left;
        }
        x.N = size(x.right) + size(x.left) + 1;
        return x;
    }

    //中序打印bst各节点的value
    public void print()
    {
        System.out.println("中序遍历");
        if(root!=null)
        {
            print(root, root.key, root.value);
        }
    }
    private void print(Node x, Key key, Value value)
    {
        if(x==null) return;
        print(x.left, x.key, x.value);
        System.out.print(x.key);
        print(x.right, x.key, x.value);
    }
    //前序打印bst各节点的value
    public void printPre()
    {
        System.out.println("\n前序遍历");
        if(root!=null)
        {
            printPre(root, root.key, root.value);
        }
    }
    private void printPre(Node x, Key key, Value value)
    {
        if(x==null) return;
        System.out.print(x.key);
        printPre(x.left, x.key, x.value);
        printPre(x.right, x.key, x.value);
    }
    //后序打印bst各节点的value
    public void printPost()
    {
        if(root!=null)
        {
            printPost(root, root.key, root.value);
        }
    }
    private void printPost(Node x, Key key, Value value)
    {
        if(x==null) return;
        System.out.print(x.key);
        printPost(x.left, x.key, x.value);
        printPost(x.right, x.key, x.value);
    }


}
