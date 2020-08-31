package ink.pog.datastructure.array;

import java.util.Scanner;

public class Queue {
    private int maxSiez;
    private int rear;
    private int front;
    private int arr[];
    public Queue(int maxSiez) {
        this.maxSiez = maxSiez;
        arr = new int[maxSiez];
    }
    public boolean isFull() {
        //判断要给arr[rear]赋值的前一格是否为front，如果为front，那么这个队列则满
        return (rear + 1) % maxSiez == front;
    }
    public boolean isEmpty() {
        return rear == front;
    }
    public int getNum() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        int value = arr[front];
        front = (front + 1) % maxSiez;
        return value;
    }
    public int size() {
        return (rear + maxSiez - front) % maxSiez;
    }
    public void addNum(int n) {
        if (isFull()) {
            System.out.println("队列已满");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSiez;
    }
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，没有数据");
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSiez, arr[i % maxSiez]);
        }
    }
    public int getHead() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据");
        }
        return arr[front];
    }
    public static void main(String[] args) {
        Queue queue = new Queue(4);
        Scanner scanner = new Scanner(System.in);
        System.out.println("s(show)：显示队列");
        System.out.println("e(exit)：退出程序");
        System.out.println("a(add)：添加数据到队列");
        System.out.println("g(get)：从队列取出数据");
        System.out.println("h(head)：查看队列头的数据");
        boolean flag = true;
        //输出菜单
        while (flag) {
            //接收一个字符
            char key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addNum(value);
                    break;
                case 'g':
                    try {
                        int result = queue.getNum();
                        System.out.printf("取出的数据是%d\n", result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int result = queue.getHead();
                        System.out.printf("头部的数据是%d\n", result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    flag = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}
