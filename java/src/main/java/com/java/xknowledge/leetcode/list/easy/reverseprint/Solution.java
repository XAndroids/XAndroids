package com.java.xknowledge.leetcode.list.easy.reverseprint;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指 Offer 06. 从尾到头打印链表
 * 链接：https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/
 */
class Solution {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static int[] reversePrint(ListNode head) {
        Deque<Integer> stack = new LinkedList<>();//使用栈保存链表元素
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp.val);
            temp = temp.next;
        }

        int stackSize = stack.size();//注意：需要提前获取stack的size，不然pop后size会变化，导致数据丢失
        int[] result = new int[stackSize];
        for (int i = 0; i < stackSize; i++) {
            result[i] = stack.pop();//使用栈出栈返回逆序链表
        }

        return result;
    }

    public static void main(String[] args){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);

        System.out.println(Arrays.toString(Solution.reversePrint(head)));
    }
}
