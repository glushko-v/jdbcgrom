package hibernate.lesson2.native_sql;

import javax.persistence.*;

@Entity
@Table(name="PRODUCT")
public class Product {

    private long id;
    private String name;
    private String description;
    private int price;



    @Override
    public String toString() {
        return id + " " + name + " " + description + " " + price;
    }

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "PR_SEQ", sequenceName = "PRODUCT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PR_SEQ")
    public long getId() {
        return id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    @Column(name = "PRICE")
    public int getPrice() {
        return price;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
