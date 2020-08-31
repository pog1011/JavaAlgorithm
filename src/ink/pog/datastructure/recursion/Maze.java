package ink.pog.datastructure.recursion;

import java.util.Random;

public class Maze {

    /**
     * 走迷宫的解法
     * @param maze  地图
     * @param i 要走的行
     * @param j 要走的列
     * @return 是否走出迷宫
     */
    public static boolean riddle(int[][] maze,int i,int j){
        //如果走到这个点上就算是出口
        if(maze[5][4] == 2){
            return true;
        }
        //如果要走的 i 行 j 列为 0 的话，说明此路可以走
        if(maze[i][j] == 0){
            //2 代表走过的标志，如果要走的 i 行 j 列可以走的话，就另其为2
            maze[i][j] = 2;
            //走迷宫的策略，策略不同，走的方式也就不同，这里的策略为 : 右 -> 下 -> 左 -> 上 (优先级 右 最高，上 最低)
            //这里比较抽象，抽象的去想
            if(riddle(maze,i,j+1) || riddle(maze,i + 1, j) || riddle(maze, i, j -1) || riddle(maze,i - 1,j)){
                return true;
            }else{
                //如果所走的坐标行不通的，就标注为3
                maze[i][j] = 3;
                return false;
            }
        }else {
            return false;
        }


    }

    public static void main(String[] args) {
        int maze[][] = new int[8][8];
        for (int i = 0; i < maze.length; i++) {
            maze[0][i] = 1;
            maze[7][i] = 1;
            maze[i][0] = 1;
            maze[i][7] = 1;
        }
        Random random = new Random();
        maze[random.nextInt(7)][random.nextInt(7)] = 1;
        maze[random.nextInt(7)][random.nextInt(7)] = 1;
        maze[random.nextInt(7)][random.nextInt(7)] = 1;
        System.out.println("迷宫地图");
        for (int i = 0; i < maze.length; i++) {
            for (int num : maze[i]) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
        int i = random.nextInt(7);
        int j = random.nextInt(7);
        maze[i][j] = 0;
        maze[5][4] = 0;
        System.out.println(i + " " + j);
        riddle(maze,i,j);
        System.out.println("迷宫地图");
        for (int z = 0; z < maze.length; z++) {
            for (int num : maze[z]) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

}
