package BasicUML;

class Teacher {
    private final String name;

    public Teacher(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void teach(Student student) {
        System.out.println(name + " Teaching " + student.getName());
    }
}

class Student {
    private final String name;

    public Student(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
public class Association {
    public static void main(String[] args) {
        Teacher teacher = new Teacher("Miss Aruna");
        Student student = new Student("Ravi");
        teacher.teach(student); // Association between teacher and student
    }
}
