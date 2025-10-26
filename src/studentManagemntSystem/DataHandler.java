package studentManagemntSystem;
import java.io.*;

public abstract class DataHandler {

    public abstract void saveToFile(String filename);

    public abstract void loadFromFile(String filename);
}
