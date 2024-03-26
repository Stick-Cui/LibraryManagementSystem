package lms.dao;

import lms.entity.Book;
import lms.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDao {

    private static List<User> userList = new ArrayList<>();

    public static void addUser(User user) {
        userList.add(user);
    }

    public static void deleteUser(User user) {
        userList.removeIf(temUser -> temUser.getName().equals(user.getName()));
    }

    public static User queryUser(User user) {
        for (User temUser: userList){
            if (temUser.getName().equals(user.getName())) {
                return temUser;
            }
        }
        return null;
    }

    public static boolean ifUserExist(User user) {
        boolean flag = false;
        for (User temUser: userList){
            if (temUser.getName().equals(user.getName())) {
                flag = true;
                break;
            }
        }
        return flag;
    }

}
