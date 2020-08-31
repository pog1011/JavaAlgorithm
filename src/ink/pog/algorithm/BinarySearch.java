package ink.pog.algorithm;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class BinarySearch {


    public static ArrayList<Integer> binarySearch(int arr[], int findNum){
        int mid = arr.length / 2;
        Arrays.sort(arr);
        ArrayList<Integer> index = new ArrayList<>();
            while (mid > 0 && arr[mid] > findNum){
                mid = mid / 2;
            }
            while (arr[mid] < findNum){
                mid = (arr.length - mid) / 2 + mid;
            }
            if(arr[mid] == findNum){
                int temp = mid;
                while (temp >= 0 && arr[temp] == findNum){
                    index.add(temp--);
                }
                while (++mid < arr.length - 1 && arr[mid] == findNum){
                    index.add(mid);
                }
            }
        return index;
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5, 66, 72, 51,72, 89, 72,66, 902, 72};
        System.out.println(binarySearch(arr, 902).toString());
    }


}
