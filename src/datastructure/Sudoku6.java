package datastructure;

import java.util.Arrays;
import java.util.Scanner;

public class Sudoku6 {

    private int[][] board = new int[6][6];

    //用于判断数字
    private boolean[] tempArray = new boolean[6];
    private int count = 6;

    private int numberToCompute = 36;

    //初始化, 输入宫格
    public Sudoku6() {

        Arrays.fill(tempArray, true);

        Scanner scanner = new Scanner(System.in);

        System.out.println("请一行一行输入已知的数字, 未知用0表示: ");

        for (int i = 0; i < 6; i++) {
            String line = scanner.nextLine();
            String[] numbers = line.split(" ");
            for (int j = 0; j < numbers.length; j++) {
                int number = Integer.parseInt(numbers[j]);
                board[i][j] = number;
                if (number != 0) {
                    numberToCompute--;
                }
            }
        }

    }

    //显示此时的宫格
    private void showBoard() {
        System.out.println("+---+---+---+---+---+---+");
        for (int i = 0; i < 6; i++) {
            System.out.print('|');
            for (int j = 0; j < 6; j++) {
                if (board[i][j] == 0) {
                    System.out.print("   " + "|");

                } else {
                    System.out.print(" "+board[i][j] + " |");
                }
            }
            System.out.println();
            System.out.println("+---+---+---+---+---+---+");
        }
    }

    public void getResult() throws InterruptedException {
        while (numberToCompute != 0) {
            for (int row = 0; row < 6; row++) {
                for (int column = 0; column < 6; column++) {
                    System.out.println();
                    System.out.println("--------------------------------判断第 " + (row + 1) + " 行 第 " + (column + 1) + " 列的格子--------------------------------");
                    checkSingleCell(row, column);
                    System.out.print("还有 " + numberToCompute + " 个格子等待判断. ");
                    System.out.println("当前宫格状态是: ");
                    showBoard();
                    System.out.println("--------------------------------判断第 " + (row + 1) + " 行 第 " + (column + 1) + " 列的格子--------------------------------");
                    if (numberToCompute == 0) {
                        System.exit(0);
                    }
                    System.out.println();
                }
            }
        }
        showBoard();
    }

    //检查当前的格子是不是有唯一解
    private int checkCurrentResult() {
        //count = 1的时候有唯一解, 对应1-6,找到那个为true的, 返回索引+1, 就是这个格子要放入的数字
        if (count == 1) {
            for (int i = 0; i < tempArray.length; i++) {
                if (tempArray[i]) {
                    return i + 1;
                }
            }
        }
        return -1;
    }

    //重新设置判断条件为初始状态
    private void resetTempArray() {
        Arrays.fill(tempArray, true);
        count = 6;
    }

    //检查一个格子是否符合条件并赋值, 返回false表示找到唯一解并赋值
    private boolean checkAndSet(int rowIndex, int columnIndex) {
        //如果返回不等于-1, 说明找到了唯一解, 将唯一解赋给当前格子
        if (checkCurrentResult() != -1) {
            board[rowIndex][columnIndex] = checkCurrentResult();
            numberToCompute--;
            System.out.println("发现当前格子的唯一可能数字是: " + checkCurrentResult());
            return true;
        } else {
            return false;
        }
    }

    //检查单个单元格, 如果不等于0说明已经有值, 直接跳过并重置条件
    private void checkSingleCell(int rowIndex, int columnIndex) {
        if (board[rowIndex][columnIndex] != 0) {
            System.out.println("当前格子已经有数字是: " + board[rowIndex][columnIndex] + " 跳过当前格子");
            resetTempArray();
        } else {
            //先检查所在行
            checkRow(rowIndex);
            if (checkAndSet(rowIndex, columnIndex)) {
                resetTempArray();
                return;
            }
            //检查所在列
            checkColumn(columnIndex);
            if (checkAndSet(rowIndex, columnIndex)) {
                resetTempArray();
                return;
            }
            //检查所在区域
            checkArea(rowIndex, columnIndex);
            if (checkAndSet(rowIndex, columnIndex)) {
                resetTempArray();
            }
            resetTempArray();

        }
    }

    //检查所在行的方法
    private void checkRow(int rowIndex) {
        System.out.println("开始检查第 " + (rowIndex + 1) + "行");
        for (int j = 0; j < 6; j++) {
            //这一行所在的格子不等于0, 说明已经有了数字, 将这个数字-1对应的索引设置为false, 同时count-1, 表示去掉一个可能性.
            if (board[rowIndex][j] != 0 && tempArray[board[rowIndex][j] - 1]) {
                System.out.println("数字不可能是: " + board[rowIndex][j]);
                tempArray[board[rowIndex][j] - 1] = false;
                count--;
            }
        }

    }

    //检查所在列的方法
    private void checkColumn(int columnIndex) {
        System.out.println("开始检查第 " + (columnIndex + 1) + "列");
        for (int j = 0; j < 6; j++) {
            //这一列所在的格子不等于0, 说明已经有了数字, 将这个数字-1对应的索引设置为false, 同时count-1, 表示去掉一个可能性.
            if (board[j][columnIndex] != 0 && tempArray[board[j][columnIndex] - 1]) {
                System.out.println("数字不可能是: " + board[j][columnIndex]);
                tempArray[board[j][columnIndex] - 1] = false;
                count--;
            }
        }
    }

    //检查所在区域, 这个最麻烦
    private void checkArea(int x, int y) {
        //区域1
        System.out.print("开始检查所在六宫格.");

        if (x <= 1 && y <= 2) {
            System.out.println("当前格子属于左上区域");
            for (int i = 0; i <= 1; i++) {
                for (int j = 0; j <= 2; j++) {
                    if (board[i][j] != 0 && tempArray[board[i][j] - 1]) {
                        System.out.println("数字不可能是: " + board[i][j]);
                        tempArray[board[i][j] - 1] = false;
                        count--;
                    }
                }
            }
        } else if (x <= 1 && y >= 3) {
            System.out.println("当前格子属于右上区域");

            for (int i = 0; i <= 1; i++) {
                for (int j = 3; j <= 5; j++) {
                    if (board[i][j] != 0 && tempArray[board[i][j] - 1]) {
                        System.out.println("数字不可能是: " + board[i][j]);
                        tempArray[board[i][j] - 1] = false;
                        count--;
                    }
                }
            }
        } else if (x >= 2 && x <= 3 && y <= 2) {
            System.out.println("当前格子属于左中区域");

            for (int i = 2; i <= 3; i++) {
                for (int j = 0; j <= 2; j++) {
                    if (board[i][j] != 0 && tempArray[board[i][j] - 1]) {
                        System.out.println("数字不可能是: " + board[i][j]);
                        tempArray[board[i][j] - 1] = false;
                        count--;
                    }
                }
            }
        } else if (x >= 2 && x <= 3 && y >= 3) {
            System.out.println("当前格子属于右中区域");
            for (int i = 2; i <= 3; i++) {
                for (int j = 3; j <= 5; j++) {
                    if (board[i][j] != 0 && tempArray[board[i][j] - 1]) {
                        System.out.println("数字不可能是: " + board[i][j]);
                        tempArray[board[i][j] - 1] = false;
                        count--;
                    }
                }
            }
        } else if (x >= 4 && y <= 2) {
            System.out.println("当前格子属于左下区域");
            for (int i = 4; i <= 5; i++) {
                for (int j = 0; j <= 2; j++) {
                    if (board[i][j] != 0 && tempArray[board[i][j] - 1]) {
                        System.out.println("数字不可能是: " + board[i][j]);
                        tempArray[board[i][j] - 1] = false;
                        count--;
                    }
                }
            }
        } else {
            System.out.println("当前格子属于右下区域");
            for (int i = 4; i <= 5; i++) {
                for (int j = 3; j <= 5; j++) {
                    if (board[i][j] != 0 && tempArray[board[i][j] - 1]) {
                        System.out.println("数字不可能是: " + board[i][j]);
                        tempArray[board[i][j] - 1] = false;
                        count--;
                    }
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {

        Sudoku6 sudoku6 = new Sudoku6();

        sudoku6.showBoard();

        sudoku6.getResult();
    }

}
