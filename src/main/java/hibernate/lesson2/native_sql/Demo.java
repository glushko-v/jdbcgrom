package hibernate.lesson2.native_sql;

public class Demo {
    public static void main(String[] args) {

        ProductDAO pd = new ProductDAO();

        pd.findById(998);

    }
}
