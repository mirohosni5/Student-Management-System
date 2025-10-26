package studentManagemntSystem;

import java.io.*;
import java.util.*;

public abstract class DataHandler {
    
    protected final ArrayList<Object> students = new ArrayList<>();

    
    public abstract Object parseLine(String line);


    public abstract String toLine(Object obj);

    // return the unique key (e.g., Student ID as String)
    public abstract String keyOf(Object obj);

    // --------- file I/O ----------
    public void saveToFile(String filename){
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Object obj : students) {
                pw.println(toLine(obj));
            }
            System.out.println(filename + " saved");
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename) {
        students.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                Object obj = parseLine(line);
                if (obj != null) students.add(obj);
            }
            System.out.println(filename + " loaded");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error reading: " + e.getMessage());
        }
    }

    // --------- basic CRUD ----------
    public Object get(String key) {
        for (Object obj : students) {
            if (keyOf(obj).equalsIgnoreCase(key)) return obj;
        }
        return null;
    }

    public ArrayList<Object> getAll() {
        return new ArrayList<>(students);
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public void add(Object obj) {
        if (obj == null) return;
        String key = keyOf(obj);
        if (key == null || key.isEmpty()) {
            System.out.println("empty id");
            return;
        }
        if (contains(key)) {
            System.out.println("id exists");
            return;
        }
        students.add(obj);
        System.out.println("record added " + key);
    }

    // your preferred delete style
    public void deleteRecord(String key) {
        Object target = get(key);
        if (target == null) {
            System.out.println("id isn't there");
            return;
        }
        students.remove(target);
        System.out.println("record deleted " + key);
    }

    // (optional) simple update that keeps the same key
    public void update(String key, Object newData) {
        if (newData == null) {
            System.out.println("invalid record");
            return;
        }
        for (int i = 0; i < students.size(); i++) {
            Object cur = students.get(i);
            if (keyOf(cur).equalsIgnoreCase(key)) {
                // force the same key to stay (ignore any change in newData's key)
                students.set(i, newData);
                System.out.println("record updated " + key);
                return;
            }
        }
        System.out.println("id isn't there");
    }
}
