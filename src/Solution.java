
import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        // 类似于图算法的广度优先搜搜, 但是每一层可以在顶点上进行标记.

        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<Wrapper> priorityQueue = new LinkedList<>();

        Queue<Wrapper> queue = new LinkedList<>();

        int startLevel = 1;

        queue.add(new Wrapper(root, startLevel));
        while (!queue.isEmpty()) {

            System.out.println("当前队列是:"+queue);

            Wrapper ejectedNode = queue.remove();

            System.out.println("弹出的是="+ejectedNode);

            priorityQueue.add(ejectedNode);

            if (ejectedNode.node.left != null) {
                queue.offer(new Wrapper(ejectedNode.node.left, ejectedNode.level + 1));
            }

            if (ejectedNode.node.right != null) {
                queue.offer(new Wrapper(ejectedNode.node.right, ejectedNode.level + 1));
            }


        }


        //然后就是从队列中收集元素.
        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.remove());

        }


        assert priorityQueue.peek() != null;
        int lastLevel = priorityQueue.peek().level;



        while (lastLevel != 0) {
            List<Integer> templist = new ArrayList<>();

//            System.out.println(lastLevel);
//            System.out.println(priorityQueue);



            while (priorityQueue.peek().level == lastLevel) {

//                System.out.println(priorityQueue.peek().level);

                templist.add(priorityQueue.remove().node.val);
                if (priorityQueue.peek() == null) {
                    break;
                }
            }
            result.add(templist);
            lastLevel--;
        }





        return result;


    }

    private class Wrapper implements Comparable<Wrapper> {

        private TreeNode node;
        private int level;

        public Wrapper(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }

        @Override
        public int compareTo(Wrapper wrapper) {
            return this.level - wrapper.level;
        }

        @Override
        public String toString() {
            return "Wrapper{" +
                    "node=" + node.val +
                    ", level=" + level +
                    '}';
        }
    }

}