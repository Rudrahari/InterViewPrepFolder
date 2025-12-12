package DesignPatterns.StructuralPatterns;

import SolidPrinciples.S;

class Engine {
    void startEngine() {
        System.out.println("Starts Engine");
    }

    void stopEngine() {
        System.out.println("Stops Engine");
    }
}

class Steering {
    void unLockSteering() {
        System.out.println("Unlocks steering wheel");
    }

    void lockSteering() {
        System.out.println("Locks steering wheel");
    }
}

class Lights {
    void turnOnLights() {
        System.out.println("Lights on!");
    }

    void turnOffLight() {
        System.out.println("Lights off!");
    }
}

class Car {
    private final Engine engine;
    private final Steering steering;
    private final Lights lights;

    Car() {
        this.engine = new Engine();
        this.steering = new Steering();
        this.lights = new Lights();
    }

    void start() {
        engine.startEngine();
        steering.unLockSteering();
        lights.turnOnLights();
    }

    void stop() {
        engine.stopEngine();
        steering.lockSteering();
        lights.turnOffLight();
    }
}

// useful when we want to hide complex implementations
public class FacadePattern {
    public static void main(String[] args) {
        Car car = new Car();
        car.start();
        car.stop();
    }
}
