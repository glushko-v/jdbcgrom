package hibernate.lesson3;

public class Demo {
    public static void main(String[] args) {


        Hotel hotel1 = new Hotel("Hilton", "Ukraine", "Kiev", "Shevchenko");
        Hotel hotel2 = new Hotel("Conrad", "Turkey", "Istanbul", "Istiklal");

        HotelDAO hd = new HotelDAO();

       hd.findById(3);
    }
}
