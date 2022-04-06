package com.example.mytest.common.algorithm.sort;

import com.example.mytest.common.algorithm.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author zhangchao
 * @Date 2022/3/24
 */
public class MergeKList {

//    题目：
//    合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
//    示例:
//    输入:
//            [
//            1->4->5,
//            1->3->4,
//            2->6
//            ]
//    输出: 1->1->2->3->4->4->5->6

    public static void main(String[] args) {

//        peek()//返回队首元素
//        poll()//返回队首元素，队首元素出队列
//        add()//添加元素
//        size()//返回队列元素个数
//        isEmpty()//判断队列是否为空，为空返回true,不空返回false

        ListNode l1 = new ListNode(1);
        l1.addNode(new ListNode(2));
        l1.addNode(new ListNode(3));
        l1.addNode(new ListNode(4));

        ListNode l2 = new ListNode(2);
        l2.addNode(new ListNode(3));
        l2.addNode(new ListNode(5));
        l2.addNode(new ListNode(6));

        List<ListNode> list = new ArrayList<>();
        list.add(l1);
        list.add(l2);

        ListNode listNode = kListSort(list);
        listNode.print();
    }

    private static ListNode kListSort(List<ListNode> list) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        list.stream().forEach(a -> {
            while (a != null) {
                queue.add(a.val);
                a = a.next;
            }
        });

        ListNode listNode = new ListNode(0);
        while (!queue.isEmpty()) {
            Integer e = queue.poll();
            System.out.println(e);
            listNode.val = e;
            listNode.next=new ListNode(e);
        }
        return listNode;
    }

//    private static void addQueue(PriorityQueue<Integer> queue, ListNode a) {
//        if (a == null) {
//            return;
//        }
//        queue.add(a.val);
//        addQueue(queue, a.next);
//    }
}
