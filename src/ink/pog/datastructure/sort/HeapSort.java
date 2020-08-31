package ink.pog.datastructure.sort;

import java.util.Arrays;

public class HeapSort {

    public static void adjust(int arr[], int i, int len) {
        int temp = arr[i];

        for (int k = i * 2 + 1; k < len; k = k * 2 + 1) {
            if (k + 1 < len) {
                k = arr[k] < arr[k + 1] ? ++k : k;
            }
            if (temp < arr[k]) {
                arr[i] = arr[k];
                i = k;
            }else{
                break;
            }
        }
        arr[i] = temp;
    }

    public static void heapSort(int arr[]) {

        for (int i = (arr.length- 1) / 2; i >= 0; i--) {
                adjust(arr, i, arr.length);
        }

        for (int j = arr.length - 1; j > 0; j--) {
            int temp = arr[0];
            arr[0] = arr[j];
            arr[j] = temp;
            adjust(arr, 0, j);
        }
    }

    public static void main(String[] args) {
        int arr[] = {0,5,1,202,42,51,-99,43,-992,432};
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = (int) (Math.random() * 80000);
//        }
        long start = System.currentTimeMillis();
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
