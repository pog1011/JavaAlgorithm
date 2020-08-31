package ink.pog.algorithm.leetcode;

import java.time.temporal.ValueRange;
import java.util.*;
import java.util.stream.Stream;

public class Array {


    public static int numOfSubarrays(int[] arr, int k, int threshold) {
        int res = 0;
        int index = 0;
        int count = 0;
        int sum = 0;
        while (index < arr.length){
            if(count == k){
                if(sum / k >= threshold) res ++;
                sum = 0;
                count = 0;
                index -= (k - 1);
            }
            sum += arr[index ++];
            count ++;
        }
        if(sum / k >= threshold) res ++;
        return res;
    }

    public static int[] spiralOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0) return new int[]{};
        int l = 0, t = 0, r = matrix[0].length - 1, b = matrix.length - 1, x = 0;
        int res[] = new int[(r + 1) * (b + 1)];
        while (true){
            if(++l > r) break;
            for(int i = l; i <= r; i ++) res[x++] = matrix[l][i];
            if(++t > b) break;
            for(int i = t; i <= b; i ++) res[x++] = matrix[r][t];
            if(--r < l ) break;
            for(int i = r; i >= l; i ++) res[x++] = matrix[b][i];
            if(--b < t) break;
            for(int i = b; i >= t; i++ ) res[x++] = matrix[l][i];
        }
        return res;

    }

    public static int longestConsecutive(int[] nums) {
        Set<Integer> integers = new HashSet<>();
        for (int num : nums) {
            integers.add(num);
        }
        int longSize = 1;
        for (Integer num : integers) {

            if(!integers.contains(num - 1)){
                int curSize = 1;
                int curNum = num;

                while (integers.contains(curNum + 1)){
                    curNum = curNum + 1;
                    curSize ++;
                }
                longSize = Math.max(longSize,curSize);
            }

        }
        return longSize;
    }
    public static boolean judgePoint24(int[] nums) {
        List<Float> floatNums = new ArrayList<>();
        for (int num : nums) {
            floatNums.add((float) num);
        }
        return judgePoint24Solve(floatNums);
    }

    public static boolean judgePoint24Solve(List<Float> nums){

        int len = nums.size();

        if(len == 1) {
            return Math.abs(nums.get(0) - 24) <= 0.00001;
        }

        for(int i = 0 ; i < len; i ++) {
            for (int j = i + 1; j < len; j ++) {
                List<Float> temp = new ArrayList<>(nums);

                float b = temp.remove(j);
                float a = temp.remove(i);
                Boolean valid = Boolean.FALSE;
                temp.add(a + b);
                valid |= judgePoint24Solve(temp);

                temp.set(temp.size() - 1, a - b);
                valid |= judgePoint24Solve(temp);

                temp.set(temp.size() - 1, a * b);
                valid |= judgePoint24Solve(temp);

                temp.set(temp.size() - 1, a / b);
                valid |= judgePoint24Solve(temp);

                temp.set(temp.size() - 1, b - a);
                valid |= judgePoint24Solve(temp);

                temp.set(temp.size() - 1, b / a);
                valid |= judgePoint24Solve(temp);
                if (valid) return true;
            }
        }
        return Boolean.FALSE;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,1,8,7};
        System.out.println(judgePoint24(nums));
    }
}
