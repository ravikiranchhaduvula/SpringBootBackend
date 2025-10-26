package DesignPatterns.BehaviouralPatterns.CommandPattern;

public class ItalicButton {
    private final TextEditor textEditor;

    public ItalicButton(TextEditor textEditor) {
        this.textEditor = textEditor;
    }

    public void click() {
        textEditor.italicizeText();
    }
}
