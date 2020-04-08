import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MinesweeperBoard {

    private List<String> result;

    private char[][] board;

    int row;
    int column;

    public MinesweeperBoard(List<String> list) {
        if (list.size() == 0) {
            result = Collections.emptyList();
        } else {
            row = list.size();
            column = list.get(0).length();

            board = new char[row][column];

            for (int i = 0; i < row; i++) {
                board[i] = list.get(i).toCharArray();
            }

            processBoard();


        }
    }

    /**
     * 探测指定格子周围的地雷数量, 返回周围8个格子的地雷数量 0-8
     *
     * @param x 行索引
     * @param y 列索引
     * @return 地雷的数量
     */
    private int probeMine(int x, int y) {

        int sum = 0;

        //探测左上格子
        if (x - 1 >= 0 && y - 1 >= 0) {
            if (board[x - 1][y - 1] == '*') {
                sum = sum + 1;
            }
        }

        //探测正上方格子
        if (x - 1 >= 0) {
            if (board[x - 1][y] == '*') {
                sum = sum + 1;
            }
        }

        //探测右上方格子
        if (x - 1 >= 0 && y + 1 < column) {
            if (board[x - 1][y + 1] == '*') {
                sum = sum + 1;
            }
        }

        //探测右侧格子
        if (y + 1 < column) {
            if (board[x][y + 1] == '*') {
                sum = sum + 1;
            }
        }

        //探测右下格子
        if (x + 1 < row && y + 1 < column) {
            if (board[x + 1][y + 1] == '*') {
                sum = sum + 1;
            }
        }

        //探测下方格子
        if (x + 1 < row) {
            if (board[x + 1][y] == '*') {
                sum = sum + 1;
            }
        }

        //探测左下格子
        if (x + 1 < row && y - 1 >= 0) {
            if (board[x + 1][y - 1] == '*') {
                sum = sum + 1;
            }
        }

        //探测左侧格子
        if (y - 1 >= 0) {
            if (board[x][y - 1] == '*') {
                sum = sum + 1;
            }
        }

        return sum;
    }

    /**
     * 处理棋盘, 给棋盘格子上添加周围有几个地雷的函数, 就地修改board域
     */
    private void processBoard() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (board[i][j] != '*') {
                    int result = probeMine(i, j);
                    if (result != 0) {
                        board[i][j] = (char) (result + 48);
                    }
                }
            }
        }

    }

    /**
     * 将字符数组board棋盘组装成结果. 会先检测是否已经有结果, 如果没有, 再进行组装
     *
     * @return 返回组装后的List对象
     */
    public List<String> withNumbers() {
        if (result == null) {
            result = new ArrayList<>();

            for (char[] s : board) {
                result.add(String.valueOf(s));
            }
        }
        return result;
    }


}