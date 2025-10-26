package DesignPatterns.BehaviouralPatterns.MementoPattern.Excercise1;

public class Client {
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
        HistoryCareTaker historyCareTaker = new HistoryCareTaker(); // History / state management
        textEditor.write("Hello World");
        historyCareTaker.saveState(textEditor);

        textEditor.write("Hello Everyone");
        historyCareTaker.saveState(textEditor);

        //Problem - Undo the last write
        historyCareTaker.undo(textEditor);
        System.out.println(textEditor.getContent());
    }
}
