import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeManager {
    private List<Employee> employees;

    public EmployeeManager() {
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        if (findEmployeeById(employee.getId()).isPresent()) {
            Logger.log(Logger.LogLevel.WARNING, "Zaměstnanec s ID " + employee.getId() + " již existuje.");
            return;
        }
        employees.add(employee);
        Logger.log(Logger.LogLevel.INFO, "Zaměstnanec přidán: " + employee);
    }

    public boolean updateEmployee(Employee updatedEmployee) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == updatedEmployee.getId()) {
                employees.set(i, updatedEmployee);
                Logger.log(Logger.LogLevel.INFO, "Zaměstnanec aktualizován: " + updatedEmployee);
                return true;
            }
        }
        Logger.log(Logger.LogLevel.WARNING, "Zaměstnanec s ID " + updatedEmployee.getId() + " nebyl nalezen.");
        return false;
    }

    public boolean removeEmployee(int id) {
        Optional<Employee> employee = findEmployeeById(id);
        if (employee.isPresent()) {
            employees.remove(employee.get());
            Logger.log(Logger.LogLevel.INFO, "Zaměstnanec odstraněn: " + employee.get());
            return true;
        }
        Logger.log(Logger.LogLevel.WARNING, "Zaměstnanec s ID " + id + " nebyl nalezen.");
        return false;
    }

    public Optional<Employee> findEmployeeById(int id) {
        return employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }

    public double calculateTotalSalaries() {
        return employees.stream()
                .mapToDouble(Employee::getSalary)
                .sum();
    }
}