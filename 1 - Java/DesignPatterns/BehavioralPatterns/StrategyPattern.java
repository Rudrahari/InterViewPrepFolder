package DesignPatterns.BehavioralPatterns;

import java.util.HashMap;
import java.util.Map;

interface Strategy {
    double apply(double amount);
}

class NoDiscountStrategy implements Strategy {

    @Override
    public double apply(double amount) {
        return amount;
    }
}

class FlatDiscountStrategy implements Strategy {

    @Override
    public double apply(double amount) {
        System.out.println("Applying flat 10 discount for cart with more than 50");
        return amount > 50 ? amount - 10 : amount;
    }
}

class PercentageDiscountStrategy implements Strategy {

    @Override
    public double apply(double amount) {
        System.out.println("Applying twenty percent discount");
        return amount * 0.8;
    }
}

class Cart {
    Map<String, Double> itemsList;
    Strategy strategy;
    double totalCartValue;

    Cart() {
        this.strategy = new NoDiscountStrategy();
        this.totalCartValue = 0.0;
        itemsList = new HashMap<>();
    }

    Cart(Strategy strategy) {
        this.strategy = strategy;
        this.totalCartValue = 0.0;
        itemsList = new HashMap<>();
    }

    void addToCart(String item, double cost) {
        itemsList.put(item, cost);
        totalCartValue += cost;
    }

    void removeFromCart(String item) {
        if (itemsList.containsKey(item)) {
            totalCartValue -= itemsList.remove(item);
        }
    }

    void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    void checkCartValueAfterDiscount() {
        System.out.println("Total: " + strategy.apply(totalCartValue));
    }

}

public class StrategyPattern {
    public static void main(String[] args) {
        Strategy flatDiscount = new FlatDiscountStrategy();
        Strategy percentageDiscount = new PercentageDiscountStrategy();

        Cart cart = new Cart();

        cart.addToCart("itemA", 200);
        cart.addToCart("itemB", 100);
        cart.removeFromCart("itemA");
        cart.checkCartValueAfterDiscount();

        Cart discountCart = new Cart(percentageDiscount);

        discountCart.addToCart("itemAA", 200);
        discountCart.addToCart("itemBB", 100);

        discountCart.checkCartValueAfterDiscount();

        discountCart.setStrategy(flatDiscount);
        discountCart.checkCartValueAfterDiscount();
    }
}
