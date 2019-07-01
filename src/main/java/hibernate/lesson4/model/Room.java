package hibernate.lesson4.model;



import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ROOM")
public class Room {

    private long id;
    private int numberOfGuests;
    private double price;
    private char breakfastIncluded;
    private char petsAllowed;
    private Date dateAvailableFrom;
    private Hotel hotel;
    private boolean isBooked = false;

    @Override
    public String toString() {
        return "Room " +  id + " " + hotel;
    }

    public Room(int numberOfGuests, double price, char breakfastIncluded, char petsAllowed, Date dateAvailableFrom, Hotel hotel) {
        this.numberOfGuests = numberOfGuests;
        this.price = price;
        this.breakfastIncluded = breakfastIncluded;
        this.petsAllowed = petsAllowed;
        this.dateAvailableFrom = dateAvailableFrom;
        this.hotel = hotel;
    }

    public Room() {
    }

    @Id
    @Column(name = "ID_ROOM")
    @SequenceGenerator(name = "R_SEQ", sequenceName = "ROOM_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "R_SEQ")
    public long getId() {
        return id;
    }

    @Column(name = "NUMBER_OF_GUESTS")
    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    @Column(name = "PRICE")
    public double getPrice() {
        return price;
    }


    @Column(name = "BREAKFAST_INCLUDED")
    public char getBreakfastIncluded() {
        return breakfastIncluded;
    }

    @Column(name = "PETS_ALLOWED")
    public char getPetsAllowed() {
        return petsAllowed;
    }

    @Column(name = "DATE_AVAILABLE_FROM")
    public Date getDateAvailableFrom() {
        return dateAvailableFrom;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "HOTEL_ID")
    public Hotel getHotel() {
        return hotel;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBreakfastIncluded(char breakfastIncluded) {
        this.breakfastIncluded = breakfastIncluded;
    }

    public void setPetsAllowed(char petsAllowed) {
        this.petsAllowed = petsAllowed;
    }

    public void setDateAvailableFrom(Date dateAvailableFrom) {
        this.dateAvailableFrom = dateAvailableFrom;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }
}
