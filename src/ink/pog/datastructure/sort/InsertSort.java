package ink.pog.datastructure.sort;

import java.util.Arrays;

public class InsertSort {

    public static void main(String[] args) {

//        int arr[] = new int[80000];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = (int) (Math.random() * 80000);
//        }
        int arr[] = {1, 5, 3, 7, 2, 4, -2};
        int j = 0;
        int temp = 0;
        long start = System.currentTimeMillis();
        for (int i = 1; i < arr.length; i++) {
            temp = arr[i];
            j = i;
            while (j - 1>= 0 && temp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(Arrays.toString(arr));
    }
}
