package com.java.xknowledge.design.structure.decorator.enjoy;

import com.java.xknowledge.design.common.bag.Bag;

public class ReinforceBagDecorator extends BagDecorator {
    public ReinforceBagDecorator(Bag bag) {
        super(bag);
    }

    public void pack() {
        super.pack();  //调用原有业务方法
        reinforce();
    }

    //加固包装
    public void reinforce() {
        System.out.println("------");
        System.out.println("加固了包装");
    }
}
