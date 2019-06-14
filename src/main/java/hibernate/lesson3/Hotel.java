package hibernate.lesson3;

import javax.persistence.*;


@Entity
@Table(name = "HOTEL")
public class Hotel {

    private long id;
    private String name;
    private String country;
    private String city;
    private String street;


    @Override
    public String toString() {
        return  id + " " + name + " " + country + ", " + " " + city;
    }

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "H_SEQ", sequenceName = "HOTEL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "H_SEQ")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Hotel(String name, String country, String city, String street) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.street = street;
    }

    public Hotel() {
    }

    @Column(name = "HOTEL_NAME")
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
}
