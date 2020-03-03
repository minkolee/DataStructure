package datastructure.chapter5.exercise;

import datastructure.chapter5.LinkedListStack;
import datastructure.chapter5.Stack;
import java.util.Objects;

public class PathFinder {

    private Point start;

    private Point destination;

    //一个字符数组, 规定如下
    // X 就是阻塞, 不可通行, 相当于墙壁
    // 空格 表示可以通行, 还没有走过, 也没有访问过和加入过路径
    // a 表示访问过, 但是无法通行, 所以标记为a
    // *, 保持在栈中的点, 即路径
    // 边缘, 也属于不可通行
    private char[][] maze;

    private int row;

    private int column;

    //存放路径的栈
    private Stack<Point> path = new LinkedListStack<>();

    //构造器, 传入迷宫对象, 起点和终点的坐标, 创建起点, 并将起点加入栈中, 将字符数组中对应的坐标设置为*
    public PathFinder(char[][] maze, int startX, int startY, int endX, int endY) {
        this.maze = maze;
        this.start = new Point(startX-1, startY-1);
        this.destination = new Point(endX-1, endY-1);
        path.push(start);
        maze[startX-1][startY-1] = '*';
        row = maze.length;
        column = (maze[0]).length;
//        System.out.println("迷宫初始化完毕, 有 "+row+" 行, "+ column+" 列.");
//        System.out.println("迷宫初始化完毕, 起点是: "+ start);
//        System.out.println("迷宫初始化完毕, 终点是: "+ destination);
    }

    //主算法
    //从起点出发, 将起点加入path中, 然后尝试往一个方向走, 如果可以的话, 将走到的格子加到栈中
    //如果一个格子各个方向都走不通(即所有方向都是路径中, 阻塞, 已经访问, 则将该格子标记为已经访问, 然后将该格子弹出)
    //继续从栈顶尝试往其他方向走.
    //每走一步, 都判定栈顶的点是不是出口, 如果是出口则结束. 如果当前格子是起点, 其他所有格子都是已经访问过, 则失败.
    public void findPath() {
        //只要栈不为空
        while (!path.isEmpty()) {
            //尝试寻找栈顶点的相邻可通行点
            Point currentPoint = path.peek();
//            System.out.println("当前的点是: " + currentPoint);
            Point nextPoint = path.peek().getNextPoint();
//            System.out.println("下一个可用点是: "+ nextPoint);
            //如果有相邻可通行点, 将可通行点加入path, 同时更改数组的标记
            if (nextPoint != null) {
                path.push(nextPoint);
                maze[nextPoint.x][nextPoint.y] = '*';
                //判断是否为终点, 是终点就结束循环
                if (path.peek().equals(destination)) {
                    break;
                }
            }
            //如果没有相邻可通行点, 说明当前路径不通, 需要将当前路径标记为已经访问, 然后弹出栈.
                else {
                maze[currentPoint.x][currentPoint.y] = 'a';
                path.pop();
            }
        }

        //循环结束之后, 如果栈为空, 说明无路可走,退回到起点
        //如果栈不为空而且break
        if (path.isEmpty()) {
            System.out.println("该迷宫没有从起点到终点的路径");
        } else {
            System.out.println("找到一条路径, 用*号表示, a表示该算法曾经探寻过的路线:");
            printMaze();
        }


    }

    private void printMaze() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
    }

    private class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        public Point getNextPoint() {
//            System.out.println("尝试获取可通行的点");


            //再尝试右方, 横向最小索引是0, 最大索引是column-1
            if (y != column - 1) {
                if (maze[x][y + 1] == ' ') {
//                    System.out.println("可用的点是右方的点, x=" + x + "y=" + y);
                    return new Point(x, y + 1);
                }
            }

            //先尝试下方, 最小索引是0, 最大索引是row-1
            // 如果x不是在最底部, 检查x下方坐标的状态, 如果可以通行, 返回那个点
            if (x != row - 1) {
                if (maze[x + 1][y] == ' ') {
//                    System.out.println("可用的点是下方的点");
                    return new Point(x + 1, y);
                }
            }

            //再尝试上方, x索引最小为0, 上方最小索引是0
            if (x != 0) {
                if (maze[x - 1][y] == ' ') {
//                    System.out.println("可用的点是上方的点");
                    return new Point(x - 1, y);
                }
            }

            //最后尝试左方
            if (y != 0) {
                if (maze[x][y - 1] == ' ') {
//                    System.out.println("可用的点是左方的点");
                    return new Point(x, y - 1);
                }
            }

            //四个方向都走不通, 返回null
            return null;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public static void main(String[] args) {


        //7行, 13列的迷宫, 坐标为先行,后列, 起点是 (2,1), 出口是 (6,13)
        char[][] maze = new char[][]{
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                {' ', ' ', ' ', 'X', ' ', ' ', ' ', 'X', 'X', 'X', 'X', ' ', 'X'},
                {'X', 'X', ' ', ' ', ' ', 'X', ' ', ' ', ' ', ' ', 'X', ' ', ' '},
                {'X', 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', ' ', ' ', 'X'},
                {'X', 'X', ' ', ' ', 'X', ' ', ' ', ' ', 'X', ' ', 'X', ' ', 'X'},
                {'X', 'X', ' ', 'X', 'X', 'X', 'X', 'X', ' ', 'X', 'X', ' ', 'X'},
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
        };
        PathFinder finder = new PathFinder(maze, 2, 1, 3, 13);

        finder.findPath();

    }

}
