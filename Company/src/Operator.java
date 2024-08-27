public class Operator implements Employee{
    private int salary;

    public Operator() {
        salary = getSalary();
    }

    @Override
    public int getMonthSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return String.valueOf(getMonthSalary()) + " руб.";
    }
}
