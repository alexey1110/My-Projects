import java.util.Random;

public class Manager implements Employee{
    public static final double BONUS_PROCENT = 0.05;
    private int income;
    private int salary;

    private static final Random random = new Random(); // Статический Random для использования везде

    public Manager(Company company) {
        income = company.getIncome();
        salary = getSalary();
    }

    @Override
    public int getMonthSalary() {
        return (int) (salary + income * BONUS_PROCENT);
    }

    @Override
    public String toString() {
        return String.valueOf(getMonthSalary()) + " руб.";
    }
}
