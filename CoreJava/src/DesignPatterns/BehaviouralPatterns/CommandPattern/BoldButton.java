package DesignPatterns.BehaviouralPatterns.CommandPattern;

public class BoldButton {
    private final TextEditor textEditor;

    public BoldButton(TextEditor textEditor) {
        this.textEditor = textEditor;
    }

    public void click() {
        textEditor.boldText();
    }
}
