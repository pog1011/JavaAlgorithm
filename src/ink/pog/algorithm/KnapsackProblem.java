package ink.pog.algorithm;

public class KnapsackProblem {


    public static void main(String[] args) {
        int weight[] = {1,4,3};
        int value[] = {1500,3000,2000};
        int capacity = 10;
        int count = value.length;
        int table[][] = new int[count + 1][capacity + 1];

        for(int i = 1; i < table.length; i ++){
            for(int j = 1; j < table[0].length; j ++){

                if(weight[i - 1] > j){
                    table[i][j] = table[i - 1][j];
                }else{
                    table[i][j] = Math.max(table[i - 1][j], value[i - 1] + table[i - 1][j - weight[i - 1]]);
                }

            }
        }




        for(int i = 0; i < table.length; i ++){
            for(int j = 0; j < table[0].length; j++){
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }




    }
}
