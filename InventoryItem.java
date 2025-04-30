public class InventoryItem {
    private int id;
    private String name;
    private int quantity;
    private int minQuantity;

    public InventoryItem(int id, String name, int quantity, int minQuantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.minQuantity = minQuantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

    public boolean isLowStock() {
        return quantity < minQuantity;
    }

    @Override
    public String toString() {
        return "ID položky: " + id + ", Název: " + name + 
               ", Počet kusů: " + quantity + ", Minimální stav: " + minQuantity;
    }
}