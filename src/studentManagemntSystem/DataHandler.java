package studentManagemntSystem;
import java.io.*;
import java.util.*;
public abstract class DataHandler {
public final ArrayList<Object> students = new ArrayList<>();
public abstract Object parseli(Stringline);

    public abstract void saveToFile(String filename);

    public abstract void loadFromFile(String filename);
}
