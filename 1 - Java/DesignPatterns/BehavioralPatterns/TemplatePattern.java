package DesignPatterns.BehavioralPatterns;

abstract class Restaurant {

    void welcomeGuestProcedure() {
        openDoorsForCustomers();
        arrangeSeatsForCustomers();
        if (provideFreeWelcomeDrinks()) {
            provideFreeDrinks();
        }
        serveFood();
        sendOffCustomers();
    }

    protected void openDoorsForCustomers() {
        System.out.println("Opening doors for customers");
    }

    protected void provideFreeDrinks() {
        System.out.println("Providing free welcome drinks");
    }

    protected boolean provideFreeWelcomeDrinks() {
        return false;
    }

    protected void sendOffCustomers() {
        System.out.println("Send off customers with smile");
    }

    protected abstract void arrangeSeatsForCustomers();

    protected abstract void serveFood();
}

class RestaurantAbc extends Restaurant {

    @Override
    protected boolean provideFreeWelcomeDrinks() {
        return true;
    }

    @Override
    protected void arrangeSeatsForCustomers() {
        System.out.println("Make arrangement based on seat availability");
    }

    @Override
    protected void serveFood() {
        System.out.println("Serve foods to customers");
    }
}

class RestaurantBcd extends Restaurant {

    @Override
    protected void arrangeSeatsForCustomers() {
        System.out.println("Arrange seats based on the number of people in a group");
    }

    @Override
    protected void serveFood() {
        System.out.println("Serve customer's food with excitement");
    }
}

public class TemplatePattern {
    public static void main(String[] args) {
        Restaurant restaurantAbc = new RestaurantAbc();

        restaurantAbc.welcomeGuestProcedure();

        Restaurant restaurantBcd = new RestaurantBcd();

        restaurantBcd.welcomeGuestProcedure();

    }
}
