package ink.pog.datastructure.search;

import java.util.ArrayList;
import java.util.Arrays;

public class BinarySearch {


    public ArrayList<Integer> binarySearch(int arr[], int left, int right, int findNum) {
        Arrays.sort(arr);
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        int midValue = arr[mid];
        if (findNum > midValue) {
            return binarySearch(arr, mid + 1, right, findNum);
        } else if (findNum < midValue) {
            return binarySearch(arr, left, mid - 1, findNum);
        } else {

            ArrayList<Integer> integers = new ArrayList<>();
            int temp = mid;
            while (temp >= 0 && arr[temp] == findNum){
                integers.add(temp--);
            }
            while (++mid < arr.length - 1 && arr[mid] == findNum){
                integers.add(mid);
            }
                return integers;

        }
    }


    public ArrayList<Integer> binarySearch(int arr[],int findNum){
        Arrays.sort(arr);
        ArrayList<Integer> integers = binarySearch(arr, 0, arr.length - 1, findNum);
        if(integers != null){
            integers.sort((value1,value2) -> {
                return value1 - value2;
            });
        }
        return integers;
    }



    public static void main(String[] args) {

        int arr[] = {1, 2, 3, 4, 5, 66, 72, 72, 89, 72, 902, 72};
        BinarySearch binarySearch = new BinarySearch();
        System.out.println(binarySearch.binarySearch(arr, 72));
    }


}
