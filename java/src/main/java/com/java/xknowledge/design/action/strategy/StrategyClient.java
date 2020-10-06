package com.java.xknowledge.design.action.strategy;

import com.java.xknowledge.design.action.strategy.fruit.Apple;
import com.java.xknowledge.design.action.strategy.fruit.Orange;

/**
 * 策略模式
 * 参考：享学《设计模式-策略模式》
 */
class StrategyClient {
    public static void main(String[] args) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addFruit(new Apple());
        shoppingCart.addFruit(new Orange());
        shoppingCart.setDiscount(new FullDiscount());    //执行满减优惠
        shoppingCart.submitOrder();

        shoppingCart.setDiscount(new HolidayDiscount());  //执行假日折扣优惠
        shoppingCart.submitOrder();
    }
}
