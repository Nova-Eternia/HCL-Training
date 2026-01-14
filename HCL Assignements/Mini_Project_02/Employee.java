package Mini_Project_02;

import java.io.Serializable;

public class Employee implements Serializable {
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    public void setSalary(double salary) { this.salary = salary; }

    public String toString() {
        return id + "," + name + "," + department + "," + salary;
    }

    public static Employee fromString(String line) {
        String[] p = line.split(",");
        return new Employee(Integer.parseInt(p[0]), p[1], p[2], Double.parseDouble(p[3]));
    }
}
