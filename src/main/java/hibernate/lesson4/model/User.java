package hibernate.lesson4.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USER1")
public class User {

    private long id;
    private String userName;
    private String password;
    private String country;
    private List orders;

    @Override
    public String toString() {
        return "User " +  id + " " + userName + " " + country;
    }

    public User(String userName, String password, String country) {
        this.userName = userName;
        this.password = password;
        this.country = country;
    }

    public User() {
    }

    @Id
    @Column(name = "ID_USER")
    @SequenceGenerator(name = "U_SEQ", sequenceName = "USER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "U_SEQ")
    public long getId() {
        return id;
    }

    @Column(name = "USER_NAME")
    public String getUserName() {
        return userName;
    }

    @Column(name = "USER_PASSWORD")
    public String getPassword() {
        return password;
    }

    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    @OneToMany(cascade = CascadeType.ALL)
    public List<Order> getOrders() {
        return orders;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setOrders(List orders) {
        this.orders = orders;
    }
}
