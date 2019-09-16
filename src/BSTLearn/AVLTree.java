package BSTLearn;

//参考博文 https://www.cnblogs.com/skywang12345/p/3577479.html#a2
//https://blog.csdn.net/u012124438/article/details/78026414

//注意Height()方法必须有
public class AVLTree<Key extends Comparable<Key>, Value> { //extends BSTree {
    //size及get方法由父类BSTree定义
    //不用继承BSTree的方法了
    //put的public接口无法重载，因为实现的时候用了泛型，这时jvm无法区分开两的方法
    //解决方法换用不同的参数
    private avlNode root;
    protected class avlNode {
        private Key key;
        private Value value;
        private int H; //树高
        private avlNode left;
        private avlNode right;

        public avlNode(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.H = -1;
        }

    }

    public int height()
    {
        return height(root);
    }
    private int height(avlNode x) {
        if(x==null) return 0;
        return x.H;
    }

    //get
    public Value get(Key key)
    {
        return get(root, key).value;
    }
    private avlNode get(avlNode x, Key key) {
        int cmp = key.compareTo(x.key);
        if(cmp<0)
        {
            return get(x.left, key);
        }
        else if(cmp>0)
        {
            return get(x.right, key);
        }
        return x;
    }

    public void put(Key key, Value value) {
//        if(isAVL)
        root = put(root, key, value);
//        else
//            System.out.println("参数错误，isAVL的参数应为true");
    }
    private avlNode put(avlNode x, Key key, Value value) {
        //若节点为空，则新建节点
        if(x==null)
        {
            x = new avlNode(key, value);
//            if(x==null)
//            {
//                System.out.println("ERROR");
//                return null;
//            }
        }
        else {
            //比较新插入的节点与当前节点的大小，直到递归调用put找到新节点的应该所在的位置
            //若出现不平衡的情况，则会在递归返回的时候时进行旋转调节
            int cmp = key.compareTo(x.key);
            if(cmp<0) {
                //左子树
                x.left = put(x.left, key, value);
                if(height(x.left)-height(x.right)>1) {
                    if(key.compareTo(x.left.key)<0)
                        x = LLRotation(x);
                    else
                        x = LRRotation(x);
                }
            }
            else if(cmp>0) {
                //右子树
                x.right = put(x.right, key, value);
                if(height(x.right)-height(x.left)>1) {
                    if(key.compareTo(x.right.key)>0)
                        x = RRRotation(x);
                    else
                        x = RLRotation(x);
                }
            }
            else {
                System.out.println("该节点已存在，更改value");
                x.value = value;
//                return null;
            }
        }
        //更新树高
        x.H = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    //最小节点
    public Value min()
    {
        return min(root).value;
    }
    private avlNode min(avlNode x) {
        if(x.left==null) return x;
        return min(x.left);
    }
    //最大节点
    public Value max()
    {
        return max(root).value;
    }
    private avlNode max(avlNode x) {
        if(x.right==null) return x;
        return max(x.right);
    }

    //删除最小节点
    public void deleteMin() {
        //更新root
        root = balance(deleteMin(root));
    }
    private avlNode deleteMin(avlNode x) {
        if(x.left==null)
        {
            return x.right;
        }
        x.left = deleteMin(x.left);
        return balance(x);
    }

    //删除最大节点
    public void deleteMax()
    {
        root = deleteMax(root);
    }
    private avlNode deleteMax(avlNode x) {
        if(x.right==null) return x.left;
        x.right = deleteMax(x.right);
        return balance(x);
    }

    //删除
    public void delete(Key key)
    {
        root = delete(root, key);
    }
    private avlNode delete(avlNode x, Key key) {
        if(x==null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp<0) {
            x.left = delete(x.left, key);
        }
        else if(cmp>0) {
            x.right = delete(x.right, key);
        }
        else {
            //两个子节点都不为null
            if(x.left!=null && x.right!=null) {
//                右子树比左子树高，选择右子树的最小节点代替删除节点
                if(height(x.right)>height(x.left)) {
                    avlNode temp = x;
                    x = min(temp.right);
                    x.right = deleteMin(temp.right);
                    x.left = temp.left;
                }
                else {
                    avlNode temp = x;
                    x = max(temp.left);
                    x.left = deleteMax(temp.left);
                    x.right = temp.right;
                }
                x.H = Math.max(height(x.left), height(x.right)) + 1;
            }
            else {
                return x.left==null ? x.right : x.left;
            }
        }
        return balance(x);
    }

    //中序打印AVL树各节点的value
    public void print() {
        System.out.println("中序遍历");
        if(root!=null) {
            print(root, root.key, root.value);
        }
    }
    private void print(avlNode x, Key key, Value value) {
        if(x==null) return;
        print(x.left, x.key, x.value);
        System.out.print(x.key);
        print(x.right, x.key, x.value);

    }
    //前序打印AVL树各节点的value
    public void printPre() {
        System.out.println("\n前序遍历");
        if(root!=null) {
            printPre(root, root.key, root.value);
        }
    }
    private void printPre(avlNode x, Key key, Value value) {
        if(x==null) return;
        System.out.print(x.key);
        printPre(x.left, x.key, x.value);
        printPre(x.right, x.key, x.value);

    }
    //后序打印AVL树各节点的value
    public void printPost() {
        if(root!=null) {
            printPost(root, root.key, root.value);
        }
    }
    private void printPost(avlNode x, Key key, Value value) {
        if(x==null) return;
        System.out.print(x.key);
        printPost(x.left, x.key, x.value);
        printPost(x.right, x.key, x.value);
    }

    //恢复平衡
    private avlNode balance(avlNode x) {
        if(x==null) return null;
        if(height(x.left)-height(x.right)>1) {
            if(height(x.left.left)>height(x.left.right))
                x = LLRotation(x);
            else
                x = LRRotation(x);
        }
        else if(height(x.right)-height(x.left)>1) {
            if(height(x.right.right)>height(x.right.left))
                x = RRRotation(x);
            else
                x = RLRotation(x);
        }
        x.H = Math.max(height(x.right), height(x.left)) + 1;
        return x;
    }

    //单旋转
    private avlNode LLRotation(avlNode k2) {
        avlNode k1;
        k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;

        k2.H = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.H = Math.max(height(k1.left), k2.H) + 1;

        return k1;
    }
    private avlNode RRRotation(avlNode k1) {
        avlNode k2;
        k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;

        k1.H = Math.max(height(k1.left), height(k1.right)) + 1;
        k2.H = Math.max(height(k2.right), k1.H) + 1;
        return k2;
    }
    //双旋转
    private avlNode LRRotation(avlNode k3) {
        k3.left = RRRotation(k3.left);
        return LLRotation(k3);
    }
    private avlNode RLRotation(avlNode k1) {
        k1.right = LLRotation(k1.right);
        return RRRotation(k1);
    }

}
