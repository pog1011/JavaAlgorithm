package ink.pog.datastructure.sort;

import java.util.Arrays;

public class QuickSort {

    public static void quickSort(int arr[], int left, int right) {
        // 如果指针 left 超过 right 指针,说明已经排列完毕
        if (left > right) {
            return;
        }
        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2];
        int temp = 0;
        while (l < r) {
            while (arr[l] < pivot && l < r) {
                l++;
            }
            while (arr[r] > pivot && l < r) {
                r--;
            }
            if (l < r) {
                temp = arr[l];
                arr[l] = arr[r];
                arr[r] = temp;
                if (arr[l] == pivot) {
                    r--;
                } else if (arr[r] == pivot) {
                    l++;
                } else {
                    l++;
                    r--;
                }
            }
        }
        quickSort(arr, left, r - 1);
        quickSort(arr, l + 1, right);
    }

    public static void main(String[] args) {
        int arr[] = new int[80000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        quickSort(arr, 0, arr.length - 1);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }


}
