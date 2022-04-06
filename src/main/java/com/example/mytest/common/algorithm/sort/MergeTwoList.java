package com.example.mytest.common.algorithm.sort;

import com.example.mytest.common.algorithm.ListNode;

/**
 * @Author zhangchao
 * @Date 2022/3/24
 */
public class MergeTwoList {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.addNode(new ListNode(2));
        l1.addNode(new ListNode(3));
        l1.addNode(new ListNode(4));
        l1.print();
        System.out.println();
        ListNode l2 = new ListNode(2);
        l2.addNode(new ListNode(3));
        l2.addNode(new ListNode(5));
        l2.addNode(new ListNode(6));
        l2.print();
        System.out.println();

//        将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
        listNodeInfo(l1, l2);
        System.out.println();
        ListNode mgNode = mergeListNode(l1, l2);
        listNodeInfo(mgNode);

    }

    public static void listNodeInfo(ListNode l1, ListNode l2) {
        listNodeInfo(l1);
        System.out.println();
        listNodeInfo(l2);
    }

    /**
     * 合并两个有序链表(归并排序(分治涉及递归))
     *
     * @param l1
     * @param l2
     */
    public static ListNode mergeListNode(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        while (l1 != null && l2 != null) {
            // 比较 p1 和 p2 两个指针
            // 将值较小的的节点接到 p 指针
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;//指针不断前进
        }
        // 有一方为空时，直接将剩下的链接到 curr 上
        if (l1 == null)
            curr.next = l2;
        else
            curr.next = l1;
        return dummyHead.next;
    }

    /**
     * 递归遍历链表
     *
     * @param node
     */
    public static void listNodeInfo(ListNode node) {
        System.out.print(node.val);
        if (node.getNext() == null) {
            return;
        }
        System.out.print("-->");
        listNodeInfo(node.getNext());
    }
}
