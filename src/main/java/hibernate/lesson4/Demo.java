package hibernate.lesson4;

import hibernate.lesson4.DAO.HotelDAO;
import hibernate.lesson4.DAO.RoomDAO;
import hibernate.lesson4.DAO.UserDAO;
import hibernate.lesson4.model.*;
import hibernate.lesson4.service.HotelService;

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
        Hotel nemo = new Hotel("Nemo", "Ukraine", "Odessa", "V.Inber");
        Hotel nemo1 = new Hotel("Nemo", "Ukraine", "Odessa", "V.Inber");
        Hotel ibis = new Hotel("Ibis", "Belguim", "Brussels", null);

//        User user1 = new User("Jack", "1234", "UK", UserType.USER.toString(), false);
//        User user2 = new User("Bill", "556622", "USA", UserType.USER.toString());
//        User user3 = new User("Nancy", "555555", "Ukraine", UserType.USER.toString(), false, false);
//        User user4 = new User("Drew", "ssss", "Germany", UserType.USER.toString());





        HotelDAO hotelDAO = new HotelDAO();
        UserDAO userDAO = new UserDAO();
        RoomDAO roomDAO = new RoomDAO();
        HotelService hs = new HotelService();

//        Room room1 = new Room(2, 100, 'N', 'N', date1, hotelDAO.findById(61));
//        Room room2 = new Room(3, 150, 'Y', 'N', date2, hotelDAO.findById(81));
//        Room room3 = new Room(2, 200, 'Y', 'N', date1, hotelDAO.findById(96));

//        hotelDAO.save(nemo);



        userDAO.login("Bill", "121");









//        roomDAO.save(room1);
//        roomDAO.save(room2);







//        hotelDAO.delete(94);
//        hotelDAO.delete(93);
//        roomDAO.delete(35);
//        roomDAO.delete(34);






    }
}
