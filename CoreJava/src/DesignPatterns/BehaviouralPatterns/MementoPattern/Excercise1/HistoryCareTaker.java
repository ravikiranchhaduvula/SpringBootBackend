package DesignPatterns.BehaviouralPatterns.MementoPattern.Excercise1;

import java.util.Stack;

/** Takes care of snapshots of the TextEditor state Single responsibility to take care of state **/
public class HistoryCareTaker {
 private final Stack<EditorMemento> history = new Stack<>();

 public void saveState(TextEditor textEditor) {
   history.push(textEditor.saveContent());
 }

 public void undo(TextEditor textEditor) {
     if(!history.isEmpty()) {
         history.pop();
         textEditor.restore(history.peek()); // Pop Last element from stack
     }
 }
}
