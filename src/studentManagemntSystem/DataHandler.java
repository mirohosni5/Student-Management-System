package studentManagemntSystem;
import java.io.*;
import java.util.*;
public abstract class DataHandler {
    public final ArrayList<Object> students = new ArrayList<>();

    public abstract Object parseli(String line);

    public abstract String toline(Object obj);

    public abstract String keyoff(Object obj);
    public void saveToFile(String filename){
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Object obj : records) pw.println(toline(obj));
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }
    public void loadFromFile(String filename) {
        records.clear();
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(filename));
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            return;
        }

        try  {
            String line;
            while ((line = br.readLine()) != null) {
                Object obj = parseli(line);
                if (obj != null) records.add(obj);
            }
        } catch (IOException e) {
            System.out.println("Error reading: " + e.getMessage());
        }

        try {
            br.close();
        }
        catch (IOException e) {
            System.out.println("Error closing file");
        }
    }
    public void saveToFile(String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Object obj : records) pw.println(toLine(obj));
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

}
