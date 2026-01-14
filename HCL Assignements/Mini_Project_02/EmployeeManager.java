package Mini_Project_02;

import java.io.*;
import java.util.*;

public class EmployeeManager {
    private static List<Employee> list = new ArrayList<>();
    private static final String FILE = "employees.txt";

    static {
        load();
    }

    static void load() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE));
            String line;
            while ((line = br.readLine()) != null) {
                list.add(Employee.fromString(line));
            }
        } catch (Exception e) {}
    }

    static void save() {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(FILE));
            for (Employee e : list)
                pw.println(e);
            pw.close();
        } catch (Exception e) {}
    }

    static void add(Employee e) throws Exception {
        for (Employee emp : list)
            if (emp.getId() == e.getId())
                throw new Exception("Employee ID must be unique");

        list.add(e);
        save();
    }

    static void display() {
        for (Employee e : list)
            System.out.println(e);
    }

    static Employee search(int id) {
        for (Employee e : list)
            if (e.getId() == id) return e;
        return null;
    }

    static void delete(int id) throws Exception {
        Employee e = search(id);
        if (e == null)
            throw new Exception("Employee not found");
        list.remove(e);
        save();
    }

    static void updateSalary(int id, double sal) throws Exception {
        if (sal <= 0)
            throw new InvalidSalaryException("Salary must be positive");

        Employee e = search(id);
        if (e == null)
            throw new Exception("Employee not found");

        e.setSalary(sal);
        save();
    }

    static void sort() {
        list.sort((a, b) -> Double.compare(b.getSalary(), a.getSalary()));
        display();
    }

    static void displayDepartments() {
        Set<String> dept = new HashSet<>();
        for (Employee e : list)
            dept.add(e.getDepartment());

        for (String d : dept)
            System.out.println(d);
    }
}

