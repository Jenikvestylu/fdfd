import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Inventory {
    private List<InventoryItem> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(InventoryItem item) {
        if (findItemById(item.getId()).isPresent()) {
            Logger.log(Logger.LogLevel.WARNING, "Položka s ID " + item.getId() + " již existuje.");
            return;
        }
        items.add(item);
        Logger.log(Logger.LogLevel.INFO, "Položka přidána do skladu: " + item);
    }

    public boolean updateItemQuantity(int id, int newQuantity) {
        Optional<InventoryItem> itemOpt = findItemById(id);
        if (itemOpt.isPresent()) {
            InventoryItem item = itemOpt.get();
            int oldQuantity = item.getQuantity();
            item.setQuantity(newQuantity);
            
            Logger.log(Logger.LogLevel.INFO, "Množství položky s ID " + id + 
                       " aktualizováno z " + oldQuantity + " na " + newQuantity);
            
            if (item.isLowStock()) {
                Logger.log(Logger.LogLevel.WARNING, "Nízký stav zásob: " + item);
            }
            
            return true;
        }
        Logger.log(Logger.LogLevel.WARNING, "Položka s ID " + id + " nebyla nalezena.");
        return false;
    }

    public Optional<InventoryItem> findItemById(int id) {
        return items.stream()
                .filter(i -> i.getId() == id)
                .findFirst();
    }

    public List<InventoryItem> getAllItems() {
        return new ArrayList<>(items);
    }

    public List<InventoryItem> getLowStockItems() {
        return items.stream()
                .filter(InventoryItem::isLowStock)
                .collect(Collectors.toList());
    }
    
    public boolean checkAvailability(int itemId, int requestedQuantity) {
        Optional<InventoryItem> item = findItemById(itemId);
        if (item.isPresent()) {
            return item.get().getQuantity() >= requestedQuantity;
        }
        return false;
    }
}