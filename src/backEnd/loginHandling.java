package backEnd;

public class loginHandling {
    protected final AdminData adminData = new AdminData();
    protected final StudentAccountData studentAccData = new StudentAccountData();


    protected Admin nowAdmin;
    protected StudentAccount nowStudent;



    public void loadAdmins(String filename) {
        adminData.loadFromFile(filename); }
    public void saveAdmins(String filename) {
        adminData.saveToFile(filename); }



    public void loadStudentAcc(String filename) {
        studentAccData.loadFromFile(filename); }
    public void saveStudentAcc(String filename) {
        studentAccData.saveToFile(filename); }

    

    //getters
    public boolean isAdminLogged()   {
        return nowAdmin != null; }
    public boolean isStudentLogged() {
        return nowStudent!= null; }

    public Admin currentAdmin()         {
        return nowAdmin; }
    public StudentAccount currentStudent() {
        return nowStudent; }



    //the admin log in part
    public boolean loginAdmin(String username, String password) {
        Admin a = adminData.find(username);
        if (a == null) return false;
        if (!a.getPassword().equals(password)) return false;
        nowAdmin= a;
        nowStudent = null; //if any student is logged in log them out
        return true;
    }

    // the student log in part
    public boolean loginStudent(int studentId, String password) {
        StudentAccount acc = studentAccData.find(studentId); // typed helper
        if (acc == null) return false;
        if (!acc.getPassword().equals(password)) return false;
        nowStudent = acc;
        //if any admin is logged in log them out
        nowAdmin = null;
        return true;
    }


    public void logout() {
        nowAdmin = null;
        nowStudent = null;
    }



}
