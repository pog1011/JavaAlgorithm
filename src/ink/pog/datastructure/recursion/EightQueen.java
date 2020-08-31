package ink.pog.datastructure.recursion;

public class EightQueen {
    int max = 8;
    //把棋盘想象成一维数组，因为每下一个棋子必定要换行，且棋盘的规格为8 * 8，所以一维数组的下标为行，值为下仔第几列
    int chessBoard[] = new int[max];
    //记录总共有多少种解法
    int count = 0;

    /**
     * 具体的下棋算法
     * @param n n表示行数
     */
    public void chess(int n){
        //如果行数等于棋盘的行数就打印输出每个旗子所在的位置，并且结束此次的递归，也就是递归出口
        if(n == max){
            print();
            return;
        }
        //每一行的每一列都尝试
        for (int i = 0; i < max; i++) {
            //让行数n的值等于列数i
            chessBoard[n] = i;
            //判断下的旗子是否正确
            if(isRight(n)){
                //如果正确就下下一行
                chess(n+1);
            }
        }
    }

    /**
     * 判断所在行数下的棋子是否正确
     * @param n 表示行数
     * @return
     */
    public boolean isRight(int n){
        //循环之前的行数判断是否有冲突
        for (int i = 0; i < n; i++) {
            //!!!!!!画图容易看出来，比较抽象
            //1.如果之前的行数与要判断的行数在同一列的话返回false
            //2.如果要判断的行数减去之前的行数与要判断的行数的列减去之前的行数所在列相等的话，说明这是在一条斜线上，返回false
            if(chessBoard[i] == chessBoard[n] || Math.abs(n - i) == Math.abs(chessBoard[n] - chessBoard[i])){
                return false;
            }
        }
        return true;
    }

    //打印
    public void print(){
        for (int i = 0; i < max; i++) {
            System.out.print(chessBoard[i] + " ");
        }
        System.out.println();
        count ++;
    }
    public static void main(String[] args) {
        EightQueen eightQueen = new EightQueen();
        eightQueen.chess(0);
        System.out.println(eightQueen.count);
    }


}
