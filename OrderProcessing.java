import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderProcessing {
    private Inventory inventory;
    private List<CustomerOrder> orders;
    private int nextOrderId;

    public OrderProcessing(Inventory inventory) {
        this.inventory = inventory;
        this.orders = new ArrayList<>();
        this.nextOrderId = 1;
    }

    public CustomerOrder createOrder(String customerName) {
        CustomerOrder order = new CustomerOrder(nextOrderId++, customerName);
        orders.add(order);
        Logger.log(Logger.LogLevel.INFO, "Vytvorena nova objednavka: " + order);
        return order;
    }

    public boolean processOrder(int orderId) {
        Optional<CustomerOrder> orderOpt = findOrderById(orderId);
        
        if (!orderOpt.isPresent()) {
            Logger.log(Logger.LogLevel.WARNING, "Objednavka s ID " + orderId + " nebyla nalezena.");
            return false;
        }
        
        CustomerOrder order = orderOpt.get();
        
        if (order.isProcessed()) {
            Logger.log(Logger.LogLevel.WARNING, "Objednavka s ID " + orderId + " jiz byla zpracovana.");
            return false;
        }
        
        // Kontrola dostupnosti všech položek
        boolean allAvailable = true;
        for (Map.Entry<Integer, Integer> entry : order.getItems().entrySet()) {
            int itemId = entry.getKey();
            int requestedQuantity = entry.getValue();
            
            if (!inventory.checkAvailability(itemId, requestedQuantity)) {
                Optional<InventoryItem> item = inventory.findItemById(itemId);
                String itemName = item.isPresent() ? item.get().getName() : "Neznama polozka";
                int available = item.isPresent() ? item.get().getQuantity() : 0;
                
                Logger.log(Logger.LogLevel.WARNING, "Nedostatek zasob pro polozku " + itemName +
                          " (ID: " + itemId + ") v objednavce " + orderId +
                          ". Pozadovano: " + requestedQuantity + ", Dostupno: " + available);
                allAvailable = false;
            }
        }
        
        if (!allAvailable) {
            return false;
        }
        
        // Aktualizace zásob
        for (Map.Entry<Integer, Integer> entry : order.getItems().entrySet()) {
            int itemId = entry.getKey();
            int requestedQuantity = entry.getValue();
            
            Optional<InventoryItem> itemOpt = inventory.findItemById(itemId);
            if (itemOpt.isPresent()) {
                InventoryItem item = itemOpt.get();
                inventory.updateItemQuantity(itemId, item.getQuantity() - requestedQuantity);
            }
        }
        
        order.setProcessed(true);
        Logger.log(Logger.LogLevel.INFO, "Objednavka s ID " + orderId + " byla uspesne zpracovana.");
        return true;
    }

    public Optional<CustomerOrder> findOrderById(int id) {
        return orders.stream()
                .filter(o -> o.getId() == id)
                .findFirst();
    }

    public List<CustomerOrder> getAllOrders() {
        return new ArrayList<>(orders);
    }
}