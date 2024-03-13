package swc2333;

import java.io.*;
import java.util.*;

class Employee {
    String name;
    double baseSalary;
    int yearsOfService;

    Employee(String name, double baseSalary, int yearsOfService) {
        this.name = name;
        this.baseSalary = baseSalary;
        this.yearsOfService = yearsOfService;
    }

    double calculateAnnualSalary() {
        return baseSalary + (baseSalary * 0.05 * yearsOfService);
    }
}

public class DemoEmployeeSalaryProgram {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\User\\Downloads\\employeeSalaries.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                double baseSalary = Double.parseDouble(parts[1]);
                int yearsOfService = Integer.parseInt(parts[2]);
                employees.add(new Employee(name, baseSalary, yearsOfService));
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        Employee topPerformer = null;
        Employee leastExperienced = null;
        for (Employee employee : employees) {
            if (topPerformer == null || employee.calculateAnnualSalary() > topPerformer.calculateAnnualSalary()) {
                topPerformer = employee;
            }
            if (leastExperienced == null || employee.yearsOfService < leastExperienced.yearsOfService) {
                leastExperienced = employee;
            }
        }

        try (PrintWriter DataEmployees = new PrintWriter(new FileWriter("C:\\Users\\User\\Downloads\\employeeData.txt"))) {
            DataEmployees.printf("%-30s , %-15s , %s%n", "Employee's Name", "Annual Salary", "Years of Service");
            DataEmployees.println("----------------------------------------------------------------------");
            for (Employee employee : employees) {
                DataEmployees.printf("%-30s , %-15.2f , %d%n", employee.name, employee.calculateAnnualSalary(), employee.yearsOfService);
            }
            DataEmployees.println("----------------------------------------------------------------------");
            DataEmployees.println("Top Performer: " + topPerformer.name);
            DataEmployees.println("Least Years of Service: " + leastExperienced.name);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}
