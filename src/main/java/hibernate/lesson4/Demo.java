package hibernate.lesson4;

import hibernate.lesson4.DAO.HotelDAO;
import hibernate.lesson4.model.Hotel;

public class Demo {
    public static void main(String[] args) {

        Hotel hilton = new Hotel("Hilton", "Ukraine", "Kiev", "Shevchenko");
        Hotel conrad = new Hotel("Conrad", "Turkey", "Istanbul", "Istiklal");

        HotelDAO hotelDAO = new HotelDAO();

        hotelDAO.delete(41);



    }
}
