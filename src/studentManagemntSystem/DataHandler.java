package studentManagemntSystem;
import java.io.*;
import java.util.*;
public abstract class DataHandler {

    public abstract void saveToFile(String filename);

    public abstract void loadFromFile(String filename);
}
