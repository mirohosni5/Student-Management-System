package studentManagemntSystem;

public class adminData extends DataHandler{
    @Override
    public Object parseLine(String line) {
        if (line == null || line.trim().isEmpty()) return null;

        String[] parts = line.split(",", -1);
        if (parts.length != 2)
            return null;

        String user = parts[0].trim();
        String pass = parts[1].trim();

        if (user.length() == 0 || pass.length() == 0)
            return null;

        return new Admin(user, pass);}
        @Override
        public String toLine(Object obj) {
            Admin a = (Admin) obj; // casting
            return a.getUsername() + "," + a.getPassword();
        }

        @Override
        public String keyOf(Object obj) {
            Admin a = (Admin) obj;
            return a.getUsername();
        }

        public Admin getUsername(String username) {
            Object o = get(username);
            if (o == null) return null;
            return (Admin) o;
        }
    }


