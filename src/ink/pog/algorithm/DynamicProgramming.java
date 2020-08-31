package ink.pog.algorithm;

import javax.swing.text.html.HTML;
import java.util.Arrays;

public class DynamicProgramming {

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        boolean[][] dp = new boolean[len][len];

        // 初始化
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        int maxLen = 1;
        int start = 0;

        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {

                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                } else {
                    dp[i][j] = false;
                }

                // 只要 dp[i][j] == true 成立，就表示子串 s[i, j] 是回文，此时记录回文长度和起始位置
                if (dp[i][j]) {
                    int curLen = j - i + 1;
                    if (curLen > maxLen) {
                        maxLen = curLen;
                        start = i;
                    }
                }
            }
        }
        return s.substring(start, start + maxLen);
    }

    //递归求不相邻的最大和
    public int recMax(int arr[],int index){
       if(index == 0){
           return arr[0];
       }else if(index == 0 || index == 1){
           return Math.max(arr[0],arr[1]);
       }else{
           return Math.max(recMax(arr,index - 1),recMax(arr,index - 2) + arr[index]);
       }
    }
    //dp 求不相邻的最大和
    public int dpMax(int arr[]){
        int dp[] = new int[arr.length];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0],arr[1]);
        for(int i = 2; i < arr.length; i ++){
            dp[i] = Math.max(dp[i - 1],dp[i - 2] + arr[i]);
        }
        return dp[arr.length - 1];
    }

    //递归求数组里的有无元素想加等于目标
    public boolean recTarget(int arr[],int index, int target){
        if(index == 0){
            return arr[0] == target;
        }
        if(arr[index] > target){
            return recTarget(arr,index - 1, target);
        }else{
            return recTarget(arr,index - 1,target - arr[index]) || recTarget(arr, index - 1, target);
        }
    }

    //dp 实现数组里的有无元素想加等于目标
    public boolean dpTarget(int arr[], int target){
//        Arrays.sort(arr);
        boolean dp[][] = new boolean[arr.length][target + 1];
        for(int i = 0; i < dp.length; i ++){
            dp[i][0] = true;
        }
        if(arr[0] == target){
            dp[0][arr[0]] = true;
        }
        for(int i = 1; i < arr.length; i++){
            for(int j = 1; j < dp[0].length; j ++){
                if(arr[i] > j){
                    dp[i][j] = dp[i -1][j];
                }else{
                    dp[i][j] =  dp[i - 1][j - arr[i]] || dp[i -1][j];
                }
            }
        }
        return dp[dp.length - 1][target];
    }

    //规则: cargo 中的二维数组规定 cargo[i][0] 为货物的重量, cargo[i][1] 为货物的价值, capacity 为背包容量
    public int dpKnapsack(int cargo[][],int capacity){
        int len = cargo.length;
        int dp[][] = new int[len + 1][capacity + 1];


        for(int i = 1; i < dp.length; i ++){
            for(int j = 1; j < dp[0].length; j ++){
                if(cargo[i - 1][0] > j){
                    dp[i][j] = dp[i - 1][j];
                }else{
                    dp[i][j] = Math.max(dp[i - 1][j],dp[i - 1][j - cargo[i - 1][0]] + cargo[i - 1][1]);
                }
            }
        }
        return dp[len][capacity];
    }

    //递归求最长公共子序列
    public int recLongCommonsSubsequence(String s1,String s2){

        if(s1.length() == 0 || s2.length() == 0){
            return 0;
        }
        if(s1.charAt(0) == s2.charAt(0)){
            return recLongCommonsSubsequence(s1.substring(1),s2.substring(1)) + 1;
        }else{
            int countA = recLongCommonsSubsequence(s1,s2.substring(1));
            int countB = recLongCommonsSubsequence(s1.substring(1),s2);
            return Math.max(countA,countB);
        }
    }
    //dp 求最长公共子序列
    public int dpLongCommonsSubsequence(String s1,String s2){
        int n = s1.length();
        int m = s2.length();
        int dp[][] = new int[n + 1][m + 1];
        for(int i = 1; i < n + 1; i ++){
            for(int j = 1; j < m + 1; j ++){
                if(s1.charAt( i - 1) == s2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i -1][j],dp[i][j - 1]);
                }
            }
        }
        return dp[n][m];
    }

    public int maxValue(int grid[][] ,int i,int j){

        if(i == grid.length - 1 && j == grid[0].length - 1){
            return grid[i - 1][j - 1] + grid[0][0];
        }else if(i == grid.length - 1 && j < grid[0].length){
            return Math.max(grid[i][j + 1],maxValue(grid, i, j + 1) + grid[i][j]);
        }else if(i < grid.length - 1 && j == grid[0].length - 1){
            return Math.max(grid[i + 1][j],maxValue(grid, i + 1, j) + grid[i][j]);
        } else{
            return Math.max(maxValue(grid,i + 1, j),maxValue(grid, i, j +1));
        }
    }

    public int dpMaxValue(int grid[][]){
        int n = grid.length;
        int m = grid[0].length;
        int dp[][] = new int[n][m];
        dp[0][0] = grid[0][0];
        for(int i = 1; i < n; i ++){
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }
        for(int i = 1 ; i < m; i++){
            dp[0][i] = grid[0][i] + dp[0][i - 1];
        }

        for(int i = 1; i < n; i ++){
            for(int j = 1; j < m; j ++){

                int route1 = dp[i - 1][j] + grid[i][j];
                int route2 = dp[i][j - 1] + grid[i][j];
                dp[i][j] = Math.max(route1,route2);

            }
        }
        return dp[n - 1][m - 1];
    }
    public static void main(String[] args) {
        DynamicProgramming dp = new DynamicProgramming();
        int gird[][] = {
                {1,3,1},
                {1,5,1},
                {4,2,1}
        };
        System.out.println(dp.dpMaxValue(gird));
    }

}
