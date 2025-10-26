package studentManagemntSystem;
import java.io.*;
import java.util.*;
public abstract class DataHandler {
public final ArrayList<Object> students = new ArrayList<>();
public abstract Object parseli(String line);
public abstract String toline(Object obj);
public abstract String keyoff(Object obj);


    public abstract void saveToFile(String filename);

    public abstract void loadFromFile(String filename);
}
