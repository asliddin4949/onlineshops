package onlineshops.Interfaces;

import onlineshops.DataStorage.Storage;
import onlineshops.Objects.User;

import java.util.Scanner;

public class MainMenu {
    public static void mainMenu(){
        System.out.println("* * * < : > < - > < : > * * *");
        System.out.println("= = = = = Set banned shops of banned products = = = = =");
        System.out.println("'1' - \"enter\"  '2' - \"sign up\"  '0' - exit ");
        Scanner scanner = new Scanner(System.in);
        String command  = scanner.next();
        if(Integer.parseInt(command)==1){
            logIn();
        } else if (Integer.parseInt(command)==2) {
            RegisterInterface.registerUp();
        } else if (Integer.parseInt(command)==0) {
            System.exit(0);
        }else {
            System.err.println("* E * R * R * O * R *");
            mainMenu();
        }
    }

    static void logIn(){
        Scanner scanner =new Scanner(System.in);
        System.out.println("Enter your username");
        String username = scanner.nextLine();
        if(username.equals("admin1")){
           AdminInterface.adminMenu();
        }else {
            if(User.isExist(username)){
                setDynamicUserId(username);
                UserInterface.userMenu();
            }else {
                System.out.println("* You should sign up to enter *");
                mainMenu();
            }
        }
    }
    static void setDynamicUserId(String username){
        User user1 = Storage.users.stream().filter(user -> user.getUsername()
                                                        .equals(username)).findFirst().orElse(null);
        if(user1!=null){
         User.dynamicUserId = user1.getId();
        }
    }
}
