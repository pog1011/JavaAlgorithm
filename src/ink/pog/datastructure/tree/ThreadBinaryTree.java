package ink.pog.datastructure.tree;

import java.math.BigInteger;

public class ThreadBinaryTree {

    private TreeNode root;
    private TreeNode pre;
    public ThreadBinaryTree(TreeNode root){
        this.root = root;
    }

    public void threadTree(TreeNode node){
        if(node == null){
            return;
        }

        threadTree(node.getLeftNode());
        if(node.getLeftNode() == null){
            node.setLeftNode(pre);
            node.setLeftType(1);
        }
        if(pre != null && pre.getRightNode() == null){
            pre.setRightNode(node);
            pre.setRightType(1);
        }
        pre = node;
        threadTree(node.getRightNode());
    }

    public void middleOrderThreeList(){
        TreeNode node = root;

        while (node != null){

            while (node.getLeftType() == 0){
                node = node.getLeftNode();
            }
            System.out.println(node);
            while (node.getRightType() == 1){
                node = node.getRightNode();
                System.out.println(node);
            }
            node = node.getRightNode();
        }
    }

    public void preOrderthreadList(){
        TreeNode node = root;

        while (node != null){
            while (node.getLeftType() == 0){
                System.out.println(node);
                node = node.getLeftNode();
            }
            System.out.println(node);
            while (node.getRightType() == 1){
                node = node.getRightNode();
            }
            node = node.getRightNode();
        }
    }

    public void postOrderThreeList(){
        TreeNode node = root;

        while (node != null){

            while (node.getRightType() == 1){
                node = node.getRightNode();
            }

            while (node.getLeftType() == 0 ){
                node = node.getLeftNode();
            }
            node = node.getRightNode();
            System.out.println(node);


        }


    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, "pog");
        TreeNode node2 = new TreeNode(2, "pig");
        TreeNode node3 = new TreeNode(3, "panda");
        TreeNode node5 = new TreeNode(5, "banana");
        TreeNode node4 = new TreeNode(4, "bubble");
        TreeNode node6 = new TreeNode(6, "bubble");
        root.setLeftNode(node2);
        root.setRightNode(node3);
        node2.setLeftNode(node4);
        node2.setRightNode(node5);
        node3.setLeftNode(node6);
        ThreadBinaryTree threadBinaryTree = new ThreadBinaryTree(root);
        threadBinaryTree.threadTree(root);
        threadBinaryTree.postOrderThreeList();


    }




}
