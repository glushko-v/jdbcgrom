package hibernate.lesson4.model;

import java.util.Date;

public class Filter {
    private int numberOfGuests;
    private double price;
    private char isBreakfastIncluded;
    private char isPetsAllowed;
    private Date dateAvailableFrom;
    private Hotel hotel;

    public Filter(int numberOfGuests, double price, char isBreakfastIncluded, char isPetsAllowed, Date dateAvailableFrom, Hotel hotel) {
        this.numberOfGuests = numberOfGuests;
        this.price = price;
        this.isBreakfastIncluded = isBreakfastIncluded;
        this.isPetsAllowed = isPetsAllowed;
        this.dateAvailableFrom = dateAvailableFrom;
        this.hotel = hotel;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public double getPrice() {
        return price;
    }

    public char isBreakfastIncluded() {
        return isBreakfastIncluded;
    }

    public char isPetsAllowed() {
        return isPetsAllowed;
    }

    public Date getDateAvailableFrom() {
        return dateAvailableFrom;
    }

    public Hotel getHotel() {
        return hotel;
    }
}


