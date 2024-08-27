public class TopManager implements Employee{

    public static final double BONUS_PROCENT = 1.5;
    private int income;
    private int salary;

    public TopManager(Company company) {
        income = company.getIncome();
        salary = getSalary();
    }

    @Override
    public int getMonthSalary() {
        return salary + (income > 10_000_000 ? (int) BONUS_PROCENT * salary : 0) ;
    }

    @Override
    public String toString() {
        return String.valueOf(getMonthSalary()) + " руб.";
    }
}
