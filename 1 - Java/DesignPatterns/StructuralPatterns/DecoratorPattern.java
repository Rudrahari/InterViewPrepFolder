package DesignPatterns.StructuralPatterns;

interface Pizza {
    void getPizzaMenuDescription();

    int getPizzaCost();
}

class ThinCrustPizza implements Pizza {

    @Override
    public void getPizzaMenuDescription() {
        System.out.println("The pizza's crust is thin");
    }

    @Override
    public int getPizzaCost() {
        return 10;
    }
}

class StuffedCrustPizza implements Pizza {

    @Override
    public void getPizzaMenuDescription() {
        System.out.println("The pizza's crust is stuffed with cheese");
    }

    @Override
    public int getPizzaCost() {
        return 20;
    }
}

// base decorator - abstract
abstract class PizzaDecorator implements Pizza {
    Pizza pizza;

    PizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public void getPizzaMenuDescription() {
        pizza.getPizzaMenuDescription();
    }

    @Override
    public int getPizzaCost() {
        return pizza.getPizzaCost();
    }
}

// concrete decorators
class ExtraCheeseDecorator extends PizzaDecorator {

    ExtraCheeseDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public void getPizzaMenuDescription() {
        super.getPizzaMenuDescription();
        System.out.println("With extra cheese");
    }

    @Override
    public int getPizzaCost() {
        return super.getPizzaCost() + 15;
    }
}

class PepperoniDecorator extends PizzaDecorator {

    PepperoniDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public void getPizzaMenuDescription() {
        super.getPizzaMenuDescription();
        System.out.println("With pepperoni");
    }

    @Override
    public int getPizzaCost() {
        return super.getPizzaCost() + 20;
    }
}

public class DecoratorPattern {
    public static void main(String[] args) {
        Pizza pizza = new ThinCrustPizza();
        pizza = new ExtraCheeseDecorator(pizza);
        pizza.getPizzaMenuDescription();
        System.out.println(pizza.getPizzaCost());

        pizza = new PepperoniDecorator(pizza);
        pizza.getPizzaMenuDescription();
        System.out.println(pizza.getPizzaCost());

        Pizza anotherPizza=new StuffedCrustPizza();
        anotherPizza=new PepperoniDecorator(anotherPizza);

        anotherPizza.getPizzaMenuDescription();
        System.out.println(anotherPizza.getPizzaCost());
    }
}
