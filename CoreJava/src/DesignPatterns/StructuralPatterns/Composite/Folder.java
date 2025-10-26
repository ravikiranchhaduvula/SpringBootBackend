package DesignPatterns.StructuralPatterns.Composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Uniformity between files and folders
 * Scalability - add Shortcut by implementing interface
 * Composition -- Add / remove components without affecting how client interacts
 */

public class Folder implements FileSystemComponent {
    private final String name;

    List<FileSystemComponent> components = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void addComponent(FileSystemComponent component) {
        components.add(component);
    }

    public void showDetails() {
        System.out.println("Folder: "+name);
        for(FileSystemComponent component: components) {
            component.showDetails();
        }
    }
}
