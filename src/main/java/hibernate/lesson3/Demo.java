package hibernate.lesson3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo {
    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String datestr = "20-10-2019";

        Date date = sdf.parse(datestr);


        Hotel hotel1 = new Hotel("Hilton", "Ukraine", "Kiev", "Shevchenko");
        Hotel hotel2 = new Hotel("Conrad", "Turkey", "Istanbul", "Istiklal");

        Room room = new Room(4, 200, 0, 0, date, hotel1);
        Room room1 = new Room(1, 90, 1, 0, date, hotel2);

        HotelDAO hd = new HotelDAO();
        RoomDAO rd = new RoomDAO();

        rd.save(room1);



    }
}
