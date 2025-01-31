package com.java.xknowledge.se.collection.set.treeset;

import java.util.TreeSet;

/**
 * TreeSet实践：保证元素存储后的顺序
 * 运行：
 * [-9, 2, 5, 10]//从小到大的顺序排序
 * -9
 * 10
 * [-9, 2]
 * [5, 10]
 * [2]
 *
 * Process finished with exit code 0
 * 参考：
 * 《疯狂Java讲义》
 */
class TreeSetTest {
    public static void main(String[] args) {
        TreeSet nums = new TreeSet();
        //向TreeSet中添加四个Integer对象
        nums.add(5);
        nums.add(2);
        nums.add(10);
        nums.add(-9);

        //输出集合元素，看到集合元素已经处于排序状态
        System.out.println(nums);

        //输出集合里的第一个元素
        System.out.println(nums.first());
        //输出集合里的最后一个元素
        System.out.println(nums.last());

        //返回小于4的子集，不包含4
        System.out.println(nums.headSet(4));
        //返回大于5的子集，如果Set中包含5，子集中还包含5
        System.out.println(nums.tailSet(5));
        //返回大于等于-3，小于4的子集。
        System.out.println(nums.subSet(-3, 4));
    }
}
