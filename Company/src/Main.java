
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Company company1 = new Company(5_000_000);
        for (int i = 0; i < 180; i++) {
            company1.hire(new Operator());
        }
        for (int i = 0; i < 80; i++) {
            company1.hire(new Manager(company1));
        }
        for (int i = 0; i < 10; i++) {
            company1.hire(new TopManager(company1));
        }
        List<Employee> topSalary = new ArrayList<>(company1.getTopSalaryStaff(15));
        System.out.println("Список самых высоких зарплат: ");
        for (Employee employee : topSalary) {
            System.out.println(employee);
        }

        List<Employee> lowSalary = new ArrayList<>(company1.getLowestSalaryStaff(30));
        System.out.println("Список самых низких зарплат: ");
        for (Employee employee : lowSalary) {
            System.out.println(employee);
        }

        Collections.shuffle(company1.listEmloyee);
        List<Employee> hireEmp = new ArrayList<>(company1.listEmloyee.subList(0, company1.listEmloyee.size()/2));
        for (int i = 0; i < company1.listEmloyee.size() / 2; i++) {
            company1.fire(company1.listEmloyee.get(i));
        }

        System.out.println("Список самых высоких зарплат: ");
        topSalary = new ArrayList<>(company1.getTopSalaryStaff(15));
        for (Employee employee : topSalary) {
            System.out.println(employee);
        }

        System.out.println("Список самых низких зарплат: ");
        lowSalary = new ArrayList<>(company1.getLowestSalaryStaff(30));
        for (Employee employee : lowSalary) {
            System.out.println(employee);
        }
    }
}