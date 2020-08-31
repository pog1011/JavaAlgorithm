package ink.pog.datastructure.linklist;

import javax.xml.stream.FactoryConfigurationError;

public class DoubleLinkList {

    Students headStudent = new Students(null,null,null);
    public Students getHead(){
        return headStudent;
    }

    public void list() {
        Students temp = headStudent;
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

    public void add(Students students){
        Students temp = headStudent;
        while (true){
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = students;
        students.pre = temp;
    }

    public void update(Students students){
        Students temp = headStudent;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == students.no) {
                //把temp.next.next后的节点挂钩在student.next上
                students.next = temp.next.next;
                students.pre = temp.next.pre;
                //temp.next挂钩student
                temp.next = students;
                return;
            }
            temp = temp.next;
        }
        System.out.println("找不到该节点");
    }

    public void del(int no) {
        Students temp = headStudent;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                //这里的删除只是不引用要删除的节点，直接把要删的节点前一个节点挂钩到要删除的后一个节点上
                temp.next = temp.next.next;
                if(temp.next == null){
                    return;
                }
                temp.next.pre = temp;
                return;
            }
            temp = temp.next;
        }
        System.out.println("没有这个编号");
    }

    public void addByOrder(Students students){
        Students temp = headStudent;
        while (true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no > students.no){
                break;
            }
            temp = temp.next;
        }
            students.next = temp.next;
            students.pre = temp;
            temp.next = students;
    }



    public static void main(String[] args) {
        DoubleLinkList doubleLinkList = new DoubleLinkList();
        doubleLinkList.addByOrder(new Students(1,"pog",12));
        doubleLinkList.addByOrder(new Students(3,"pig",13));
        doubleLinkList.addByOrder(new Students(2,"bubble",14));


    }



}

class Students {
    public Integer no;
    public String name;
    public Integer age;
    public Students next;
    public Students pre;
    public Students(Integer no, String name, Integer age) {
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