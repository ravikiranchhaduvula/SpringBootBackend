package DesignPatterns.BehaviouralPatterns.ObserverPattern;

import java.util.ArrayList;
import java.util.List;

// Observer interface
interface Observer {
    void update(float temperature);
}

// Concrete observers
class DispayDevice implements Observer {

    String name;
    public DispayDevice(String name) {
      this.name = name;
    }

    @Override
    public void update(float temperature) {
        System.out.println("Temperature on Device: " + name +"is: "+temperature);
    }
}

class MobileDevice implements  Observer {
    @Override
    public void update(float temperature) {
        System.out.println("Temperature on Mobile is: "+temperature);
    }
}

//Subject interface
interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers();
}

// Concrete subject
class WeatherStn implements Subject {

    private float temperature;
    private List<Observer> observerList;

    public WeatherStn() {
        observerList = new ArrayList<>();
    }

    @Override
    public void attach(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observerList.remove(observer);
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
        notifyObservers();
    }

    @Override
    public void notifyObservers() {
       for(Observer observer: observerList) {
           observer.update(temperature); // Runtime polymorphism
       }
    }
}
public class ObserverPattern {
    public static void main(String[] args) {
        // Create a Publisher
        WeatherStn weatherStn = new WeatherStn();
        // Create Subscribers
        DispayDevice dispayDevice = new DispayDevice("SamsungLCD");
        MobileDevice mobileDevice = new MobileDevice();

        //Attach Subscribers
        weatherStn.attach(dispayDevice);
        weatherStn.attach(mobileDevice);

        //Set Temperature
        weatherStn.setTemperature(25);

        weatherStn.detach(mobileDevice);

        weatherStn.setTemperature(50);
    }
}
