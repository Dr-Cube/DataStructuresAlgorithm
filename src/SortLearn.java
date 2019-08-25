import java.util.*;
import java.math.*;
import java.text.*;
import java.io.*;
import java.util.stream.IntStream;

public class SortLearn {
    public static void main(String[] args){
        int[] arr = {4, 23, 7, 34, 129, 18, 0, 116, 11, 32, 25};
        System.out.printf("未排序数组为：%s\n\n", Arrays.toString(arr.clone()));
        //比较排序
        System.out.printf("选择排序结果为：%s\n\n", Arrays.toString(SelectionSort(arr.clone())));
        System.out.printf("冒泡排序结果为：%s\n\n", Arrays.toString(BubbleSort(arr.clone())));
        System.out.printf("插入排序结果为：%s\n\n", Arrays.toString(InsertionSort(arr.clone())));
        System.out.printf("希尔排序结果为：%s\n\n", Arrays.toString(ShellSort(arr.clone())));
        System.out.printf("归并排序结果为：%s\n\n", Arrays.toString(MergeSort(arr.clone())));
        System.out.printf("快速排序结果为：%s\n\n", Arrays.toString(QuickSort(arr.clone())));
        System.out.printf("堆排序结果为：%s\n\n", Arrays.toString(HeapSort(arr.clone())));

        //非比较排序
        System.out.printf("计数排序结果为：%s\n\n", Arrays.toString(CountingSort(arr.clone())));
        System.out.printf("桶排序结果为：%s\n\n", Arrays.toString(BucketSort(arr.clone())));
        System.out.printf("基数排序结果为：%s\n\n", Arrays.toString(RadixSort(arr.clone())));
    }

    //排序方法都有返回值是为了方便同时演示所有排序方法
    //比较排序
    //选择排序
    public static int[] SelectionSort(int[] arr)
    {
        System.out.println("选择排序");
        int minIdx;
        for(int i=0;i<arr.length-1;i++)
        {
            minIdx = i;
            for(int j=i+1;j<arr.length;j++)
            {
                if(arr[j]<arr[minIdx])
                {
                    minIdx = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = temp;

            System.out.printf("第%d趟结果为：%s\n", i+1, Arrays.toString(arr));
        }
        return arr;
    }

    //冒泡排序
    public static int[] BubbleSort(int[] arr)
    {
        System.out.println("冒泡排序");
        for(int i=0;i<arr.length-1;i++)
        {
            for(int j=0;j<arr.length-i-1;j++)
            {
                if(arr[j]>arr[j+1])
                {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            System.out.printf("第%d趟结果为：%s\n", i+1, Arrays.toString(arr));
        }
        return arr;
    }

    //插入排序
    public static int[] InsertionSort(int[] arr)
    {
        System.out.println("插入排序");
        int cur, j;
        for(int i=1;i<arr.length;i++)
        {
            j = i;
            cur = arr[i];
            while(j-1>=0 && cur<arr[j-1])
            {
                arr[j] = arr[j-1];
                arr[j-1] = cur;
                j--;
            }
            System.out.printf("第%d趟结果为：%s\n", i, Arrays.toString(arr));
        }
        return arr;
    }

    //希尔排序
    public static int[] ShellSort(int[] arr)
    {
        System.out.println("希尔排序");
        int len = arr.length;
        int cnt = 1;
        int cur, j;
        for(int gap=len/2;gap>0;gap=gap/2)
        {
            //注意此处i的终止条件，i和简单插入排序一样要跑到最后的
            for(int i=gap;i<len;i++)
            {
                j = i;
                cur = arr[j];
                while(j-gap>=0 && cur<arr[j-gap])
                {
                    arr[j] = arr[j-gap];
                    arr[j-gap] = cur;
                    j -= gap;
                }
            }
            System.out.printf("第%d趟增量为%d，结果为：%s\n", cnt, gap, Arrays.toString(arr));
            cnt++;
        }

        return arr;
    }

    //归并排序，参考橙书p170
    //Top-Down 递归
    private static int[] mergeHelperArr;
    public static int[] MergeSort(int[] arr)
    {
        System.out.println("归并排序");
        mergeHelperArr = new int[arr.length];
        return mergeHelper(arr, 0, arr.length-1);
    }
    private static int[] mergeHelper(int[] arr, int low, int high)
    {
        if(high<=low)
        {
            return arr;
        }
        //亦为二分查找的mid计算方法，此法可避免(low+high)/2的溢出问题，如low+high>Integer.MAX_VALUE时
        int mid = low + (high - low)/2;
        mergeHelper(arr, 0, mid);
        mergeHelper(arr, mid+1, high);
        merge(arr, low, mid, high);

        return arr;
    }
    //原地归并
    private static void merge(int[] arr, int low, int mid, int high)
    {
        int i = low, j = mid + 1;
        int len = high - low + 1;
        System.arraycopy(arr, low, mergeHelperArr, low, len);
//        for(int k=low;k<=high;k++)
//        {
//            temp[k] = ArrToMerge[k];
//        }
        for(int k=low;k<=high;k++)
        {
            //i++ 先赋值，再加1
            /*语句arr[k] = mergeHelperArr[j++];
            相当于如下两句：
            arr[k] = mergeHelperArr[j];
            j++;
             */
            if(i>mid) arr[k] = mergeHelperArr[j++];
            else if(j>high) arr[k] = mergeHelperArr[i++];
            else if(arr[i]<arr[j]) arr[k] = mergeHelperArr[i++];
            else if(arr[i]>arr[j]) arr[k] = mergeHelperArr[j++];
        }
    }

    //快速排序，参考算法导论p85,86
    public static int[] QuickSort(int[] arr){
        System.out.println("快速排序");
        return QSort(arr, 0, arr.length-1);
    }
    private static int[] QSort(int[] arr, int left, int right)
    {
        if(left>=right) return arr;
        int v = partition(arr, left, right); //pivot = arr[j]
        QSort(arr, left, v-1);
        QSort(arr, v+1, right);
        return arr;
    }
    private static int partition(int[] arr, int left, int right)
    {
        int i = left-1, j;
        int pivot = arr[right];
        for(j=left;j<=right-1;j++)
        {
            if(arr[j]<=pivot)
            {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i+1, right);
        System.out.printf("pivot为%d，分割结果为：%s\n", pivot, Arrays.toString(arr));
        return i+1;
    }

    //三数中值分割
    //需要修改一种简单排序算法的参数，用于在left+3>right的时候使用
    public static int Midian3(int[] arr, int left, int right)
    {
        int mid = left + (right-left) / 2;
        if(arr[left]>arr[mid]) swap(arr, left, mid);
        if(arr[left]>arr[right]) swap(arr, left, right);
        if(arr[mid]>arr[right]) swap(arr, mid, right);
        swap(arr, mid, right-1);
        return arr[right-1];
    }

    //堆排序，算法导论p74
    //升序用大顶堆，降序用小顶堆
    //TODO:画出堆的状态
    public static int[] HeapSort(int[] arr)
    {
        System.out.println("堆排序");
        buildMaxHeap(arr);
        int len = arr.length;
        printHeap(arr, len);
        System.out.printf("当前数组状态为%s\n\n", Arrays.toString(arr));
        for(int i=len-1;i>0;i--)
        {
            swap(arr, 0, i);
            len--;
            maxHeapify(arr, 0, len);
            printHeap(arr, len);
            System.out.printf("当前数组状态为%s\n\n", Arrays.toString(arr));
        }
        return arr;
    }
    //建堆
    private static void buildMaxHeap(int[] arr)
    {
        int len = arr.length;
        for(int i=arr.length/2;i>=0;i--)
        {
            maxHeapify(arr, i, len);
        }
    }
    //保持堆的性质
    private static void maxHeapify(int[] arr, int i, int len)
    {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;
        if(left<len && arr[left]>arr[largest])
        {
            largest = left;
        }
        if(right<len && arr[right]>arr[largest])
        {
            largest = right;
        }
        if(largest!=i)
        {
            swap(arr, i, largest);
            maxHeapify(arr, largest, len);
        }
    }
    private static int[] printHelp = {0, 2, 6, 14, 30};
    private static void printHeap(int[] arr, int len)
    {
        System.out.println("当前堆的状态为：");
        for(int i=0;i<len;i++)
        {
            System.out.printf("%d ", arr[i]);
            int temp = i;
            boolean flag = IntStream.of(arr).anyMatch(x -> x == temp);
            if(flag)
            {
                System.out.print("\n");
            }
        }
        System.out.print("\n");
    }

    //非比较排序
    //计数排序
    public static int[] CountingSort(int[] arr)
    {
        System.out.println("计数排序");
        int maxVal = getMaxValue(arr);
        int[] bucket = new int[maxVal+1];
        for(int iter : arr)
        {
            bucket[iter]++;
        }
        System.out.printf("中间数组为%s\n", Arrays.toString(bucket));

        int j = 0;
        for(int i=0;i<=maxVal;i++)
        {
            while(bucket[i]>0)
            {
                arr[j] = i;
                bucket[i]--;
                j++;
            }
        }
        return arr;
    }

    //桶排序，参考程序员小灰
    public static int[] BucketSort(int[] arrInt)
    {
        System.out.println("桶排序");
        double[] arr = new double[arrInt.length];
        for(int i=0;i<arrInt.length;i++)
            arr[i] = arrInt[i];
        //1.计算max,min得到差
        double maxVal = arr[0], minVal = arr[0];
        for(double iter : arr)
        {
            if(iter>maxVal) maxVal = iter;
            if(iter<minVal) minVal = iter;
        }
        double dis = maxVal - minVal;

        //2.建桶
        int bucketNum = arr.length;
        ArrayList<LinkedList<Double>> bucketList =
                new ArrayList<LinkedList<Double>>(bucketNum);
        for(int i=0;i<bucketNum;i++)
        {
            bucketList.add(new LinkedList<Double>());
        }

        //3.遍历数组，放入桶中
        for(double iter : arr)
        {
            int pos = (int)((iter-minVal) * (bucketNum-1) / dis);
            bucketList.get(pos).add(iter);
            System.out.print("各个桶内元素为");
            System.out.println(bucketList);
        }
        //4.对桶内进行排序
        for(LinkedList<Double> iter : bucketList)
        {
            Collections.sort(iter);
        }
        //5.依次输出
        int[] arrReturn = new int[arr.length];
        int idx = 0;
        for(LinkedList<Double> iter : bucketList)
        {
            for(double ele : iter)
            {
                arrReturn[idx] = (int)ele;
                idx++;
            }
        }
        return arrReturn;
    }

    //基数排序
    public static int[] RadixSort(int[] arr)
    {
        System.out.println("基数排序");
        int radixLen = getRadixLen(getMaxValue(arr));
        int radix = 1;
        for(int i=1;i<=radixLen;i++)
        {
            countingSort(arr, radix);
            System.out.printf("当前数位为%d\n", radix);
            System.out.printf("排序结果为%s\n", Arrays.toString(arr));
            radix *= 10;
        }
        return arr;
    }
    //在各个数位之间用counting sort
    //radix为排序的基数
    private static void countingSort(int[] arr, int radix)
    {
        int[] bucket = new int[10];
        int[] temp = new int[arr.length];
        for(int iter : arr)
        {
            bucket[(iter/radix)%10]++;
        }
        for(int i=1;i<10;i++)
        {
            bucket[i] += bucket[i-1];
        }
        for(int i=arr.length-1;i>=0;i--)
        {
            temp[bucket[(arr[i]/radix)%10]-1] = arr[i];
            bucket[(arr[i]/radix)%10]--;
        }
        //arraycopy的方向为 src==>dst，参数顺序为 src, 复制开始idx, dst, 复制开始idx, 复制长度
        System.arraycopy(temp, 0, arr, 0, arr.length);
    }
    //获得max的位数
    private static int getRadixLen(int maxVal)
    {
        if(maxVal==0) return 1;
        int radixLen = 0;
        while(maxVal!=0)
        {
            maxVal /= 10;
            radixLen++;
        }
        return radixLen;
    }

    //helper
    private static void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static int getMaxValue(int[] arr)
    {
        int maxVal = 0;
        for(int iter : arr)
        {
            if(iter>maxVal) maxVal = iter;
        }
        return maxVal;
    }
}
/*
被static修饰的成员变量和成员方法独立于该类的任何对象。也就是说，它不依赖类特定的实例，被类的所有实例共享。

只要这个类被加载，Java虚拟机就能根据类名在运行时数据区的方法区内定找到他们。
因此，static对象可以在它的任何对象创建之前访问，无需引用任何对象。 

用public修饰的static成员变量和成员方法本质是全局变量和全局方法，当声明它类的对象市，
不生成static变量的副本，而是类的所有实例共享同一个static变量。 

static变量前可以有private修饰，表示这个变量可以在类的静态代码块中，或者类的其他静态成员方法中使用(当然也可以在非静态成员方法中使用)，
但是不能在其他类中通过类名来直接引用，这一点很重要。实际上你需要搞明白，private是访问权限限定，static表示不要实例化就可以使用。
static前面加上其它访问权限关键字的效果也以此类推。 
 */
