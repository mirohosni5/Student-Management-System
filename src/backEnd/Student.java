package backEnd;

public class Student extends Person {
    private int ID;
    private String Department;
    private double GPA;

    public Student(int ID, String name, int age, String gender, String Department, double GPA) {
        super(name, age, gender);
        this.ID = ID;
        this.Department = Department;
        this.GPA = GPA;}

    public int getID() {return ID;}

    public void setID(int ID) {this.ID = ID;}

    public double getGPA() {return GPA;}

    public void setGPA(double GPA) {this.GPA = GPA;}

    public String getDepartment() {return Department;}

    public void setDepartment(String department) {Department = department;}


}
