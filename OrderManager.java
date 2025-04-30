import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderManager {
    private List<Order> orders;

    public OrderManager() {
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        if (findOrderById(order.getId()).isPresent()) {
            Logger.log(Logger.LogLevel.WARNING, "Zakázka s ID " + order.getId() + " již existuje.");
            return;
        }
        orders.add(order);
        Logger.log(Logger.LogLevel.INFO, "Zakázka přidána: " + order);
    }

    public boolean updateOrderStatus(int id, Order.OrderStatus newStatus) {
        Optional<Order> orderOpt = findOrderById(id);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            order.setStatus(newStatus);
            Logger.log(Logger.LogLevel.INFO, "Stav zakázky s ID " + id + " aktualizován na: " + newStatus);
            return true;
        }
        Logger.log(Logger.LogLevel.WARNING, "Zakázka s ID " + id + " nebyla nalezena.");
        return false;
    }

    public Optional<Order> findOrderById(int id) {
        return orders.stream()
                .filter(o -> o.getId() == id)
                .findFirst();
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }

    public List<Order> getActiveOrders() {
        return orders.stream()
                .filter(o -> o.getStatus() == Order.OrderStatus.PRIJATA || o.getStatus() == Order.OrderStatus.PROBIHA)
                .collect(Collectors.toList());
    }
}