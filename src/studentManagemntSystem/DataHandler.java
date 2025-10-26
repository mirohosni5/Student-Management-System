package studentManagemntSystem;
import java.io.*;
import java.util.*;
public abstract class DataHandler {
    public final ArrayList<Object> students = new ArrayList<>();

    public abstract Object parseli(String line);

    public abstract String toline(Object obj);

    public abstract String keyoff(Object obj);
    public void saveToFile(){
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Object obj : records) {
                recordInterfaces rec = (recordInterfaces) obj;
                pw.println(rec.lineRepresentation());
            }
            System.out.println(filename + " is saved");
        } catch (IOException e) {
            System.out.println(filename + " couldn't save");
        }
    }


}
