package com.java.xknowledge.design.action.strategy;

/**
 * 满减优惠策略实现，实现满减优惠价格计算
 */
class FullDiscount implements Discount {
    @Override
    public int calculate(int money) {
        if (money > 200) {
            System.out.println("满200减免20元");
            money -= 20;
        }
        return money;
    }
}
