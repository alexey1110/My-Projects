public interface Employee {
    int MIN_SALARY = 115_000;
    int MAX_SALARY = 140_000;
    int getMonthSalary();
    default int getSalary(){
        return (int) ((Math.random() * (MAX_SALARY - MIN_SALARY)) + MIN_SALARY);
    }

}
