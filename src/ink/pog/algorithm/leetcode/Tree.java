package ink.pog.algorithm.leetcode;

import com.sun.jmx.remote.internal.ArrayQueue;

import javax.sound.midi.Soundbank;
import java.util.*;

public class Tree {

    private static int res;


    //102. 二叉树的层序遍历  BFS解法
    public static List<List<Integer>> levelOrderBFS(TreeNode root) {
        if (root == null) return new ArrayList<List<Integer>>();
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() > 0) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();

            for (int i = 0; i < size; ++i) {
                TreeNode temp_root = queue.remove();
                if (temp_root.left != null) {
                    queue.add(temp_root.left);
                }
                if (temp_root.right != null) {
                    queue.add(temp_root.right);
                }
                temp.add(temp_root.val);
            }
            res.add(temp);
        }
        return res;
    }

    //102. 二叉树的层序遍历 DFS
    public static List<List<Integer>> levelOrderDFS(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        levelOrderDfs(1, res, root);
        return res;
    }

    public static void levelOrderDfs(int index, List<List<Integer>> res, TreeNode root) {
        if (res.size() < index) {
            res.add(new ArrayList<Integer>());
        }
        res.get(index - 1).add(root.val);
        if (root.left != null) {
            levelOrderDfs(index + 1, res, root.left);
        }
        if (root.right != null) {
            levelOrderDfs(index + 1, res, root.right);
        }
    }

    public static List<List<Integer>> levelOrderBottomDFS(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        levelOrderBottomDfs(1, root, res);
        return res;
    }

    public static void levelOrderBottomDfs(int index, TreeNode root, List<List<Integer>> res) {
        if (res.size() < index) {
            res.add(0, new ArrayList<>());
        }
        res.get(res.size() - index).add(root.val);
        if (root.left != null) {
            levelOrderBottomDfs(index + 1, root.left, res);
        }
        if (root.right != null) {
            levelOrderBottomDfs(index + 1, root.right, res);
        }
    }

    public static StringBuilder postorder(TreeNode root, StringBuilder sb) {
        if (root == null) return sb;
        postorder(root.left, sb);
        postorder(root.right, sb);
        sb.append(root.val).append(",");
        return sb;
    }

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        StringBuilder sb = postorder(root, new StringBuilder());
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static TreeNode helper(Integer lower, Integer upper, ArrayDeque<Integer> nums) {
        if (nums.isEmpty()) return null;
        int val = nums.getLast();
        if (val < lower || val > upper) return null;

        nums.removeLast();
        TreeNode root = new TreeNode(val);
        root.right = helper(val, upper, nums);
        root.left = helper(lower, val, nums);
        return root;
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;
        ArrayDeque<Integer> nums = new ArrayDeque<Integer>();
        for (String s : data.split(","))
            nums.add(Integer.valueOf(s));
        return helper(Integer.MIN_VALUE, Integer.MAX_VALUE, nums);
    }

    public static int minDepth(TreeNode root) {
        if (root == null) return 0;
        int depth = 1;
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                if (temp.left == null && temp.right == null) return depth;
                if (temp.left != null) queue.offer(temp.left);
                if (temp.right != null) queue.offer(temp.right);
            }
            depth++;
        }
        return depth;
    }

    public static ListNode[] listOfDepth(TreeNode tree) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(tree);
        ArrayList<ListNode> listNodes = new ArrayList<>();
        ListNode listNodess[] = new ListNode[1];
        while (!queue.isEmpty()) {
            int size = queue.size();
            ListNode listNode = new ListNode(0);
            ListNode nodeTemp = listNode;
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                if (temp.left != null) queue.offer(temp.left);
                if (temp.right != null) queue.offer(temp.right);
                nodeTemp.next = new ListNode(temp.val);
                nodeTemp = nodeTemp.next;
            }
            listNodes.add(listNode.next);
        }
        ListNode[] listNodes1 = listNodes.toArray(listNodess);

        return listNodes1;
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> lists = new ArrayList<>();
        queue.add(root);
        int depth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> integers = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                if (depth % 2 == 0) {
                    integers.add(0, temp.val);
                } else {
                    integers.add(temp.val);
                }
                if (temp.left != null) queue.offer(temp.left);
                if (temp.right != null) queue.offer(temp.right);
            }
            depth++;
            lists.add(integers);
        }
        return lists;
    }

    public static int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int left = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                if (temp.left != null) {
                    left = temp.left.val;
                    queue.offer(temp.left);
                }
                if (temp.right != null) queue.offer(temp.right);
            }
            if (queue.size() == 0) {
                return left;
            }
        }
        return left;
    }

    public static int sumEvenGrandparent(TreeNode root) {
        helper(null, null, root);
        return res;
    }

    public static void helper(TreeNode grand, TreeNode father, TreeNode node) {
        if (node == null) return;
        if (grand != null && (grand.val & 1) == 0) res += node.val;
        helper(father, node, node.left);
        helper(father, node, node.right);
    }

    public static int deepestLeavesSum(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int sum = 0;
            for (int i = 0; i < queue.size(); i++) {
                TreeNode temp = queue.poll();
                if (temp.left != null) queue.offer(temp.left);
                if (temp.right != null) queue.offer(temp.right);
                sum += temp.val;
            }
            if (queue.isEmpty()) return sum;
        }
        return 0;
    }

    public static TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;
        TreeNode tmp = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(tmp);
        return root;

    }

    public static void mirrorTreehelper(TreeNode origin, TreeNode target) {
        if (origin == null) return;
        if (origin.left != null) {
            target.right = new TreeNode(origin.left.val);

        }
        if (origin.right != null) {
            target.left = new TreeNode(origin.right.val);

        }
        mirrorTreehelper(origin.right, target.left);
        mirrorTreehelper(origin.left, target.right);

    }

    public static boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int l = isBalancedHelper(root.left);
        int r = isBalancedHelper(root.right);
        return Math.abs(l - r) <= 1;
    }

    public static int isBalancedHelper(TreeNode node) {
        if (node == null) return 0;
        return Math.max(node.left == null ? 0 : isBalancedHelper(node.left), node.right == null ? 0 : isBalancedHelper(node.right)) + 1;
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTreeHelper(preorder, 0, preorder.length, inorder, 0, inorder.length, map);
    }

    public static TreeNode buildTreeHelper(int[] preOrder, int pre_left, int pre_right, int[] inOrder, int in_left, int in_right, Map<Integer, Integer> map) {
        if (pre_left == pre_right) {
            return null;
        }
        int root_val = preOrder[pre_left];
        TreeNode root = new TreeNode(root_val);
        Integer in_root_index = map.get(root_val);
        Integer leftNum = in_root_index - in_left;
        root.left = buildTreeHelper(preOrder, pre_left + 1, pre_left + 1 + leftNum, inOrder, in_left, in_root_index, map);
        root.right = buildTreeHelper(preOrder, pre_left + 1 + leftNum, pre_right, inOrder, in_root_index + 1, in_right, map);
        return root;
    }

    public static TreeNode buildPostTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildPostTreeHelper(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
    }

    public static TreeNode buildPostTreeHelper(int[] inorder, int in_left, int in_right, int[] postOrder, int po_left, int po_right, Map<Integer, Integer> map) {
        if (in_left > in_right) {
            return null;
        }
        int root_val = postOrder[po_right];
        Integer root_val_index = map.get(root_val);
        Integer leftNum = root_val_index - in_left;
        TreeNode root = new TreeNode(root_val);
        root.left = buildPostTreeHelper(inorder, in_left, root_val_index - 1, postOrder, po_left, po_left + leftNum - 1, map);
        root.right = buildPostTreeHelper(inorder, root_val_index + 1, in_right, postOrder, po_left + leftNum, po_right - 1, map);
        return root;
    }

    // 100
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    //450
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val == key) {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            if (root.right != null && root.left != null) {
                Integer min = deleteNodeHelper(root.right);
                root.val = min;
                root.right = deleteNode(root.right, min);
            }
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    // 450 get min
    public Integer deleteNodeHelper(TreeNode root) {
        while (root != null) root = root.left;
        return root.val;
    }

    // 701
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (root.val > val) root.left = insertIntoBST(root.left, val);
        if (root.val < val) root.right = insertIntoBST(root.right, val);
        return root;
    }

    // 144
    public static List<Integer> preOrderTraversalByMorris(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        TreeNode cur = root;
        List<Integer> res = new ArrayList<>();
        TreeNode pre;
        while (cur != null) {
            pre = cur.left;
            if (pre != null) {
                while (pre.right != null && pre.right != cur) pre = pre.right;
                if (pre.right == null) {
                    pre.right = cur;
                    res.add(cur.val);
                    cur = cur.left;
                    continue;
                } else {
                    pre.right = null;
                }
            } else {
                res.add(cur.val);
            }
            cur = cur.right;
        }
        return res;
    }


    public static int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i ++) {
            for (int j = 0; j <= i; j ++) {
                dp[i] = dp[i - 1] + dp[i - j];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {

//
        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(9);
        root.right = new TreeNode(2);
//        root.left.left = new TreeNode(10);
        root.right.left = new TreeNode(3);
//        root.right.right = new TreeNode(3);
//        root.right.right.left = new TreeNode(10);
//        root.left.right = new TreeNode(20);
//        root.left.left.right = new TreeNode(20);
//        System.out.println(isBalanced(root));[9,3,15,20,7]
//[9,15,7,20,3]
        List<Integer> integers = preOrderTraversalByMorris(root);
        System.out.println(integers.toString());
//        TreeNode treeNode = buildPostTree(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3});
//        System.out.println(treeNode.toString());

    }

}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}