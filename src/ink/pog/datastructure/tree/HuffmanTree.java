package ink.pog.datastructure.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {


    public static void main(String[] args) {
        int arr[] = {1,49,8,10,23};

        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            nodes.add(new Node(arr[i]));
        }

       while (nodes.size() > 1){
           Collections.sort(nodes);
           Node leftNode = nodes.get(0);
           Node rightNode = nodes.get(1);
           Node parent = new Node(leftNode.value + rightNode.value);
           parent.left = leftNode;
           parent.right = rightNode;
           nodes.add(parent);
           nodes.remove(leftNode);
           nodes.remove(rightNode);
       }
       nodes.get(0).preOrder();


    }




}

class Node implements Comparable<Node>{

    Integer value;
    Node left;
    Node right;

    public void preOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }


    public Node(Integer value){
        this.value = value;
    }
    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}

