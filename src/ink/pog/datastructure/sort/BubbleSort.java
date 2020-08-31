package ink.pog.datastructure.sort;

import java.util.Arrays;

public class BubbleSort {


    public static void main(String[] args) {
        int temp = 0;
        int arr[] = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 80000);
        }
        boolean flag = true;
        long start = System.currentTimeMillis();
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if(arr[j] > arr[j + 1]){
                    flag = false;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if(flag){
                break;
            }else{
                flag = true;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println( end - start);
        System.out.println(Arrays.toString(arr));


    }

}
