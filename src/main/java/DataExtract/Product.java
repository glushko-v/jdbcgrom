package DataExtract;

public class Product {

        private long id;
        private String name;
        private String description;
        private int price;

        Product(long id, String name, String description, int price) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.price = price;
        }

    @Override
    public String toString() {
        return id + " " + name + " " + description + " " + price;
    }
}
