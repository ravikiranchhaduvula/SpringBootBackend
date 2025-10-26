package DesignPatterns.BehaviouralPatterns.CommandPattern;

interface Command {
    void execute();
}

class BoldCommand implements Command {

    private final TextEditor2 textEditor2;

    public BoldCommand(TextEditor2 textEditor2) {
     this.textEditor2 = textEditor2;
    }

    @Override
    public void execute() {
        textEditor2.boldText();
    }
}

class ChangeColor implements Command {

    private final TextEditor2 textEditor2;

    public ChangeColor(TextEditor2 textEditor2) {
        this.textEditor2 = textEditor2;
    }

    @Override
    public void execute() {
        textEditor2.changeColor();
    }
}

class Button {
    private Command command; // Instead of Editor

    public void setCommand(Command command) { // Bold, Italic
        this.command = command;
    }

    public void click() {
        command.execute();
    }
}

class TextEditor2 {
    public void boldText() {
        System.out.println("Text has been bolded.");
    }

    public void italicizeText() {
        System.out.println("Text has been italicised.");
    }

    public void underlineText() {
        System.out.println("Text has been underlined.");
    }

    public void changeColor() {
        System.out.println("Button Color has been changed.");
    }
}

public class WithCommandPattern {
    public static void main(String[] args) {
        // Decoupling --> One button can do any type of actions
        //Completely decoupled from text editor
      TextEditor2 textEditor2 = new TextEditor2();
      Button button = new Button();
      button.setCommand(new BoldCommand(textEditor2)); // Runtime
      button.click();
      button.setCommand(new ChangeColor(textEditor2));
      button.click();
    }
}
