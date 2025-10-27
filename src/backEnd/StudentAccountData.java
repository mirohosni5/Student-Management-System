package backEnd;

public class StudentAccountData extends DataHandler {
    @Override
    public Object parseLine(String line) {
        if (line == null) return null;
        line = line.trim();
        if (line.length() == 0) return null;

        String[] parts = line.split(",", -1);
        if (parts.length != 2) return null;

        String idText = parts[0].trim();
        String pass = parts[1].trim();

        if (idText.length() == 0) return null;
        if (pass.length() == 0) return null;

        for (int i = 0; i < idText.length(); i++) {
            char c = idText.charAt(i);
            if (c < '0' || c > '9') return null;
        }

        int id = Integer.parseInt(idText);
        return new StudentAccount(id, pass);
    }

    @Override
    public String toLine(Object obj) {
        StudentAccount acc = (StudentAccount) obj;
        return acc.getStudentId() + "," + acc.getPassword();
    }

    @Override
    public String keyOf(Object obj) {
        StudentAccount acc = (StudentAccount) obj;
        return String.valueOf(acc.getStudentId());
    }

    public StudentAccount find(int id) {
        Object obj = get(String.valueOf(id));
        if (obj == null) return null;
        return (StudentAccount) obj;
    }

}
