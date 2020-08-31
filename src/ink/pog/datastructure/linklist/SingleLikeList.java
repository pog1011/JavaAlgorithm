package ink.pog.datastructure.linklist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.zip.CRC32;

public class SingleLikeList {
    //定义一个链表
    Student headStudent = new Student(null, null, null);

    //获取链表头
    public Student getHead() {
        return this.headStudent;
    }

    //往链表添加数据
    public void add(Student student) {
        //为了不改变原来的链表，创建一个临时的链表
        Student temp = headStudent;
        while (true) {
            if (temp.next == null) {
                break;
            }
            //判断插入的数据在链表中是否已经存在
            if (temp.next.no == student.no) {
                System.out.println("编号已存在");
                return;
            }
            //往后移
            temp = temp.next;
        }
        temp.next = student;
    }

    //添加数据按序号排列
    public void addByOrder(Student student) {
        Student temp = headStudent;
        while (true) {
            if (temp.next == null) {
                break;
                //如果temp.next.no大于插入进来的no的话，那么插入的位置就找到了，就在temp.next的前面
            } else if (temp.next.no > student.no) {
                break;
            } else if (temp.next.no == student.no) {
                System.out.println("编号已存在");
                return;
            }
            temp = temp.next;
        }
        //把插入进来数据的next与temp.next挂钩
        student.next = temp.next;
        //temp.next与插入进来的数据挂钩
        temp.next = student;
    }
    //删除指定编号的节点
    public void del(int no) {
        Student temp = headStudent;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                //这里的删除只是不引用要删除的节点，直接把要删的节点前一个节点挂钩到要删除的后一个节点上
                temp.next = temp.next.next;
                return;
            }
            temp = temp.next;
        }
        System.out.println("没有这个编号");
    }
    //获取有效节点
    public Integer getCount(Student head) {
        if (head.next == null) {
            return 0;
        }
        Student temp = head;
        Integer count = 0;
        while (true) {
            if (temp.next == null) {
                break;
            }
            count++;
            temp = temp.next;
        }
        return count;
    }

    public Student findLastIndexNode(Student head, int index) {
        if (head.next == null) {
            return null;
        }
        Integer size = getCount(head);
        if (index <= 0 || index > size) {
            return null;
        }
        Student temp = head.next;
        for (int i = 0; i < size - index; i++) {
            temp = temp.next;
        }
        return temp;
    }


    public void reverseLink(Student head) {
        if (head.next == null || head.next.next == null) {
            return;
        }
        Student cur = head.next;
        Student next = null;
        Student reverse = new Student(null, null, null);
        while (cur != null) {
            next = cur.next;    //临时储存cur.next的变量
            cur.next = reverse.next;    //讲cur.next挂钩reverse第一个节点
            reverse.next = cur;     //reverse.next等于挂钩reverse第一个节点后的cur
            cur = next;     //cur等于原先的cur.next
        }
        head.next = reverse.next;
    }

    public void reversePrint(Student head) {
        if (head.next == null) {
            return;
        }
        Student cur = head.next;
        Stack<Student> students = new Stack<>();
        while (cur != null) {
            students.push(cur);
            cur = cur.next;
        }
        while (students.size() > 0) {
            System.out.println(students.pop());
        }
    }
    //更新指定编号的节点
    public void update(Student student) {
        Student temp = headStudent;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == student.no) {
                //把temp.next.next后的节点挂钩在student.next上
                student.next = temp.next.next;
                //temp.next挂钩student
                temp.next = student;
                return;
            }
            temp = temp.next;
        }
        System.out.println("找不到该节点");
    }

    public void combine(Student headOne, Student headTwo) {
        if (headOne.next == null && headTwo.next == null) {
            return;
        }
        Student cur1 = headOne.next;
        Student cur2 = headTwo.next;
        Student orderLink = new Student(null, null, null);
        Student temp = orderLink;
        while (cur1 != null || cur2 != null) {
            if (cur1 == null) {
                temp.next = cur2;
                break;
            }
            if (cur2 == null) {
                temp.next = cur1;
                break;
            }
            if (cur1.no < cur2.no) {
                temp.next = cur1;
                temp = temp.next;
                cur1 = cur1.next;
            } else {
                temp.next = cur2;
                temp = temp.next;
                cur2 = cur2.next;
            }
        }
        headOne.next = orderLink.next;
    }
    //打出链表的数据
    public void list() {
        Student temp = headStudent;
        if (temp.next == null) {
            System.out.println("没有数据");
            return;
        }
        while (true) {
            if (temp.next == null) {
                break;
            }
            System.out.println(temp.next);
            temp = temp.next;
        }
    }
    //把传进来的链表变成环形链表
    public Student cirleLink(Student head){
        Student temp = head;
        while (true){
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = head;
        return head;
    }

    /**
     *  约瑟夫问题
     * @param head 储存数据的链表
     * @param m 每隔M个就干掉
     * @param no 从编号No开始算起
     * @return
     */
    public ArrayList josephu(Student head,int m,int no){
        if (head.next == null){
            return null;
        }
        //创建一个存放编号的数组
        ArrayList<Integer> noArray = new ArrayList<>();
        //创建一个辅助指针，用来储存被干掉的节点之前的一个节点
        Student temp = null;
        //把传进来的head变成环形链表
        Student circle = cirleLink(head.next);
        //从指定编号算起
        while (true){
            if(circle.no == no){
                break;
            }
            circle = circle.next;
        }
        while (true){
            //如果circle的下一个以及下下个都等于temp的话，说明这个环形链表只剩一个节点
            if(circle.next == temp && circle.next.next == temp){
                noArray.add(temp.no);
                break;
            }
            //循环每隔 m 个数要干掉的节点的前一个
            for (int i = 0; i < m - 1; i++) {
                //当 i 循环到最后一个的时候，此时的temp等于要被干掉的节点的前一个
                temp = circle;
                if(i == m - 2){
                    //放入要被干掉的节点的编号
                    noArray.add(circle.next.no);
                    //将circle的下一个节点指向下下个节点上，这样被干掉的节点就不会再被指向了
                    circle.next = circle.next.next;
                    break;
                }
                //往后移
                circle = circle.next;
            }
            //干掉节点之后，circle往后移动一个然后继续
            circle = circle.next;
        }
        return noArray;
    }

    public static void main(String[] args) {

        Student pog = new Student(2, "pog", 12);
        Student bubble = new Student(5, "bubble", 13);
        Student pig = new Student(1, "bag", 16);
        Student w = new Student(4, "apple", 9);
        Student q = new Student(6, "computer", 14);
        Student ee = new Student(8, "ox", 6);
        Student r = new Student(7, "pig", 8);
        List<Student> list = Arrays.asList(pig,pog,bubble,w,q,ee,r);

        list.stream()
                .filter((student)->{
                    return student.no % 2 == 0;
                })
                .filter(student -> {
                    return student.age > 10;
                })
                .map(student -> {
                    return student.name.toUpperCase();
                })
                .sorted((student1,student2)->{
                    return student1.compareTo(student2);
                })
                .limit(1)
                .forEach(System.out::println);



    }
}

 class Student {
    public Integer no;
    public String name;
    public Integer age;
    public Student next;

    public Student(Integer no, String name, Integer age) {
        this.no = no;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}