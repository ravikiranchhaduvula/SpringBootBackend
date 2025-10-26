package DesignPatterns.CreationalPatterns.AbstractFactory;

class MacFactory implements UIFactory {
    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public ScrollBar createScrollBar() {
        return new MacScrollbar();
    }
}
