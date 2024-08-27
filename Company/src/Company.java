import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Company {
    private int income;
    public List<Employee> listEmloyee;

    public Company(int income) {
        listEmloyee = new ArrayList<>();
        this.income = income;
    }

    public void hire(Employee employee) {
        listEmloyee.add(employee);
    }

    public void hireAll(Collection<Employee> employes){
        listEmloyee.addAll(employes);
    }

    public void fire(Employee employee){
        listEmloyee.remove(employee);
    }

    public int getIncome(){
        return income;
    }

    public List<Employee> getTopSalaryStaff(int count) {
        List<Employee> employees = new ArrayList<>(listEmloyee);
        employees.sort(new SalaryComarator());
        count = Math.min(count, employees.size());

        return (count > 0 ? employees.subList(0, count) : new ArrayList<>());
    }

    public List<Employee> getLowestSalaryStaff(int count){
        List<Employee> employees = new ArrayList<>(listEmloyee);
        Collections.sort(employees, new SalaryComarator().reversed());
        count = Math.min(count, employees.size());

        List<Employee> lowSalary = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lowSalary.add(employees.get(i));
        }
        return (count > 0 ? lowSalary : new ArrayList<>());
    }
}
