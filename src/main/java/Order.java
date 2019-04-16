import java.util.Date;

public class Order {
    private long id;
    private String productName;
    private int price;
    private Date dateOrdered;
    private Date dateConfirmed;

    public Order(long id, String productName, int price, Date dateOrdered, Date dateConfirmed) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.dateOrdered = dateOrdered;
        this.dateConfirmed = dateConfirmed;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", dateOrdered=" + dateOrdered +
                ", dateConfirmed=" + dateConfirmed +
                '}';
    }
}
