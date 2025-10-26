package DesignPatterns.CreationalPatterns.AbstractFactory;

class WindowsFactory implements UIFactory {

    @Override
    public Button createButton() {
        return new WindowButton();
    }

    @Override
    public ScrollBar createScrollBar() {
        return new WindowScrollbar();
    }
}
