package DesignPatterns.BehaviouralPatterns.MementoPattern.Excercise1;

/**
 * A text editor where the user can undo changes such as text addition, do formatting
 * The Editor stores snapshots of it's state (text content) after each change,
 * Enabling the user to revert to previous states.
 */
public class TextEditor {
    private String content;

    // Set the content -- Stateless
    public void write(String text) {
        this.content = text;
    }

    // Get the content -- Stateless
    public String getContent() {
        return content;
    }

    // Save Current state of editor
    public EditorMemento saveContent() {
        return new EditorMemento(content); //Headline, code snippet
    }

    // Restore takes current state and update the content with current state instead of original
    public void restore(EditorMemento memento) {
        content = memento.getContent();
    }
}
