package DesignPatterns.BehaviouralPatterns.ObserverPattern;


class DisplayDevice {
 public void showTemperature(float temperature) {
     System.out.println("The Current Temperature: "+temperature+" C");
 }
}

class WeatherStation {
    private float temperature;
    private final DisplayDevice displayDevice; // Can have multiple devices in future

    //What all display devices this weather station connects to
    public WeatherStation(DisplayDevice displayDevice) {
        this.displayDevice = displayDevice;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
        notifyDevice();
    }

    public void notifyDevice() {
        displayDevice.showTemperature(temperature);
    }
}
public class WithoutObserverPattern {
    public static void main(String[] args) {
        DisplayDevice displayDevice = new DisplayDevice();
        WeatherStation station = new WeatherStation(displayDevice); // Tight Coupling
        station.setTemperature(26);
        station.setTemperature(30);
    }
}
