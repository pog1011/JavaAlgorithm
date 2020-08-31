package ink.pog.datastructure.stack;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayStack {

    private int maxSize;
    private int[] stack;
    private int top = -1;
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    public void push(int num){
        if(top == maxSize - 1){
            System.out.println("栈已满");
            return;
        }
        stack[++top] = num;
    }

    public int pop(){
        if(top == -1){
            throw new RuntimeException("栈没有数据");
        }
        return stack[top--];
    }


    public static void main(String[] args) {

        ArrayStack stack = new ArrayStack(5);
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);
        stack.push(50);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());


    }



}
