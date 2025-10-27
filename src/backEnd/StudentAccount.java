package backEnd;

public class StudentAccount {
    private int studentId;
    private String password;

    public StudentAccount(int studentId, String password) {
        this.studentId = studentId;
        this.password = password;
    }

    public int getStudentId() { return studentId; }
    public String getPassword() { return password;}

}
