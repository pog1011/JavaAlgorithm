package ink.pog.datastructure.search;

import java.util.Arrays;

public class FibonacciSearch {
    public static int maxSize = 20;

    public static int[] fibonacci(){
        int fibonacci[] = new int[maxSize];
        fibonacci[0] = 1;
        fibonacci[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
        }
        return fibonacci;
    }

    public static int search(int arr[] , int findNum){
        int[] fibonacci = fibonacci();
        int k = 0;
        int left = 0;
        int right = arr.length - 1;

        while (right > fibonacci[k]){
            k++;
        }

        int[] temp = Arrays.copyOf(arr, fibonacci[k]);

        for (int i = right; i < arr.length; i++) {
            temp[i] = arr[right];
        }

        while (left <= right){
            int mid = left + fibonacci[k - 1] - 1;
            if(findNum > temp[mid]){
                left = mid + 1;
                k -= 2;
            }else if (findNum < temp[mid]){
                right = mid - 1;
                k --;
            }else{
                if(mid < arr.length - 1){
                    return mid;
                }else{
                    return arr.length - 1;
                }

            }


        }
        return -1;

    }


    public static void main(String[] args) {

        int arr[] = {1,2,5,6,7,12,55,66,77,142,142,656};
        System.out.println(search(arr, 77));



    }

}
