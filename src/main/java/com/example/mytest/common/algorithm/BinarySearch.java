package com.example.mytest.common.algorithm;

/**
 * @Author zhangchao
 * @Date 2022/3/26
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        int target = 3;

        int index = getTargetByBinarySearch(arr, target);
        System.out.println(index);
    }

    private static int getTargetByBinarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (target > arr[middle]) {
                left = middle + 1;
            } else if (target < arr[middle]) {
                right = middle - 1;
            } else {
                return middle;
            }
        }
        return -1;

    }


}
