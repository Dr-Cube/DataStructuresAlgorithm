import java.lang.reflect.Array;

public class Stack<T> {
//    private T[] data;
    private Object[] data;
    private int maxSize;
    private int top;

    /*
    泛型
    1.不能实例化类型变量， 如T obj = new T ();
    2. 不能实例化泛型数组，如T [] arr = new T[3];
    解决方法：
    利用强制类型转换或者反射
     */

    //泛型擦除
    //https://www.cnblogs.com/penghuwan/p/8420791.html
    //https://www.jianshu.com/p/e618b81aadb4

    //应用强制类型转换方法
    public Stack(int maxSize){
        this.maxSize = maxSize;
        //java type parameter cannot be instantiated directly
        data = new Object[maxSize];
        this.top = -1;
    }

    //应用反射方法
//    public Stack(Class<T> tClass, int maxSize){
//        this.maxSize = maxSize;
//        //java type parameter cannot be instantiated directly
//        data = (T[]) Array.newInstance(tClass, maxSize);
//        this.top = -1;
//    }

    public int getMaxSize(){
        return maxSize;
    }

    public boolean empty(){
        return top == -1;
    }

    public boolean isFull(){
        return top + 1 == maxSize;
    }

    public int getElementCount(){
        return top + 1;
    }

    //java中的Stack类是不定长的，所以push方法返回的是Object x
    public boolean push(Object x){
        if(isFull()){
            System.out.println("当前栈满");
            return false;
        }
        this.data[++top] = x;
        return true;
    }

    //java中的Stack类的pop方法返回的是栈顶Object
    public T pop() throws Exception{
        if(empty()) {
//            System.out.println("当前栈空");
            throw new Exception("当前栈空");
        }
        return (T) this.data[top--];
    }

    public Object peek() throws Exception{
        if(empty()){
            throw new Exception("当前栈空");
        }
        return (T) this.data[top];
    }

    public static void main(String[] args) throws Exception {
        Stack<Integer> stack = new Stack<>(5);
        stack.push(3);
        stack.push(4);
        stack.push(2);
        stack.pop();
        System.out.println(stack.peek());
        stack.pop();
        System.out.println(stack.peek());

        Stack<String> stringStack = new Stack<>(5);
        stringStack.push("Hello");
        stringStack.push("world");
        stringStack.push("java");
        stringStack.pop();
        System.out.println(stringStack.peek());
        stringStack.push("stack");
        System.out.println(stringStack.peek());

    }
}
