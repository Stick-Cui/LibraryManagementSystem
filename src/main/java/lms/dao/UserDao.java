package lms.dao;

import lms.entity.User;
import lms.util.Constant;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.sun.org.apache.bcel.internal.util.SecuritySupport.getResourceAsStream;

public class UserDao {

    private static final List<User> userList = new ArrayList<>();
    public static User currentLoginUser = new User();

    public static void userDataCache() {
        try {
            InputStream inputStream = getResourceAsStream(Constant.USER_DATA_FILE);
            BufferedReader input = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line;
            while ((line = input.readLine()) != null){
                String[] arr = line.split(",");
                User user = new User();
                user.setRole(arr[0]);
                user.setName(arr[1]);
                user.setPassword(arr[2]);
                userList.add(user);
            }
            input.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void userDataPersistence() {
        BufferedWriter bufferedWriter = null;
        try {
            String resourcePath = Objects.requireNonNull(UserDao.class.getClassLoader().getResource("")).getPath();
            //System.out.println(resourcePath);
            File file = new File(resourcePath+Constant.USER_DATA_FILE);
            if(!userList.isEmpty()&&file.exists()&&file.delete()){
                if(file.createNewFile()){
                    bufferedWriter = new BufferedWriter(new FileWriter(file));
                    StringBuilder sb = new StringBuilder();
                    for (User user:userList){
                        sb.append(user.getRole()).append(",").append(user.getName()).append(",").append(user.getPassword());
                        sb.append("\r\n");
                    }
                    bufferedWriter.write(sb.toString());
                    bufferedWriter.flush();
                    bufferedWriter.close();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void addUser(User user) {userList.add(user);
    }

    public static void deleteUser(User user) {userList.removeIf(temUser -> temUser.getName().equals(user.getName()));
    }

    public static boolean validateUser(User user) {
        return userList.stream().anyMatch(temUser -> temUser.getName().equals(user.getName()));
    }

    public static User queryUser(User user) {
        Optional<User> first = userList.stream().filter(temUser -> temUser.getName().equals(user.getName())).findFirst();
        return first.orElseGet(User::new);
    }

}
