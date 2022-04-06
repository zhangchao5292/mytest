package com.example.mytest.common.algorithm;

/**
 * 创建链表 https://blog.csdn.net/qq_42124842/article/details/91368624
 *
 * @Author zhangchao
 * @Date 2022/3/24
 */
public class ListNode {

    public Integer val;

    public ListNode next;

    public ListNode(Integer val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode(Integer val) {
        this.val = val;
    }

    // 添加新的结点
    public void addNode(ListNode newNode) {
        if (null == this.next) {
            this.next = newNode;
        } else {
            this.next.addNode(newNode);
        }
    }

    // 打印链表
    public void print() {
        System.out.print(this.val);
        if (this.next != null) {
            System.out.print("-->");
            this.next.print();
        }
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }


}
