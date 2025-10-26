package DesignPatterns.CreationalPatterns.AbstractFactory;


public class Main {
    private final Button button;
    private final ScrollBar scrollBar;
    public Main(UIFactory factory) {
      this.button = factory.createButton();
      this.scrollBar = factory.createScrollBar();
    }

    public void renderUI() {
        button.render();
        scrollBar.scroll();
    }
    public static void main(String[] args) {
     /*WindowButton button = new WindowButton();
     WindowScrollbar scrollbar = new WindowScrollbar(); // Can create Mac scrollbar no logical grouping

     button.render();
     scrollbar.scroll();*/
        UIFactory windowsFactory = new WindowsFactory();
        Main app = new Main(windowsFactory);
        app.renderUI();
    }
}
