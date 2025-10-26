package DesignPatterns.BehaviouralPatterns.CommandPattern;

public class WithoutCommandPattern {
    public static void main(String[] args) {
      TextEditor textEditor = new TextEditor();
      //Tightly coupled with editor
      BoldButton boldButton = new BoldButton(textEditor);
      boldButton.click();
      ItalicButton italicButton = new ItalicButton(textEditor);
      italicButton.click();
    }
}
