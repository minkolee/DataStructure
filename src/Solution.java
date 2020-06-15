class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }

        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        //两个都不为空
        //开始遍历两个链表, 然后组合成新链表

        //创建一个两个节点的新链表, 开始合并
        ListNode listNode = new ListNode(l1.val + l2.val);
        ListNode result = listNode;

        while (l1.next != null && l2.next != null) {
            listNode.next = new ListNode(l1.next.val + l2.next.val);
            listNode = listNode.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        //循环结束的时候, 一定是某一个l1或者l2的next为空了, 把不为空的部分, 拼上去

        if (l1.next == null) {
            listNode.next = l2.next;
        }

        if (l2.next == null) {
            listNode.next = l1.next;
        }

        //然后从result的头部开始整理链表

        ListNode start = result;

        while (start != null) {
            if (start.val >= 10) {
                start.val = start.val - 10;
                if (start.next == null) {
                    start.next = new ListNode(1);
                } else {
                    start.next.val = start.next.val + 1;
                }
            }
            start = start.next;
        }


        return result;
    }


    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(){
            @Override
            public void run() {
                System.out.println("I am thread1");
            }
        };

        thread1.start();

        thread1.join();

        System.out.println("This is main thread");
    }


}