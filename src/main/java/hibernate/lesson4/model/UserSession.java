package hibernate.lesson4.model;


import java.util.ArrayList;
import java.util.List;

public class UserSession {

    static List<User> usersLoggedIn = new ArrayList<>();

    static List<User> usersRegistered = new ArrayList<>();


    public static void registerUser(User user) {

        if (user != null && !isRegistered(user)) usersRegistered.add(user);

    }

    public static void logIn(User user) {

        if (user != null && !isLoggedIn(user)) usersLoggedIn.add(user);

    }

    public static boolean isLoggedIn(User user) {

        for (User person : usersLoggedIn) {

            if (person.equals(user)) return true;


        }

        return false;
    }

    public static void logOut(User user) {
        if (user != null && !isLoggedIn(user)) {

            usersLoggedIn.remove(user);
        }
    }

    public static boolean isRegistered(User user){

        for (User person: usersRegistered) {
            if (person.equals(user)) return true;
        }


        return false;
    }


}
