package indi.andy.structs.list;




public class MergeList {






    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if (l1 == null && l2 == null) return null;
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode temp = new ListNode(0);
        ListNode ans = temp;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;

            }
            temp = temp.next;
        }

        if (l1 != null) {
            temp.next = l1;
        }

        if (l2 != null) {
            temp.next = l2;
        }

        return ans.next;
    }


    public static void main(String[] args) {

        ListNode node9 = new ListNode(9);
        ListNode node8 = new ListNode(8);
        ListNode node7 = new ListNode(7, node9);
        ListNode node6 = new ListNode(6, node8);
        ListNode node5 = new ListNode(5, node7);
        ListNode node4 = new ListNode(4, node6);
        ListNode node3 = new ListNode(3, node5);
        ListNode node2 = new ListNode(2, node4);
        ListNode node1 = new ListNode(1, node3);

        ListNode mergeTwoLists = mergeTwoLists(node1, node2);

        while (mergeTwoLists != null) {
            System.out.println(mergeTwoLists.val);
            mergeTwoLists = mergeTwoLists.next;
        }



    }
}
