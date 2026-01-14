package Mini_Project_02;

import java.io.*;
import java.util.*;

public class Login {
    public static boolean validate(String user, String pass) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("users.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p[0].equals(user) && p[1].equals(pass))
                    return true;
            }
        } catch (Exception e) {}
        return false;
    }
}
