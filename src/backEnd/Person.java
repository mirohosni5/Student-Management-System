package backEnd;

public class Person {
    protected String Fullname;
    protected int age;
    protected String gender;

    public Person(String Fullname, int age, String gender) {
        this.Fullname = Fullname;
        this.age = age;
        this.gender = gender;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


}
