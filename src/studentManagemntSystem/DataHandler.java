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
        try {
            br = new BufferedReader(new FileReader(filename));
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            return;
        }

        try {
            String line;
            while ((line = br.readLine()) != null) {
                recordInterfaces rec = createRecord(line);
                if (rec != null) records.add(rec);
            }
        }
        catch (IOException e) {
            System.out.println("Error reading file");
        }

        try {
            br.close();
        }
        catch (IOException e) {
            System.out.println("Error closing file");
        }
    }


}
