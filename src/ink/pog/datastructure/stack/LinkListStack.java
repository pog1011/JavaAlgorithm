package ink.pog.datastructure.stack;

public class LinkListStack {

    StackDemo head = new StackDemo(null,null);

    public StackDemo getHead(){
        return this.head;
    }

    public void push(StackDemo stackDemo){
        stackDemo.next = head.next;
        head.next = stackDemo;
    }

    public StackDemo pop(){
        if(head.next == null){
            throw new RuntimeException("栈没有数据");
        }
        head = head.next;
        return head;
    }

    public static void main(String[] args) {

        LinkListStack linkListStack = new LinkListStack();
        linkListStack.push(new StackDemo(1,11));
        linkListStack.push(new StackDemo(2,22));
        linkListStack.push(new StackDemo(3,33));
        linkListStack.push(new StackDemo(4,44));
        System.out.println(linkListStack.pop());
        System.out.println(linkListStack.pop());
        System.out.println(linkListStack.pop());
        System.out.println(linkListStack.pop());

    }



}
class StackDemo{
    private Integer no;

    private Integer num;

    public StackDemo next;

    public StackDemo(Integer no,Integer num){
        this.no = no;
        this.num = num;
    }

    @Override
    public String toString() {
        return "StackDemo{" +
                "no=" + no +
                ", num=" + num +
                '}';
    }
}