package studentManagemntSystem;
import java.io.*;
import java.util.*;

public class StudentDatabase extends DataHandler{

        private int nextId = 1; // used to auto-generate IDs when needed

        // CSV: ID,Fullname,Age,Gender,Department,GPA
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

                // keep nextId ahead of the biggest ID we’ve seen
                if (id >= nextId) nextId = id + 1;

                return s; // parent stores it as Object
            } catch (Exception e) {
                return null; // skip broken line quietly
            }
        }

        @Override
        public String toLine(Object obj) {
            Student s = (Student) obj; // casting (your style)
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

        // ====== Nice typed helpers (simple & friendly) ======

        // Add with basic validation + auto-ID if 0 or duplicate
        public void addStudent(Student s) {
            if (!isValid(s)) {
                System.out.println("invalid data");
                return;
            }
            if (s.getID() <= 0 || contains(String.valueOf(s.getID()))) {
                s.setID(nextId++); // auto ID
            }
            add(s); // uses parent prints: "record added <id>" or "id exists"
        }

        // Update by ID (keeps same ID)
        public void updateStudent(int id, Student newData) {
            if (!isValid(newData)) {
                System.out.println("invalid data");
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

        // Get one by ID (typed)
        public Student getById(int id) {
            Object rec = get(String.valueOf(id));
            return rec == null ? null : (Student) rec;
        }

        // Get all (typed)
        public ArrayList<Student> getAllStudents() {
            ArrayList<Student> out = new ArrayList<>();
            for (Object o : students) out.add((Student) o);
            return out;
        }

        // Search by exact ID or by name substring (case-insensitive)
        public ArrayList<Student> search(String term) {
            ArrayList<Student> list = new ArrayList<>();
            if (term == null || term.trim().isEmpty()) return getAllStudents();

            String q = term.trim().toLowerCase();

            // try ID first
            try {
                int id = Integer.parseInt(q);
                Student s = getById(id);
                if (s != null) list.add(s);
                return list;
            } catch (NumberFormatException ignore) {}

            // fallback: name contains
            for (Object o : students) {
                Student s = (Student) o;
                String name = s.getFullname() == null ? "" : s.getFullname();
                if (name.toLowerCase().contains(q)) list.add(s);
            }
            return list;
        }

        // ====== super simple validation (kept minimal) ======
        private boolean isValid(Student s) {
            if (s == null) return false;
            if (s.getFullname() == null || s.getFullname().trim().isEmpty()) return false;
            if (s.getDepartment() == null || s.getDepartment().trim().isEmpty()) return false;
            if (s.getAge() < 15 || s.getAge() > 100) return false;
            String g = s.getGender() == null ? "" : s.getGender().trim();
            if (!(g.equalsIgnoreCase("Male") || g.equalsIgnoreCase("Female"))) return false;
            if (Double.isNaN(s.getGPA()) || s.getGPA() < 0.0 || s.getGPA() > 4.0) return false;
            return true;
        }
    }


