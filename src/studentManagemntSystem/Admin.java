package studentManagemntSystem;

public class Admin {

    protected String username;
    protected String password;

public Admin(String username, String password){
    this.username=username;
    this.password=password;
}

    public String getPassword() {
        return password;
    }
}
