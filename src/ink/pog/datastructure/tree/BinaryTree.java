package ink.pog.datastructure.tree;

public class BinaryTree {
    private TreeNode root;

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public void preOder() {
        if (this.root == null) {
            System.out.println("二叉树为空");
            return;
        }
        this.root.preOrder();
    }

    public void middleOrder() {
        if (this.root == null) {
            System.out.println("二叉树为空");
            return;
        }
        this.root.middleOrder();
    }

    public void postOrder() {
        if (this.root == null) {
            System.out.println("二叉树为空");
            return;
        }
        this.root.postOrder();
    }

    public TreeNode preOrderSearch(int no) {
        if (this.root == null) {
            return null;
        }
        return this.root.preOrderSearch(no);
    }

    public TreeNode middleSearch(int no) {
        if (this.root == null) {
            return null;
        }
        return this.root.middleOrderSearch(no);
    }

    public TreeNode postOrderSearch(int no) {
        if (this.root == null) {
            return null;
        }
        return this.root.postOrderSearch(no);
    }

    public void del(int no){
        if(this.root == null){
            System.out.println("root is null");
            return;
        }else if (this.root.getNo() == no){
            this.root = null;
            return;
        }else{
            this.root.del(no);
        }
    }
    public static void main(String[] args) {
//        TreeNode treeNode = new TreeNode();
//        int arr[] = {1,2,3,4,5,6,7,8};
//        TreeNode root = new TreeNode();
//        treeNode.pre(arr, 0, root);

        BinaryTree binaryTree = new BinaryTree();
        TreeNode root = new TreeNode(1, "pog");
        TreeNode node1 = new TreeNode(2, "pig");
        TreeNode node2 = new TreeNode(3, "bubble");
        TreeNode node3 = new TreeNode(4, "babble");
        TreeNode node4 = new TreeNode(5, "banana");
        TreeNode node5 = new TreeNode(6, "baggage");
        TreeNode node6 = new TreeNode(7, "bag");
        TreeNode node7 = new TreeNode(8, "bad");
        TreeNode node8 = new TreeNode(9, "bed");
        TreeNode node9 = new TreeNode(10, "ban");
        node3.setLeftNode(node4);
        node2.setLeftNode(node3);
        node1.setLeftNode(node2);
        node1.setRightNode(node5);
        node7.setRightNode(node9);
        node6.setRightNode(node7);
        node6.setLeftNode(node8);
        root.setRightNode(node6);
        root.setLeftNode(node1);
        binaryTree.setRoot(root);
        binaryTree.postOrder();
//        System.out.println("before del");
//        binaryTree.postOrder();
//        binaryTree.del(2);
//        System.out.println("after del");
//        binaryTree.postOrder();
    }

}


class TreeNode {
    private Integer no;
    private String name;
    private TreeNode leftNode;
    private TreeNode rightNode;

    private int LeftType;
    private int RightType;

    public TreeNode(){}
    public void setLeftType(int leftType) {
        LeftType = leftType;
    }

    public void setRightType(int rightType) {
        RightType = rightType;
    }
    public TreeNode(Integer no) {
        this.no = no;
    }
    public TreeNode(Integer no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "no=" + no +
                ", name='" + name;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(TreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public TreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(TreeNode rightNode) {
        this.rightNode = rightNode;
    }

    public int getLeftType() {
        return LeftType;
    }

    public int getRightType() {
        return RightType;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.leftNode != null) {
            this.leftNode.preOrder();
        }
        if (this.rightNode != null) {
            this.rightNode.preOrder();
        }
    }

    public void pre(int arr[],int index,TreeNode root){

        if(index * 2 + 1 < arr.length){
            root.setLeftNode(new TreeNode(arr[index * 2 + 1]));
            pre(arr,index * 2 + 1,root.leftNode);
        }
        if(index * 2 + 2 < arr.length){
            root.setRightNode(new TreeNode(arr[index * 2 + 2]));
            pre(arr,index * 2 + 2,root.rightNode);
        }
    }

    public void middleOrder() {
        if (this.leftNode != null) {
            this.leftNode.middleOrder();
        }
        System.out.println(this);
        if (this.rightNode != null) {
            this.rightNode.middleOrder();
        }
    }

    public void postOrder() {
        if (this.leftNode != null) {
            this.leftNode.postOrder();
        }
        if (this.rightNode != null) {
            this.rightNode.postOrder();
        }
        System.out.println(this);
    }

    public TreeNode preOrderSearch(int no) {
        System.out.println("进入前序");
        if (this.no == no) {
            return this;
        }
        TreeNode temp = null;
        if (this.leftNode != null) {
            temp = this.leftNode.preOrderSearch(no);
        }
        if (temp != null) {
            return temp;
        }
        if (this.rightNode != null) {
            temp = this.rightNode.preOrderSearch(no);
        }
        return temp;
    }

    public TreeNode middleOrderSearch(int no) {

        TreeNode temp = null;
        if (this.leftNode != null) {
            temp = this.leftNode.middleOrderSearch(no);
        }

        if (this.no == no) {
            return this;
        }
        if (temp != null) {
            return temp;
        }
        System.out.println(this);
        if (this.rightNode != null) {
            temp = this.rightNode.middleOrderSearch(no);
        }
        return temp;
    }

    public TreeNode postOrderSearch(int no) {

        TreeNode temp = null;

        if (this.leftNode != null) {
            temp = this.leftNode.postOrderSearch(no);
        }
        if (this.rightNode != null) {
            temp = this.rightNode.postOrderSearch(no);
        }

        if (this.no == no) {
            return this;
        }
        if(temp != null){
            return temp;
        }
        System.out.println(this);
        return temp;
    }

    public void del(int no){

        if(this.leftNode != null && this.leftNode.no == no){

            if(this.leftNode.leftNode != null){
                this.leftNode = this.leftNode.leftNode;
                return;
            }else if(this.leftNode.rightNode != null){
                this.leftNode = this.leftNode.rightNode;
                return;
            } else{
                this.leftNode = null;
                return;
            }

        }
        if(this.rightNode != null && this.rightNode.no == no){
            if(this.rightNode.leftNode != null){
                this.rightNode = this.rightNode.leftNode;
                return;
            }else if(this.rightNode.rightNode != null){
                this.rightNode = this.rightNode.rightNode;
                return;
            }else{
                this.rightNode = null;
                return;
            }
        }

        if(this.leftNode != null){
            this.leftNode.del(no);
        }
        if(this.rightNode != null){
            this.rightNode.del(no);
        }
    }
}
