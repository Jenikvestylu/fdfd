import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        // Získání instance systému
        CompanySystem system = CompanySystem.getInstance();
        
        // Demonstrace správy zaměstnanců
        demonstrateEmployeeManagement(system);
        
        // Demonstrace evidence zakázek
        demonstrateOrderManagement(system);
        
        // Demonstrace sledování zásob
        demonstrateInventoryManagement(system);
        
        // Demonstrace zpracování objednávek
        demonstrateOrderProcessing(system);
        
        // Generování celkového reportu
        system.generateSystemReport();
    }
    
    private static void demonstrateEmployeeManagement(CompanySystem system) {
        System.out.println("\n===== SPRAVA ZAMESTNANCU =====");
        
        EmployeeManager employeeManager = system.getEmployeeManager();
        
        // Přidání zaměstnanců
        employeeManager.addEmployee(new Employee(1, "Jan", "Novak", "Programator", 45000));
        employeeManager.addEmployee(new Employee(2, "Petr", "Svoboda", "Analytik", 42000));
        employeeManager.addEmployee(new Employee(3, "Jana", "Dvorakova", "Tester", 38000));
        employeeManager.addEmployee(new Employee(4, "Tomas", "Cerny", "Manazer", 65000));
        
        // Zobrazení všech zaměstnanců
        System.out.println("\nVsichni zamestnanci:");
        List<Employee> allEmployees = employeeManager.getAllEmployees();
        for (Employee employee : allEmployees) {
            System.out.println(employee);
        }
        
        // Vyhledání zaměstnance podle ID
        System.out.println("\nVyhledani zamestnance s ID 2:");
        employeeManager.findEmployeeById(2).ifPresent(System.out::println);
        
        // Editace zaměstnance
        System.out.println("\nEditace zamestnance s ID 3:");
        employeeManager.findEmployeeById(3).ifPresent(employee -> {
            employee.setSalary(42000);
            employee.setPosition("Senior Tester");
            employeeManager.updateEmployee(employee);
            System.out.println("Po aktualizaci: " + employee);
        });
        
        // Odstranění zaměstnance
        System.out.println("\nOdstraneni zamestnance s ID 1:");
        employeeManager.removeEmployee(1);
        
        // Výpočet celkových mzdových nákladů
        System.out.println("\nCelkove mzdove naklady: " + employeeManager.calculateTotalSalaries() + " Kc");
    }
    
    private static void demonstrateOrderManagement(CompanySystem system) {
        System.out.println("\n===== EVIDENCE ZAKAZEK =====");
        
        OrderManager orderManager = system.getOrderManager();
        
        // Přidání zakázek
        orderManager.addOrder(new Order(1, "Vyvoj e-shopu", 
                                       "Implementace noveho e-shopu pro klienta", 
                                       Order.OrderStatus.PRIJATA, 
                                       "2025-04-15", "2025-06-30"));
        
        orderManager.addOrder(new Order(2, "Aktualizace webovych stranek", 
                                       "Modernizace webovych stranek spolecnosti", 
                                       Order.OrderStatus.PROBIHA, 
                                       "2025-03-10", "2025-05-10"));
        
        orderManager.addOrder(new Order(3, "Podpora IS", 
                                       "Podpora informacniho systemu klienta", 
                                       Order.OrderStatus.DOKONCENA, 
                                       "2025-02-01", "2025-04-01"));
        
        // Zobrazení všech zakázek
        System.out.println("\nVsechny zakazky:");
        List<Order> allOrders = orderManager.getAllOrders();
        for (Order order : allOrders) {
            System.out.println(order);
        }
        
        // Aktualizace stavu zakázky
        System.out.println("\nAktualizace stavu zakazky s ID 1:");
        orderManager.updateOrderStatus(1, Order.OrderStatus.PROBIHA);
        
        // Zobrazení aktivních zakázek
        System.out.println("\nAktivni zakazky:");
        List<Order> activeOrders = orderManager.getActiveOrders();
        for (Order order : activeOrders) {
            System.out.println(order);
        }
    }
    
    private static void demonstrateInventoryManagement(CompanySystem system) {
        System.out.println("\n===== SLEDOVANI ZASOB =====");
        
        Inventory inventory = system.getInventory();
        
        // Přidání položek do skladu
        inventory.addItem(new InventoryItem(1, "Notebook Lenovo", 15, 5));
        inventory.addItem(new InventoryItem(2, "Monitor Dell", 12, 3));
        inventory.addItem(new InventoryItem(3, "Klavesnice", 30, 10));
        inventory.addItem(new InventoryItem(4, "Mys", 25, 8));
        inventory.addItem(new InventoryItem(5, "Tiskarna", 2, 3));
        
        // Zobrazení všech položek
        System.out.println("\nVsechny polozky na sklade:");
        List<InventoryItem> allItems = inventory.getAllItems();
        for (InventoryItem item : allItems) {
            System.out.println(item);
        }
        
        // Aktualizace počtu kusů
        System.out.println("\nAktualizace poctu kusu polozky s ID 2:");
        inventory.updateItemQuantity(2, 2);
        
        // Zobrazení položek s nízkým stavem zásob
        System.out.println("\nPolozky s nizkym stavem zasob:");
        List<InventoryItem> lowStockItems = inventory.getLowStockItems();
        for (InventoryItem item : lowStockItems) {
            System.out.println(item);
        }
    }
    
    private static void demonstrateOrderProcessing(CompanySystem system) {
        System.out.println("\n===== ZPRACOVANI OBJEDNAVEK =====");
        
        OrderProcessing orderProcessing = system.getOrderProcessing();
        
        // Vytvoření nové objednávky
        System.out.println("\nVytvoreni nove objednavky:");
        CustomerOrder order1 = orderProcessing.createOrder("Firma ABC s.r.o.");
        order1.addItem(1, 2); // 2 notebooky
        order1.addItem(3, 5); // 5 klávesnic
        System.out.println("Vytvorena objednavka: " + order1);
        
        // Zpracování objednávky
        System.out.println("\nZpracovani objednavky:");
        boolean processed = orderProcessing.processOrder(order1.getId());
        System.out.println("Objednavka zpracovana: " + processed);
        
        // Vytvoření druhé objednávky (nedostatek zboží)
        System.out.println("\nVytvoreni objednavky s nedostatkem zbozi:");
        CustomerOrder order2 = orderProcessing.createOrder("Firma XYZ s.r.o.");
        order2.addItem(5, 5); // 5 tiskáren (máme jen 2)
        System.out.println("Vytvorena objednavka: " + order2);
        
        // Zpracování objednávky s nedostatkem zboží
        System.out.println("\nZpracovani objednavky s nedostatkem zbozi:");
        processed = orderProcessing.processOrder(order2.getId());
        System.out.println("Objednavka zpracovana: " + processed);
        
        // Stav skladu po zpracování objednávek
        System.out.println("\nStav skladu po zpracovani objednavek:");
        List<InventoryItem> allItems = system.getInventory().getAllItems();
        for (InventoryItem item : allItems) {
            System.out.println(item);
        }
    }
}