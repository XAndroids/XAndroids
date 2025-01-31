package com.java.xknowledge.design.create.factory.headfirst.simple.pizza;

public class VeggiePizza extends Pizza {
	public VeggiePizza() {
		name = "Veggie com.java.xknowledge.design.create.factory.headfirst.builder.pizza.Pizza";
		dough = "Crust";
		sauce = "Marinara sauce";
		toppings.add("Shredded mozzarella");
		toppings.add("Grated parmesan");
		toppings.add("Diced onion");
		toppings.add("Sliced mushrooms");
		toppings.add("Sliced red pepper");
		toppings.add("Sliced black olives");
	}
}
