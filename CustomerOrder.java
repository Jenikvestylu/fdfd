import java.util.HashMap;
import java.util.Map;

public class CustomerOrder {
    private int id;
    private String customerName;
    private Map<Integer, Integer> items; // ID položky -> počet kusů
    private boolean processed;

    public CustomerOrder(int id, String customerName) {
        this.id = id;
        this.customerName = customerName;
        this.items = new HashMap<>();
        this.processed = false;
    }

    public int getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void addItem(int itemId, int quantity) {
        items.put(itemId, items.getOrDefault(itemId, 0) + quantity);
    }

    public Map<Integer, Integer> getItems() {
        return items;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    @Override
    public String toString() {
        return "ID objednavky: " + id + ", Zakaznik: " + customerName + 
               ", Polozky: " + items + ", Zpracovano: " + processed;
    }
}