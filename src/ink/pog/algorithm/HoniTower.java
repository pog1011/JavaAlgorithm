package ink.pog.algorithm;

public class HoniTower {

    public static void move(int num,char a,char b,char c){
        if(num == 1){
            System.out.println("第" + num +"个盘从" + a + " => " + c);
        }else{
            move(num - 1,a, c, b);
            System.out.println("第" + num +"个盘从" + a + " => " + c);
            move(num - 1,b,a,c);
        }
    }

    public static void main(String[] args) {
        move(3,'A','B','C');


    }
}
