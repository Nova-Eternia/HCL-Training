package Mini_Project_02;

import java.util.*;

public class MainApp {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.print("Username: ");
        String u = sc.next();
        System.out.print("Password: ");
        String p = sc.next();

        if (!Login.validate(u, p)) {
            System.out.println("Invalid Login");
            return;
        }

        while (true) {
            System.out.println("\n1 Add Employee");
            System.out.println("2 Display All");
            System.out.println("3 Search by ID");
            System.out.println("4 Update Salary");
            System.out.println("5 Delete");
            System.out.println("6 Display Sorted");
            System.out.println("7 Display Departments");
            System.out.println("8 Exit");

            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            try {
                switch (ch) {
                    case 1:
                        System.out.print("ID: ");
                        int id = sc.nextInt();
                        System.out.print("Name: ");
                        String name = sc.next();
                        System.out.print("Department: ");
                        String dep = sc.next();
                        if (dep.isEmpty())
                            throw new EmptyDepartmentException("Department cannot be empty");

                        System.out.print("Salary: ");
                        double sal = sc.nextDouble();
                        if (sal <= 0)
                            throw new InvalidSalaryException("Salary must be positive");

                        EmployeeManager.add(new Employee(id, name, dep, sal));
                        System.out.println("Employee Added");
                        break;

                    case 2:
                        EmployeeManager.display();
                        break;

                    case 3:
                        System.out.print("ID: ");
                        Employee e = EmployeeManager.search(sc.nextInt());
                        System.out.println(e == null ? "Not Found" : e);
                        break;

                    case 4:
                        System.out.print("ID: ");
                        int eid = sc.nextInt();
                        System.out.print("New Salary: ");
                        EmployeeManager.updateSalary(eid, sc.nextDouble());
                        System.out.println("Salary Updated");
                        break;

                    case 5:
                        System.out.print("ID: ");
                        EmployeeManager.delete(sc.nextInt());
                        System.out.println("Employee Deleted");
                        break;

                    case 6:
                        EmployeeManager.sort();
                        break;

                    case 7:
                        EmployeeManager.displayDepartments();
                        break;

                    case 8:
                        System.exit(0);
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }
}

