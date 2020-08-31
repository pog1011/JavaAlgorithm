package ink.pog.datastructure;


import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class sumTest {


    public static int lengthOfLongestSubstring(String s) {
        if (s.equals("")) {
            return 0;
        }
        HashMap<String, Integer> map = new HashMap<>();
        int count = 0;
        String[] temp = s.split("");
        int max = 0;
        for (int i = 0; i < temp.length; i++) {
            if (map.get(temp[i]) == null) {
                map.put(temp[i], i);
                count++;
            } else {
                max = count > max ? count : max;
                count = 0;
                i = map.get(temp[i]);
                map.clear();
            }
        }

        return max = count > max ? count : max;
    }

    //    public static int longestValidParentheses(String s) {
//        int count = 0;
//        int max = 0;
//        HashSet<Character> set = new HashSet<>();
//        for (int i = 0; i < s.length(); i++) {
//            if (i + 1 < s.length() && s.charAt(i) == '(' && s.charAt(i + 1) == ')') {
//                i++;
//                count += 2;
//            } else {
//                if (i + 1 < s.length() && s.charAt(i) == '(' && s.charAt(i + 1) == '(') {
//                    if (set.contains(s.charAt(i))) {
//                        set.clear();
//                        count = 0;
//                    } else {
//                        set.add(s.charAt(i));
//                    }
//                }
//                if (i - 1 >= 0 && s.charAt(i - 1) == ')' && s.charAt(i) == ')') {
//                    if (set.contains('(')) {
//                        count += 2;
//                        set.clear();
//                    }
//                }
//                max = Math.max(max, count);
//
//            }
//        }
//        return Math.max(max, count);
//    }
    public static int longestValidParentheses(String s) {
        assert s != null;
        if (s.length() < 2) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int max = 0;

        stack.add(-1);

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    max = max > (i - stack.peek()) ? max : (i - stack.peek());
                }

            }

        }
        return max;

    }

    public static int ballCount(int n, int m) {
        if (m == n || m == 0) {
            return 1;
        }
        return ballCount(n - 1, m - 1) + ballCount(n - 1, m);
    }

    public static void numDivide(int a, int arr[], int index) {
        if (a == 0) {
            for (int i = 0; i < index; i++) {
                System.out.print(arr[i] + "  ");
            }
            System.out.println();
        }
        for (int i = a; i > 0; i--) {
            if (index > 0 && arr[index - 1] < i) continue;
            arr[index] = i;
            numDivide(a - i, arr, index + 1);
        }


    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int n = nums1.length;
        int m = nums2.length;
        int mid = (n + m) / 2;
        if (n == 0) {
            return m % 2 == 0 ? (double) (nums2[mid - 1] + nums2[mid]) / 2 : nums2[mid];
        }
        if (m == 0) {
            return n % 2 == 0 ? (double) (nums1[mid - 1] + nums1[mid]) / 2 : nums1[mid];
        }

        int j = 0;
        int i = 0;
        int current = 0;
        int last = 0;
        int count = 0;
        while (count <= mid) {
            count++;
            last = current;
            if (i == n) {
                current = nums2[j++];
                continue;
            }
            if (j == m) {
                current = nums1[i++];
                continue;
            }
            if (nums1[i] < nums2[j]) {
                current = nums1[i++];
            } else {
                current = nums2[j++];
            }
        }
        if ((n + m) % 2 == 0) {
            return (double) (current + last) / 2;
        } else {
            return current;
        }
    }

    public static ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode temp = new ListNode(head.val);
        ListNode cur = head.next;
        ListNode p = temp;
        ListNode z = null;
        while (cur != null) {
            while (p.next != null && p.next.val < cur.val) {
                p = p.next;
            }
            z = cur.next;
            ListNode x = p.next;
            p.next = cur;
            p.next.next = x;
            p = temp;
            cur = z;
        }
        return temp;
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode temp = head.next;
        ListNode reverse = head;
        int gross = k;
        int count = 1;
        while (count <= gross && temp != null) {
            if (count % k == 0 && temp != null) {
                ListNode p = temp;
                for (int i = 0; i < k; i++) {
                    reverse = temp.next;
                    reverse.next = p;
                    temp = temp.next;
                }
                count = 1;
                continue;
            }
            count++;
        }
        return head;
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0 || strs[0].equals("")) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        String res = strs[0];

        for (int i = 1; i < strs.length; i++) {
            String temp = strs[i];
            if (temp.length() == 0 || "".equals(temp)) {
                return "";
            }
            int start = 0;
            while (start < temp.length() && start < res.length() && res.charAt(start) == temp.charAt(start)) {
                start++;
            }
            res = res.substring(0, start);
        }
        return res;
    }

    static int count = 0;

    public static boolean carrier(int[] weights, int D, int K) {
        int remainK = K;
        for (int weight : weights) {
            if (weight > K) return false;
            if (remainK < weight) {
                D--;
                remainK = K;
            }
            remainK -= weight;
        }
        return D > 0;
    }

    public static int shipWithinDays(int[] weights, int D) {
        int low = 0;
        int high = 50000 * 500;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (carrier(weights, D, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static boolean canThreePartsEqualSum(int[] A) {
        int average = 0;
        for (int i : A) {
            average += i;
        }
        if (average % 3 != 0) {
            return false;
        }
        average /= 3;
        int count = 0;
        int sum = 0;
        for (int i : A) {
            sum += i;
            if (sum == average) {
                count++;
                sum = 0;
            }
            if (average == 0 && count == 3 && sum == average) {
                return true;
            }
        }
        return count == 3;
    }

    public static int maxScoreSightseeingPair(int[] A) {
        if (A.length == 0 || A.length == 1) return 0;
        int cur = A[0];
        int max = -1;
        for (int i = 1; i < A.length; i++) {
            max = Math.max(max, cur - 1 + A[i]);
            cur = Math.max(cur - 1, A[i]);
        }
        return max;
    }

    public static boolean checkSubarraySum(int[] nums, int k) {
        int sum;

        for (int i = 0; i < nums.length - 1; i++) {
            sum = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if ((sum == 0 && k == 0) || (k != 0 && sum % k == 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        //dp[j][i] j为字符串的开头 i为字符串的末尾
        boolean dp[][] = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            //
            dp[i][i] = true;
        }

        char[] chars = s.toCharArray();
        int maxLen = 1;
        int begin = 0;
        for (int r = 1; r < len; r++) {
            for (int l = 0; l < r; l++) {

                if (chars[l] == chars[r]) {
                    //如果只有1个或者两个字符且相等的时候那么就是回文字符串
                    if (r - l < 3) {
                        dp[l][r] = true;
                    } else {
                        //否则就从前面判断是不是相等
                        dp[l][r] = dp[l + 1][r - 1];
                    }
                } else {
                    dp[l][r] = false;
                }

                if (dp[l][r]) {
                    int curLen = r - l + 1;
                    if (maxLen < curLen) {
                        maxLen = curLen;
                        begin = l;
                    }
                }

            }
        }

        return s.substring(begin, begin + maxLen);
    }

    public static String decodeString1(String s) {
        Integer multiply = 0;
        StringBuilder res = new StringBuilder();
        LinkedList<Integer> temp_num = new LinkedList<>();
        LinkedList<String> temp_res = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (c == '[') {
                temp_num.addLast(multiply);
                temp_res.addLast(res.toString());
                res = new StringBuilder();
                multiply = 0;
            } else if (c == ']') {
                StringBuilder temp = new StringBuilder();
                Integer mult = temp_num.removeLast();
                for (Integer i = 0; i < mult; i++) {
                    temp.append(res);
                }
                res = new StringBuilder(temp_res.removeLast() + temp);
            } else if (c >= '0' && c <= '9') {
                multiply = multiply * 10 + Integer.parseInt(c + "");
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

    public static String decodeString(String s) {
        return dfs(s, 0)[0];
    }

    private static String[] dfs(String s, int i) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        while (i < s.length()) {

            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                multi = multi * 10 + Integer.parseInt(s.charAt(i) + "");
            } else if (s.charAt(i) == '[') {
                String[] dfsRes = dfs(s, i + 1);
                i = Integer.parseInt(dfsRes[0]);
                while (multi > 0) {
                    res.append(dfsRes[1]);
                    multi--;
                }
            } else if (s.charAt(i) == ']') {
                return new String[]{String.valueOf(i), res.toString()};
            } else {
                res.append(s.charAt(i));
            }
            i++;
        }
        return new String[]{res.toString()};
    }

    public static int rob(int[] nums) {

        if(nums == null || nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return nums[0];
        }
        int pre = nums[0];
        int max = Math.max(nums[0],nums[1]);
        for(int i = 2; i < nums.length; i ++){
            max = Math.max(nums[i] + pre,max);
            pre = nums[i - 2] + pre;
        }
        return max;
    }
    public static int countPrimes(int n) {
        int count = 0;
        boolean[] isPrime = new boolean[n];

        for (int i = 2; i * i <= n; i++) {
            if (!isPrime[i]) {
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = true;
                    count ++;
                }
            }
        }

        return n - 2 - count;
    }
    public static int[] shuffle(int[] nums, int n) {
        int temp = nums[0];
        for(int i = 1, j = n; i <= nums.length && j < nums.length; i ++, j ++){
            nums[i - 1] = temp;
            temp = nums[i];
            nums[i] = nums[j];
        }
//        Arrays.sort();
        return nums;
    }


    public static void test(int m, int arr[], int count){
        if(count > 4) {
            for(int i = 0; i < arr.length; i ++){
                if(arr[i] == 0) return;
                if(i == arr.length - 1 && arr[i] != 0) System.out.println(m);
            }
            return;
        };

        int n = (int)Math.pow(m,count);
        while (n != 0){
            int mod = n % 10;
            if(arr[mod] != 0) return;
            arr[mod] = 1;
            n /= 10;
        }
        test(m, arr, count += 1);
    }

    public static void main(String[] args) {
       String msg=  "ASSIGN:bubble;MSG:pog:122";
//       msg = msg.substring(msg.indexOf(":") + 1);
//        System.out.println(msg);
        msg = msg.substring(msg.indexOf(":") + 1,msg.indexOf(";"));
        System.out.println(msg);

//        ListNode node1 = new ListNode(1);
//        node1.next = new ListNode(5);
//        node1.next.next = new ListNode(7);
//        node1.next.next.next = new ListNode(2);
//        reverseKGroup(node1,2);
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}