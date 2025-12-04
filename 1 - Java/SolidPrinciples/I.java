package SolidPrinciples;

// ISP -> Interface Segregation Principle
// entities shouldn't be forced to implement methods which they don't need
interface CarInterface {
    void start();

    void stop();

    void petrolTankCapacity();

    void dieselTankCapacity();

    void lithiumIonBatteryLifeTime();
}

// just from the below example we knew we are violating ISP
class PetrolCarClass implements CarInterface {

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void petrolTankCapacity() {

    }

    // un supported operation
    @Override
    public void dieselTankCapacity() {
        try {
            throw new Exception();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // un supported operation
    @Override
    public void lithiumIonBatteryLifeTime() {
        try {
            throw new Exception();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

public class I {

}

// Good way -> split Car interface to multiple interfaces
interface Car {
    void start();

    void stop();
}

interface PetrolEngine {
    void petrolEngineTankCapacity();
}

interface DieselEngine {
    void dieselEngineTankCapacity();
}

interface LithiumIonBattery {
    void lithiumIonBatteryLifeTime();
}

class PetrolCar implements Car, PetrolEngine {

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void petrolEngineTankCapacity() {

    }
}

class DieselCar implements Car, DieselEngine {

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void dieselEngineTankCapacity() {

    }
}

class HybridCar implements Car, PetrolEngine, LithiumIonBattery {

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void lithiumIonBatteryLifeTime() {

    }

    @Override
    public void petrolEngineTankCapacity() {

    }
}

class ElectricCar implements Car, LithiumIonBattery {

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void lithiumIonBatteryLifeTime() {

    }
}

