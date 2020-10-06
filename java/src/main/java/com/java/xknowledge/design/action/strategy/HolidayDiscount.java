package com.java.xknowledge.design.action.strategy;

/**
 * 假日优惠策略实现，实现加入优惠价格计算
 */
class HolidayDiscount implements Discount {
    @Override
    public int calculate(int money) {
        System.out.println("假日9折优惠");
        Double balance = money * 0.9;
        return balance.intValue();
    }
}
