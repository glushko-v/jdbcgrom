package hibernate.lesson4;

import hibernate.lesson4.DAO.HotelDAO;
import hibernate.lesson4.DAO.RoomDAO;
import hibernate.lesson4.DAO.UserDAO;
import hibernate.lesson4.model.Hotel;
import hibernate.lesson4.model.Room;
import hibernate.lesson4.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo {
    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String strDate1 = "20-10-2019";
        String strDate2 = "15-08-2019";
        String strDate3 = "02-09-2019";
        String strDate4 = "11-07-2019";


            Date date1 = sdf.parse(strDate1);
            Date date2 = sdf.parse(strDate2);
            Date date3 = sdf.parse(strDate3);
            Date date4 = sdf.parse(strDate4);


        Hotel hilton = new Hotel("Hilton", "Ukraine", "Kiev", "Shevchenko");
        Hotel conrad = new Hotel("Conrad", "Turkey", "Istanbul", "Istiklal");

        User user1 = new User("Jack", "1234", "UK");
        User user2 = new User("Bill", "556622", "USA");

        Room room1 = new Room(2, 100, 'N', 'N', date1, hilton);
        Room room2 = new Room(3, 150, 'Y', 'N', date2, conrad);

        HotelDAO hotelDAO = new HotelDAO();
        UserDAO userDAO = new UserDAO();
        RoomDAO roomDAO = new RoomDAO();


//        roomDAO.save(room1);
//        roomDAO.save(room2);

        hotelDAO.delete(94);
        hotelDAO.delete(93);
//        roomDAO.delete(35);
//        roomDAO.delete(34);






    }
}
