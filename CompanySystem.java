import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class CompanySystem {
    private static CompanySystem instance;
    private EmployeeManager employeeManager;
    private OrderManager orderManager;
    private Inventory inventory;
    private OrderProcessing orderProcessing;

    private CompanySystem() {
        this.employeeManager = new EmployeeManager();
        this.orderManager = new OrderManager();
        this.inventory = new Inventory();
        this.orderProcessing = new OrderProcessing(inventory);
        
        Logger.log(Logger.LogLevel.INFO, "Podnikovy system inicializovan.");
    }

    public static CompanySystem getInstance() {
        if (instance == null) {
            instance = new CompanySystem();
        }
        return instance;
    }

    public EmployeeManager getEmployeeManager() {
        return employeeManager;
    }

    public OrderManager getOrderManager() {
        return orderManager;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public OrderProcessing getOrderProcessing() {
        return orderProcessing;
    }

    public void generateSystemReport() {
        System.out.println("\n========== SYSTEMOVY REPORT ==========");
        System.out.println("Datum a cas: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        
        System.out.println("\n----- Zamestnanci -----");
        System.out.println("Celkovy pocet zamestnancu: " + employeeManager.getAllEmployees().size());
        System.out.println("Celkove mzdove naklady: " + employeeManager.calculateTotalSalaries() + " Kc");
        
        System.out.println("\n----- Zakazky -----");
        System.out.println("Celkovy pocet zakazek: " + orderManager.getAllOrders().size());
        System.out.println("Aktivnich zakazek: " + orderManager.getActiveOrders().size());
        
        System.out.println("\n----- Sklad -----");
        System.out.println("Celkovy pocet polozek na sklade: " + inventory.getAllItems().size());
        System.out.println("Polozky s nizkym stavem: " + inventory.getLowStockItems().size());
        
        System.out.println("\n----- Objednavky -----");
        int processedOrders = (int) orderProcessing.getAllOrders().stream()
                                  .filter(CustomerOrder::isProcessed)
                                  .count();
        System.out.println("Celkovy pocet objednavek: " + orderProcessing.getAllOrders().size());
        System.out.println("Zpracovanych objednavek: " + processedOrders);
        
        System.out.println("=====================================");
    }
}