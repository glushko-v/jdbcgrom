package hibernate.lesson4.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "HOTEL")
public class Hotel {


    private long id;
    private String name;
    private String country;
    private String city;
    private String street;
    private List<Room> rooms;

    @Override
    public String toString() {
        return name + " " + city;
    }

    public Hotel(String name, String country, String city, String street) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.street = street;
    }

    public Hotel() {
    }

    @Id
    @Column(name = "ID_HOTEL")
    @SequenceGenerator(name = "H_SEQ", sequenceName = "HOTEL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "H_SEQ")
    public long getId() {
        return id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    @Column(name = "CITY")
    public String getCity() {
        return city;
    }

    @Column(name = "STREET")
    public String getStreet() {
        return street;
    }

    @OneToMany(cascade = CascadeType.ALL)
    public List<Room> getRooms() {
        return rooms;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
