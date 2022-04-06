package com.example.mytest.common.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 双指针法解决数组删除重复元素的问题
 * https://mp.weixin.qq.com/s/Z-oYzx9O1pjiym6HtKqGIQ
 *
 * @Author zhangchao
 * @Date 2022/3/25
 */
public class RepeatEle {

    //    给定 nums = [0,0,1,1,1,2,2,3,3,4],
//    函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
//    思路：双指针的应用，选择两个指针，i,j   i为慢指针，j为快指针，当nums[i]=nums[j]时,j++,跳过重复项，
//    当nums[i]!=nums[j]时，说明目前不是重复项，则i++继续之后的检测，直到j为数组的末尾为止。

    // ①定义fast为1，slow为0，这里的fast和slow代表的是数组的下标
// ②若fast和slow内容相等，让fast向后走一步，再判断，如果一直相等，fast就一直向后走，直到slow和fast的内容不相等，当两个内容不相等时，slow和fast中间那些元素其实都是不要的重复的，所以此时让slow向后走一步，再将fast的值赋值给slow,fast++判断下一个元素
// ③当整个循环结束后，返回此时数组的元素个数，让slow+1即是当前删除掉重复元素后的数组长度
    public static void main(String[] args) {
        int[] arr = new int[]{0, 0, 1, 1, 1, 2, 5, 3, 3, 4};
//        int[] arr = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        List<Integer> list = new ArrayList<>();
        int fast = 1;
        int slow = 0;
        while (fast < arr.length) {
            if (arr[fast] == arr[slow]) {
                fast++;
            } else {
                arr[slow] = arr[fast];
                System.out.println(arr[slow] + "---" + arr[fast]);
                list.add(arr[fast]);
                fast++;
            }
        }
        System.out.println(list.toString());
    }
}
