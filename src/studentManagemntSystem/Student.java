package studentManagemntSystem;

public class Student extends Person {
    private int ID;
    private  String Department;
    private double GPA;

    public Student(int ID, String name, int age, String gender, String Department, double GPA){
        super(name, age, gender);
        this.ID = ID;
        this.Department = Department;
        this.GPA = GPA;
    }

}
