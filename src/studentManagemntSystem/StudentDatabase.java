package studentManagemntSystem;
import java.io.*;
import java.util.*;

public class StudentDatabase extends DataHandler{

        private int nextId = 1; // this is for auto generating ids

        //ID,Fullname,Age,Gender,Department,GPA
        @Override
        public Object parseLine(String line) {
            if (line == null || line.trim().isEmpty()) return null;

            String[] p = line.split(",", -1); // keep empty cells if any
            if (p.length != 6) return null;

            try {
                int id        = Integer.parseInt(p[0].trim());
                String name   = p[1].trim();
                int age       = Integer.parseInt(p[2].trim());
                String gender = p[3].trim();
                String dept   = p[4].trim();
                double gpa    = Double.parseDouble(p[5].trim());

                Student s = new Student(id, name, age, gender, dept, gpa);


                if (id >= nextId)
                    nextId = id + 1;

                return s;
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        public String toLine(Object obj) {
            Student s = (Student) obj;
            return s.getID() + "," +
                    s.getFullname() + "," +
                    s.getAge() + "," +
                    s.getGender() + "," +
                    s.getDepartment() + "," +
                    s.getGPA();
        }

        @Override
        public String keyOf(Object obj) {
            Student s = (Student) obj;
            return String.valueOf(s.getID());
        }

     
        public void addStudent(Student s) {
            if (!isValid(s)) {
                System.out.println("invalid data");
                return;
            }
            if (s.getID() <= 0 || contains(String.valueOf(s.getID()))) {
                s.setID(nextId++);
            }
            add(s);
        }


        public void updateStudent(int id, Student newData) {
            if (!isValid(newData)) {
                System.out.println("not valid data");
                return;
            }
            newData.setID(id); // keep the same key
            for (int i = 0; i < students.size(); i++) {
                Student cur = (Student) students.get(i);
                if (cur.getID() == id) {
                    students.set(i, newData);
                    System.out.println("record updated " + id);
                    return;
                }
            }
            System.out.println("id isn't there");
        }


    public Student getById(int id) {
        Object rec = get(String.valueOf(id));
        if (rec == null) {
            return null;
        } else {
            return (Student) rec;
        }
    }



    public ArrayList<Student> getAllStudents() {
            ArrayList<Student> out = new ArrayList<>();
            for (Object o : students) out.add((Student) o);
            return out;
        }

    // i am search by id and name here
    public ArrayList<Student> search(String term) {
        ArrayList<Student> result = new ArrayList<>();

        if (term == null || term.trim().isEmpty()) {
            return getAllStudents();
        }

        term = term.trim().toLowerCase();
//here we are checking if it is a number so we could search by id

        boolean isNumber = true;
        for (char c : term.toCharArray()) {
            if (!Character.isDigit(c)) {
                isNumber = false;
                break;
            }
        }


        if (isNumber) {
            int id = Integer.parseInt(term);
            Student s = getById(id);
            if (s != null) result.add(s);
            return result;
        }

        //in another case we are searching by name
        for (Object obj : students) {
            Student s = (Student) obj;
            String name = s.getFullname();
            if (name != null && name.toLowerCase().contains(term)) {
                result.add(s);
            }
        }

        return result;
    }

    private boolean isValid(Student s) {
        if (s == null)
            return false;

        if (s.getFullname() == null)
            return false;
        if (s.getFullname().trim().isEmpty())
            return false;

        if (s.getDepartment() == null)
            return false;
        if (s.getDepartment().trim().isEmpty())
            return false;

        if (s.getAge() < 15 || s.getAge() > 100)
            return false;

        String g = s.getGender();
        if (g == null)
            return false;
        g = g.trim();
        if (!g.equalsIgnoreCase("Male") && !g.equalsIgnoreCase("Female"))
            return false;

        double gpa = s.getGPA();
        if (Double.isNaN(gpa))
            return false;
        if (gpa < 0.0 || gpa > 4.0)
            return false;

        return true;
    }

}


