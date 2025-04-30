public class Employee {
    private String firstName;
    private String lastName;
    private int id;
    private String position;
    private double salary;

    public Employee(int id, String firstName, String lastName, String position, double salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.salary = salary;
    }

    // Gettery a settery
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Jméno: " + firstName + " " + lastName + 
               ", Pozice: " + position + ", Plat: " + salary;
    }
}