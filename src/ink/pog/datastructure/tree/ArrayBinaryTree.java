package ink.pog.datastructure.tree;

public class ArrayBinaryTree {
    int[] arr;
    public ArrayBinaryTree(int arr[]){
        this.arr = arr;
    }

    public void preOrder(int index){
        if(this.arr.length == 0){
            System.out.println("arr is null");
            return;
        }

        System.out.println(this.arr[index]);
        if((index * 2 + 1) < this.arr.length){
            preOrder(index * 2 + 1);
        }
        if((index * 2 + 2) < this.arr.length){
            preOrder(index * 2 + 2);
        }

    }

    public void middleOrder(int index){
        if(arr.length == 0){
            System.out.println("arr is null");
            return;
        }
        if((index * 2 + 1) <arr.length){
            middleOrder(index * 2 + 1);
        }
        System.out.println(arr[index]);
        if((index * 2 + 2) < arr.length){
            middleOrder(index * 2 + 2);
        }
    }

    public void postOrder(int index){
        if(arr.length == 0){
            System.out.println("arr is null");
            return;
        }
        if((index * 2 + 1) < arr.length){
            postOrder(index * 2 + 1);
        }
        if((index * 2 + 2) < arr.length){
            postOrder(index * 2 + 2);
        }
        System.out.println(arr[index]);
    }


    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5,6,7,8};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder(0);

    }

}
