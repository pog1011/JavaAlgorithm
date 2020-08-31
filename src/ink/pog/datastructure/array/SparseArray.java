package ink.pog.datastructure.array;

public class SparseArray {


    public static void main(String[] args) {

        int arr[][] = new int[11][11];

        arr[1][10] = 3;
        arr[2][9] = 4;
        arr[10][1] = 5;
        arr[8][10] = 6;
        int sum = 0;
        int sparseArr[][] = null;
        for (int i = 0; i < 2; i++) {
            if (i == 1) {
                sparseArr = new int[sum + 1][3];
                sparseArr[0][0] = 11;
                sparseArr[0][1] = 11;
                sparseArr[0][2] = sum;
            }
            for (int z = 0; z < 11; z++) {
                for (int j = 0; j < 11; j++) {
                    if (arr[z][j] != 0) {
                        if (i == 0) {
                            sum += 1;
                        } else {
                            sparseArr[sum][0] = z;
                            sparseArr[sum][1] = j;
                            sparseArr[sum][2] = arr[z][j];
                            sum--;
                        }
                    }
                }
            }
        }


        int newArr[][] = new int[sparseArr[0][0]][sparseArr[0][1]];

        for (int i = 1; i < sparseArr.length; i++) {
                newArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }


        for (int[] ints : newArr) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }




    }
}
