public class YangHui {
    private static int[][] getYH(int n){
        int[][] arr = new int[n][n];
        for(int i=0;i<n;i++){
            arr[i][0] = arr[i][i] = 1;
        }
        for(int i=1;i<n;i++){
            for(int j=1;j<n;j++){
                arr[i][j] = arr[i-1][j-1] + arr[i-1][j];
            }
        }
        return arr;
    }
    public static void printYH(int[][] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr.length-1-i;j++){
                System.out.print("  ");
            }
            for(int j=0;j<=i;j++){
                System.out.print("  ");
                System.out.printf("%-2d", arr[i][j]);
            }
            System.out.println();
        }
    }
    public static void main(String[] args){
        int arr[][] = getYH(6);
        printYH(arr);
    }
}
