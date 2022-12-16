package onlineshops.Objects;

import lombok.Getter;
import lombok.Setter;
import onlineshops.DataStorage.Storage;

import java.math.BigDecimal;
@Getter
@Setter
public class User {

    public static int dynamicUserId;
    public static int countId=0;
    private int id;
    private String name;
    private BigDecimal balance;
    private String username;

    public static boolean isExist(String login){
        User checkUser = Storage.users.stream().filter(user -> user.getUsername().equals(login)).findFirst().orElse(null);
        return checkUser != null;
    }
    public User(String name,String username,int id) {
        this.name = name;
        this.username = username;
        this.id = id;
    }

    public static User getUser(int id){
        return Storage.users.stream().filter(user -> user.getId()==id).findFirst().orElse(null);
    }
}
