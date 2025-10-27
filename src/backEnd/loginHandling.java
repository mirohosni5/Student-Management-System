package backEnd;

public class LoginHandling {
    protected final AdminData adminData = new AdminData();
    protected final StudentAccountData studentAccData = new StudentAccountData();

    protected Admin currentAdmin;
    protected StudentAccount currentStudent;

    public void loadAdmins(String filename) {
        adminData.loadFromFile(filename); }
    public void saveAdmins(String filename) {
        adminData.saveToFile(filename); }

    public void loadStudentAcc(String filename) {
        studentAccData.loadFromFile(filename); }
    public void saveStudentAcc(String filename) {
        studentAccData.saveToFile(filename); }

    //the admin login part
    public boolean loginAdmin(String username, String password) {
        Admin a = adminData.find(username);
        if (a == null)
            return false;
        if (!a.getPassword().equals(password))
            return false;
        currentAdmin = a;
        currentStudent = null; //if any student is still loged in log him out
        return true;
    }

    //the student login part
    public boolean loginStudent(int studentId, String password) {
        StudentAccount acc = studentAccData.find(studentId);
        if (acc == null)
            return false;
        if (!acc.getPassword().equals(password))
            return false;
        currentStudent = acc;
        currentAdmin = null; // if any admin is logged in log him out
        return true;
    }

    public void logout() {
        currentAdmin = null;
        currentStudent = null;
    }


    public boolean isAdminLogged()   { return currentAdmin != null; }
    public boolean isStudentLogged() { return currentStudent != null; }

    public Admin currentAdmin()         { return currentAdmin; }
    public StudentAccount currentStudent() { return currentStudent; }

}
