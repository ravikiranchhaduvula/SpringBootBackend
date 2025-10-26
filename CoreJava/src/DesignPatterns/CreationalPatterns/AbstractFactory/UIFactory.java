package DesignPatterns.CreationalPatterns.AbstractFactory;

// Abstract Factory Interface
interface UIFactory {
    Button createButton();

    ScrollBar createScrollBar();
}
