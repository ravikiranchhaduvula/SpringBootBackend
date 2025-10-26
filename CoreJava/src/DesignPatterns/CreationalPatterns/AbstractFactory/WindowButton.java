package DesignPatterns.CreationalPatterns.AbstractFactory;

// Windows UI
class WindowButton implements Button {
    public void render() {
        System.out.println("Rendering windows Button");
    }
}
