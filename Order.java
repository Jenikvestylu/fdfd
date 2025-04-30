public class Order {
    private int id;
    private String name;
    private String description;
    private OrderStatus status;
    private String creationDate;
    private String dueDate;

    public enum OrderStatus {
        PRIJATA, PROBIHA, DOKONCENA
    }

    public Order(int id, String name, String description, OrderStatus status, 
                 String creationDate, String dueDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.creationDate = creationDate;
        this.dueDate = dueDate;
    }

    // Gettery a settery
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "ID zakázky: " + id + ", Název: " + name + ", Popis: " + description + 
               ", Stav: " + status + ", Datum přijetí: " + creationDate + ", Termín: " + dueDate;
    }
}