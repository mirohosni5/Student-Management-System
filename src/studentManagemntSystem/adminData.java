package studentManagemntSystem;

public class adminData extends DataHandler{
    @Override
    public Object parseLine(String line) {
        if (line == null || line.trim().isEmpty()) return null;

        String[] parts = line.split(",", -1);
        if (parts.length != 2) return null;

        String user = parts[0].trim();
        String pass = parts[1].trim();

        if (user.length() == 0 || pass.length() == 0) return null;

        return new Admin(user, pass);
    }

