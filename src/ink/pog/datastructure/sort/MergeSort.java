package ink.pog.datastructure.sort;

import java.util.Arrays;

public class MergeSort {

    public static void merge(int arr[],int left, int mid, int right, int temp []){
        int l = left;   //定义左指针
        int r = mid + 1;    //定义右指针, 因为要分成两半进行分解
        int t = 0;  //定义临时数组的下标指针

        //比较分解后的数进行比较，比较后放进临时数组
        while (l <= mid && r <= right){
            if(arr[l] < arr[r]){
                temp[t++] = arr[l++];
            }else{
                temp[t++] = arr[r++];
            }
        }
        //如果一边有剩余的就都放进临时数组里面
        while (l <= mid){
            temp[t++] = arr[l++];
        }
        while (r <= right){
            temp[t++] = arr[r++];
        }

        t = 0;  //归并到原先的数组
        while (left <= right){
            arr[left++] = temp[t++];
        }

    }

    public static void sort(int arr[],int left,int right, int temp[]){
        if(left < right){
            int mid = (left + right) / 2;
            sort(arr,left,mid,temp);
            sort(arr,mid + 1, right, temp);
            merge(arr,left,mid,right,temp);

        }


    }

    public static void main(String[] args) {

        int arr[] = new int[80000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        int temp[] = new int[arr.length];
        long start = System.currentTimeMillis();
        Arrays.sort(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }


}
