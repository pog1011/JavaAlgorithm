package ink.pog.datastructure.sort;

import java.util.Arrays;

public class SelectSort {


    public static void main(String[] args) {
//        int arr[] = new int[80000];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = (int) (Math.random() * 80000);
//        }
        int arr[] = {1,5,3,7,2,4,-2};
        int minIndex = 0;
        int temp = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if(arr[minIndex] > arr[j]){
                    minIndex = j;
                }
            }
            temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(Arrays.toString(arr));


    }


}
