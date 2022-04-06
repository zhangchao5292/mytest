package com.example.mytest.common.algorithm.sort;

import java.util.Arrays;

/**
 * https://www.cnblogs.com/chengxuyuanzhilu/p/6133568.html 归并排序
 *
 * @Author zhangchao
 * @Date 2022/3/24
 */
public class MergeSort {

//    工作原理
//
//（1）申请空间，大小为两个半子集大小的空间，用来存放两个半子集排序合并后的数据
//（2）设定两个指针，最初位置分别为两个半子集的起始位置
//（3）比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置
//（4）重复步骤3直到某一指针达到序列尾
//（5）将另一序列剩下的所有元素直接复制到合并序列尾

    public static int[] sort(int[] nums, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            // 左边
            sort(nums, low, mid);
            // 右边
            sort(nums, mid + 1, high);
            // 左右归并
            merge(nums, low, mid, high);
        }
        return nums;
    }

    public static void merge(int[] nums, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low;// 左指针
        int j = mid + 1;// 右指针
        int k = 0;

        // 把较小的数先移到新数组中
        while (i <= mid && j <= high) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }

        // 把左边剩余的数移入数组
        while (i <= mid) {
            temp[k++] = nums[i++];
        }

        // 把右边边剩余的数移入数组
        while (j <= high) {
            temp[k++] = nums[j++];
        }

        // 把新数组中的数覆盖nums数组
        for (int k2 = 0; k2 < temp.length; k2++) {
            nums[k2 + low] = temp[k2];
        }
    }


    // 归并排序的实现
    public static void main(String[] args) {
        int[] nums = {16, 4, 13, 25, 92, 11, 9, 3, 2, 34, 8};
        MergeSort.sort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }
}
