package io.cheny.sparsearray;

/**
 * 稀疏数组 .
 *
 * @author BibiChen
 * @version v1.0
 * @since 2019/8/20
 */
public class SparseArray {

    public static void main(String[] args) {
        // 创建一个原始的二维数组 11 * 11
        // 0：表示没有棋子，1：表示 黑子 2：表示 蓝子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[5][8] = 2;
        // 输出原始的二维数组
        System.out.println("原始的二维数组");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        // 将二维数组 转 稀疏数组
        // 1. 先遍历二维数组 得到非 0 数据的个数
        int sum = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        System.out.println("sum = " + sum);
        // 2. 创建对应的稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        // 给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        // 遍历二维数组，将非 0 的值存放到 sparseArr 中
        // count 用于记录是第几个非 0 数据
        int count = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        System.out.println("得到的稀疏数组~~");
        for (int[] sparse : sparseArr) {
            for (int data : sparse) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        // 稀疏数组 恢复成 二维数组
        /*
            1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
            2. 再读取稀疏数组后几行的数据，并赋给 原始的二维数组
         */
        // 1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        // 2. 再读取稀疏数组后几行的数据，并赋给 原始的二维数组
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        System.out.println("稀疏数组转二维数组~~");
        for (int[] ints : chessArr2) {
            for (int data : ints) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

    }

}
