package DesignPatterns.BehaviouralPatterns.MementoPattern.Excercise1;

/**
 * Captures Internal state of the text editor
 */
public class EditorMemento {
    //Once captured state content won't be changed. Content is internal state
    // Initial State
    private String content; // Is the internal state of editor
    // (If any other state that will be initialized)

    // Intialise state using constructor
    public EditorMemento(String content) {
        this.content = content;
    }

    // Get the latest state always
    public String getContent() {
        return content;
    }
}
