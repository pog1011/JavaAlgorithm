package ink.pog.datastructure.tree;

public class AVLTree {
    private AVLTreeNode root;

    public AVLTree() {
        root = new AVLTreeNode();
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
        AVLTreeNode node = root.search(value);
        if (node == null) {
            System.out.println("没有此节点");
            return;
        }
        AVLTreeNode parentNode = root.searchParent(value);
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

    public int searchAndDelMinNode(AVLTreeNode node) {
        AVLTreeNode target = node;
        while (target.leftNode != null) {
            target = target.leftNode;
        }
        del(target.value);
        return target.value;
    }


    public static void main(String[] args) {
        int[] arr = {2, 23, 3, 6, 5, 7, 8,25};
        AVLTree avlTree = new AVLTree();
        for (int i : arr) {
            avlTree.insert(i);
        }
//        avlTree.middleOrder();
        avlTree.del(6);
        avlTree.del(2);
        avlTree.del(3);
        avlTree.del(6);
        avlTree.del(5);
        avlTree.insert(10);
        avlTree.insert(2);
        avlTree.insert(11);
        System.out.println(avlTree.root.height());
        System.out.println(avlTree.root.leftHeight());
        System.out.println(avlTree.root.rightHeight());
        avlTree.middleOrder();

    }
}

class AVLTreeNode {

    Integer value;
    AVLTreeNode leftNode;
    AVLTreeNode rightNode;

    public AVLTreeNode(Integer value) {
        this.value = value;
    }

    public AVLTreeNode() {
    }


    public AVLTreeNode search(int value) {
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

    public AVLTreeNode searchParent(int value) {
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
                this.rightNode = new AVLTreeNode(value);
            }
            this.rightNode.insert(value);
        }
        if (this.value > value) {
            if (this.leftNode == null) {
                this.leftNode = new AVLTreeNode(value);
            }
            this.leftNode.insert(value);
        }
        if (rightHeight() - leftHeight() > 1) {
            if (rightNode.leftHeight() - rightNode.rightHeight() >= 1) {
                rightNode.rightRotate();
                leftRotate();
            } else {
                leftRotate();
            }
            return;
        }
        if (leftHeight() - rightHeight() > 1) {
            if (leftNode.rightHeight() - leftNode.leftHeight() >= 1) {
                leftNode.leftRotate();
                rightRotate();
            } else {
                rightRotate();
            }
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

    public int height() {
        return Math.max(leftNode == null ? 0 : leftNode.height(), rightNode == null ? 0 : rightNode.height()) + 1;

    }

    public int leftHeight() {
        if (leftNode == null) {
            return 0;
        } else {
            return leftNode.height();
        }
    }

    public int rightHeight() {
        if (rightNode == null) {
            return 0;
        } else {
            return rightNode.height();
        }
    }

    public void leftRotate() {
        AVLTreeNode newLeftNode = new AVLTreeNode(value);
        newLeftNode.leftNode = leftNode;
        newLeftNode.rightNode = rightNode.leftNode;
        value = rightNode.value;
        rightNode = rightNode.rightNode;
        leftNode = newLeftNode;
    }

    public void rightRotate() {
        AVLTreeNode newRightNode = new AVLTreeNode(value);
        newRightNode.rightNode = rightNode;
        newRightNode.leftNode = leftNode.rightNode;
        value = leftNode.value;
        leftNode = leftNode.leftNode;
        rightNode = newRightNode;
    }

    @Override
    public String toString() {
        return "AVLTreeNode{" +
                "value=" + value +
                '}';
    }
}
