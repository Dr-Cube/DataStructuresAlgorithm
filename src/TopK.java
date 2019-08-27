import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TopK {
    public static void main(String[] arg){
        int k = 3;
        int[] arr = {4, 23, 7, 34, 129, 18, 0, 116, 11, 32, 25};
//        List<Integer> SortOut = sortway(arr, k);
        List<Integer> BubbleOut = bubble(arr, k);
//        List<Integer> HeapOut = heapway(arr, k);
        System.out.println(BubbleOut);
    }

    //对arr进行排序，直接得到topk结果
    private static List<Integer> sortway(int[] arr, int k){
        ArrayList<Integer> rlt = new ArrayList<>();
        Arrays.sort(arr);
        for(int i=arr.length-1;i>=arr.length-k;i--){
            rlt.add(arr[i]);
        }
        return rlt;
    }

    //前k趟冒泡排序
    private static List<Integer> bubble(int[] arr, int k){
        ArrayList<Integer> rlt = new ArrayList<>();
        int i, j, ki=0;
        for(i=0;i<arr.length-1;i++){
            if(ki>=k) break;;
            for(j=i;j<arr.length-i-1;j++){
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            rlt.add(arr[arr.length-1-ki]);
            ki++;
        }
        return rlt;
    }

    //利用堆
    private static List<Integer> heapway(int[] arr, int k){
        ArrayList<Integer> rlt = new ArrayList<>();

        return rlt;
    }
}
