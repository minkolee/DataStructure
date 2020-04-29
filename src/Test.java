import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test {

    public static void main(String[] args) {

        TreeNode treeNode1 = new TreeNode(0);

        treeNode1.right = new TreeNode(4);
        treeNode1.left = new TreeNode(2);

        treeNode1.right.right = new TreeNode(-1);
        treeNode1.right.left = new TreeNode(3);
        treeNode1.right.left.right = new TreeNode(6);
        treeNode1.right.right.right = new TreeNode(8);


        treeNode1.left.left = new TreeNode(1);
        treeNode1.left.left.left = new TreeNode(5);
        treeNode1.left.left.right = new TreeNode(1);


        System.out.println(new Solution().levelOrderBottom(treeNode1));

    }
}
