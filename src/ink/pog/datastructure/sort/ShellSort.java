package ink.pog.datastructure.sort;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ShellSort {

    public static void change(int arr[]) {
        int len = arr.length;
        int temp = 0;
        int count = 0;
        for (int gap = len / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < len; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }

        }
    }

    public static void move(int arr[]){

        int len = arr.length;
        int temp = 0;
        int j = 0;
        //每次相隔 len / 2 的间距进行比较
        //ex: 1, 5, 3, 7, 2, 4, -2 , len / 2 = 3,第一次每隔 3 个就进行比较,接着 3 / 2 = 1,第二次每隔一个进行比较
        for (int gap = len / 2; gap > 0; gap /= 2) {
            //另 i 等于 gap, 这样比较好运算, 即每次的间隔下标为 arr[i - gap]
            for (int i = gap; i < len; i++) {
                j = i;
                //保存要进行比较的 arr[i]
                temp = arr[i];
                if(arr[i] < arr[i - gap]){
                    while (j - gap >= 0 && temp < arr[j - gap]){
                        arr[j] = arr[j - gap];
                        //下一次间隔的值得下标
                        j -= gap;
                    }
                    //比较完成之后，把要进行比较的 arr[i] 赋值给他最后一次比较的下标的值
                    arr[j] = temp;
                }
            }
        }


        System.out.println(Arrays.toString(arr));

    }

    public static void main(String[] args) {
//        int arr[] = new int[80000000];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = (int) (Math.random() * 80000);
//        }
        int arr[] = {1, 5, 3, 7, 2, 4, -2};
        long start = System.currentTimeMillis();
        move(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);


    }


}
