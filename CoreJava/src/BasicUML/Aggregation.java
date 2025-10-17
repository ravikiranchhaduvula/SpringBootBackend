package BasicUML;

import java.util.Arrays;
import java.util.List;

class Professor {
    private String name;

    public Professor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Department {
    private String name;
    private List<Professor> professors;

    public Department(String name, List<Professor> professorList) {
        this.name = name;
        this.professors = professorList;
    }

    public void showProfessors() {
        System.out.println("Department: " + name);

        for(Professor prof: professors) {
            System.out.println(prof.getName());
        }
    }
}
public class Aggregation {
    public static void main(String[] args) {
        Professor prof1 = new Professor("John");
        Professor prof2 = new Professor("Sita");
        List<Professor> professors = Arrays.asList(prof1, prof2);
        //Aggregation department has professors but professors exists independently
        Department department = new Department("IT", professors);
        department.showProfessors();
    }
}
