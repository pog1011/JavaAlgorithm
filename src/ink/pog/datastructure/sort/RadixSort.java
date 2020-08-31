package ink.pog.datastructure.sort;

import java.util.Arrays;

public class RadixSort {


    public static void main(String[] args) {

        int arr[] = new int[8000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        radixSort(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);


    }

    public static void radixSort(int arr[]){
        int bucket[][] = new int[10][arr.length];
        int bucketCount[] = new int[10];
        int t = 0;
        int max = arr[0];

        for (int i = 0; i < arr.length; i++) {
            if(max < arr[i]){
                max = arr[i];
            }
        }

        int numLen = (max + "").length();

        for (int i = 0,n = 1; i < numLen; i++, n *= 10) {

            for (int j = 0; j < arr.length; j++) {
                int num = arr[j] / n % 10;
                bucket[num][bucketCount[num]++] = arr[j];
            }

            for (int j = 0; j < bucket.length; j++) {

                for(int y = 0; y < bucketCount[j]; y ++){
                    arr[t++] = bucket[j][y];
                }
                bucketCount[j] = 0;
            }
            t = 0;

        }






    }



}
