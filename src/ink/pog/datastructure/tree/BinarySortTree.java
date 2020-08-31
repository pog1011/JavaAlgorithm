package ink.pog.datastructure.tree;

import jdk.nashorn.internal.ir.BinaryNode;

public class BinarySortTree {

    private BinarySortTreeNode root;

    public BinarySortTree() {
        root = new BinarySortTreeNode();
    }

    public void insert(int value) {
        if (root.value == null) {
            root.value = value;
        } else {
            root.insert(value);
        }
    }

    public void del(int value) {
        if (root.value == null) {
            System.out.println("当前二叉树为空");
            return;
        }
        BinarySortTreeNode node = root.search(value);
        if (node == null) {
            System.out.println("没有此节点");
            return;
        }
        BinarySortTreeNode parentNode = root.searchParent(value);
        if (node.leftNode == null && node.rightNode == null) {
            if (parentNode == null) {
                root.value = null;
            } else {
                if (parentNode.leftNode != null && parentNode.leftNode.value == value) {
                    parentNode.leftNode = null;
                }
                if (parentNode.rightNode != null && parentNode.rightNode.value == value) {
                    parentNode.rightNode = null;
                }
            }
        }
        if (node.leftNode != null && node.rightNode == null) {
            if (parentNode != null) {
                if (parentNode.leftNode != null && parentNode.leftNode.value == value) {
                    parentNode.leftNode = node.leftNode;
                }
                if (parentNode.rightNode != null && parentNode.rightNode.value == value) {
                    parentNode.rightNode = node.leftNode;
                }
            } else {
                root = root.leftNode;

            }
        }
        if (node.rightNode != null && node.leftNode == null) {
            if (parentNode != null) {
                if (parentNode.leftNode != null && parentNode.leftNode.value == value) {
                    parentNode.leftNode = node.rightNode;
                }
                if (parentNode.rightNode != null && parentNode.rightNode.value == value) {
                    parentNode.rightNode = node.rightNode;
                }
            } else {
                root = root.rightNode;
            }
        }
        if (node.rightNode != null && node.leftNode != null) {
            int minValue = searchAndDelMinNode(node.rightNode);
            node.value = minValue;
        }
    }

    public void middleOrder() {
        if (root.value == null) {
            System.out.println("二叉树为空");
            return;
        } else {
            root.middleOrder();
        }
    }

    public int searchAndDelMinNode(BinarySortTreeNode node) {
        BinarySortTreeNode target = node;
        while (target.leftNode != null) {
            target = target.leftNode;
        }
        del(target.value);
        return target.value;
    }



    public static void main(String[] args) {

        int arr[] = {19, 2, 11, 128, 91, 24, 34, 4, 5};
        BinarySortTree root = new BinarySortTree();
        for (int i : arr) {
            root.insert(i);
        }
//        root.insert(234);
//        root.insert(-234);

        root.insert(-234);
        root.middleOrder();
    }


}

class BinarySortTreeNode {

    Integer value;
    BinarySortTreeNode leftNode;
    BinarySortTreeNode rightNode;

    public BinarySortTreeNode(Integer value) {
        this.value = value;
    }

    public BinarySortTreeNode() {
    }


    public BinarySortTreeNode search(int value) {
        if (this.value == value) {
            return this;
        }
        if (this.value > value) {
            if (this.leftNode == null) return null;
            return this.leftNode.search(value);
        } else {
            if (this.rightNode == null) return null;
            return this.rightNode.search(value);
        }
    }

    public BinarySortTreeNode searchParent(int value) {
        if ((this.leftNode != null && this.leftNode.value == value) || (this.rightNode != null && this.rightNode.value == value)) {
            return this;
        } else {
            if (this.leftNode != null && this.value > value) {
                return this.leftNode.searchParent(value);
            }
            if (this.rightNode != null && this.value <= value) {
                return this.rightNode.searchParent(value);
            }
            return null;
        }
    }

    public void insert(Integer value) {
        if (this.value < value) {
            if (this.rightNode == null) {
                this.rightNode = new BinarySortTreeNode(value);
            }
            this.rightNode.insert(value);
        }
        if (this.value > value) {
            if (this.leftNode == null) {
                this.leftNode = new BinarySortTreeNode(value);
            }
            this.leftNode.insert(value);
        }
    }

    public void middleOrder() {
        if (this.leftNode != null) {
            this.leftNode.middleOrder();
        }
        System.out.println(this.value);
        if (this.rightNode != null) {
            this.rightNode.middleOrder();
        }
    }



    @Override
    public String toString() {
        return "BinarySortTreeNode{" +
                "value=" + value +
                '}';
    }
}


