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
        File br = new File(filename);
        if (!br.exists()) {
            System.out.println("No file yet.");
            return;
        }
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Object obj = parseli(line);
                records.add(obj);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading: " + e.getMessage());
        }
    }


}
